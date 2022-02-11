package lu.greenhalos.j2asyncapi.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import lu.greenhalos.j2asyncapi.annoations.AsyncApiProcessor;
import lu.greenhalos.j2asyncapi.core.Config;
import lu.greenhalos.j2asyncapi.schemas.AsyncApiDocumentRoot;
import lu.greenhalos.j2asyncapi.schemas.Contact;
import lu.greenhalos.j2asyncapi.schemas.Info;
import lu.greenhalos.j2asyncapi.schemas.Server;
import lu.greenhalos.j2asyncapi.schemas.Servers;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class AsyncApiGeneratorTest {

    private static final String DOCS_TARGET = "../docs/asyncapi-example.yaml";

    @Test
    void generate() throws IOException {

        var asyncAPI = new AsyncApiDocumentRoot();
        asyncAPI.setInfo(info());
        asyncAPI.setServers(servers());

        var config = Config.builder()
                .withAsyncApi(asyncAPI)
                .build();

        AsyncApiProcessor.process(ExampleApplication.class, config);
        writeToFile(asyncAPI);
    }


    private Servers servers() {

        var servers = new Servers();
        servers.setAdditionalProperty("test", server());

        return servers;
    }


    private Server server() {

        var server = new Server();
        server.setUrl("http://rabbitmq");
        server.setDescription("RabbitMQ Server");
        server.setProtocol("amqp");

        return server;
    }


    private static Info info() {

        try {
            var contact = new Contact();
            contact.setName("Fancy Team");
            contact.setUrl(new URI("https://greenhalos.lu"));
            contact.setEmail("asyncapi@greenhalos.lu");

            var info = new Info();
            info.setTitle("Application API");
            info.setVersion("0.1.0");
            info.setContact(contact);

            return info;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


    private static void writeToFile(AsyncApiDocumentRoot asyncAPI) throws IOException {

        var objectMapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        var bytes = objectMapper.writeValueAsBytes(asyncAPI);
        FileUtils.writeByteArrayToFile(new File(DOCS_TARGET), bytes);
    }
}
