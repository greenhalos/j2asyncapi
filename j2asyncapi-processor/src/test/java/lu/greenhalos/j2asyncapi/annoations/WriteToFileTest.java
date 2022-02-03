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
import lu.greenhalos.j2asyncapi.annoations.example.listener.ExampleListener.ObjectRepresentingAnId;
import lu.greenhalos.j2asyncapi.core.Config;
import lu.greenhalos.j2asyncapi.core.fields.FieldType;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class WriteToFileTest {

    private static final String DOCS_TARGET = "../docs/asyncapi-annotations.yaml";

    @Test
    void generate() throws IOException {

        var config = Config.builder().addFieldType(new ObjectRepresentingAnIdFieldType()).build();

        var asyncAPI = new AsyncAPI();
        asyncAPI.setInfo(info());
        asyncAPI.setServers(servers());

        AsyncApiProcessor.process(ExampleBaseApplication.class, asyncAPI, config);
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

    private static class ObjectRepresentingAnIdFieldType implements FieldType {

        @Override
        public List<Class<?>> getAllowedClasses() {

            return List.of(ObjectRepresentingAnId.class);
        }


        @Override
        public List<Object> getExamples(@Nullable Field field) {

            return List.of("value1", "value42");
        }


        @Override
        public String getType(@Nullable Field field) {

            return "string";
        }


        @Override
        public String getFormat(@Nullable Field field) {

            return null;
        }
    }
}
