package lu.greenhalos.j2asyncapi.annoations;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.info.Contact;
import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import lu.greenhalos.j2asyncapi.annoations.example.ExampleBaseApplication;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import java.util.Map;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class WriteToFileTest {

    private static final String DOCS_TARGET = "../docs/asyncapi-annotations.yaml";

    @Test
    void generate() throws IOException {

        var asyncAPI = new AsyncAPI();
        asyncAPI.setInfo(info());
        asyncAPI.setServers(servers());

        AsyncApiProcessor.process(ExampleBaseApplication.class, asyncAPI);
        writeToFile(asyncAPI);
    }


    private Map<String, Server> servers() {

        return Map.of("test", server());
    }


    private Server server() {

        var server = new Server();
        server.setUrl("http://rabbitmq");
        server.setDescription("RabbitMQ Server");
        server.setProtocol("amqp");

        return server;
    }


    private static Info info() {

        return new Info("Application API", "0.1.0", null, null,
                new Contact("Fancy Team", "https://greenhalos.lu", "asyncapi@greenhalos.lu"), null);
    }


    private static void writeToFile(AsyncAPI asyncAPI) throws IOException {

        var objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        var bytes = objectMapper.writeValueAsBytes(asyncAPI);
        FileUtils.writeByteArrayToFile(new File(DOCS_TARGET), bytes);
    }
}
