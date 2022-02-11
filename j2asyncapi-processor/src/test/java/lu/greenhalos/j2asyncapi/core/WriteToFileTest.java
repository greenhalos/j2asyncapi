package lu.greenhalos.j2asyncapi.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.schemas.AsyncApiDocumentRoot;
import lu.greenhalos.j2asyncapi.schemas.ChannelItem;
import lu.greenhalos.j2asyncapi.schemas.Contact;
import lu.greenhalos.j2asyncapi.schemas.Info;
import lu.greenhalos.j2asyncapi.schemas.Operation;
import lu.greenhalos.j2asyncapi.schemas.Server;
import lu.greenhalos.j2asyncapi.schemas.Servers;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import java.math.BigDecimal;

import java.net.URI;
import java.net.URISyntaxException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class WriteToFileTest {

    private static final String DOCS_TARGET = "../docs/asyncapi.yaml";

    private enum ExampleEnum {

        VALUE_2,
        VALUE_3
    }

    @Test
    void generate() throws IOException {

        var asyncAPI = new AsyncApiDocumentRoot();
        asyncAPI.setInfo(info());
        asyncAPI.setServers(servers());

        var config = Config.builder().withAsyncApi(asyncAPI).build();
        var messageReference = MessageUtil.process(Example.class, config);

        var subscribe = new Operation();
        subscribe.setMessage(messageReference);

        var channelItem = new ChannelItem();
        channelItem.setDescription("Publish information");
        channelItem.setSubscribe(subscribe);

        asyncAPI.getChannels().setAdditionalProperty("channelName", channelItem);
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

    private static class Example {

        private static final String staticFieldToIgnore = "ignore me please";

        public String currency;
        public Boolean isFancy;
        public Integer intAmount;
        public Long longAmount;
        public Float floatingAmount;
        public Double doubleAmount;
        public BigDecimal bigDecimalAmount;
        public InnerExample innerExample;
        public final String finalCurrency;
        private final String privateFinalCurrency;
        public List<String> listCurrency;
        public ExampleEnum exampleEnum;
        public LocalDate exampleLocalDate;
        public LocalDateTime exampleLocalDateTime;
        public Instant exampleInstant;

        @AsyncApi.Field(type = Integer.class, examples = { "456565", "4654" }, format = "flapping")
        public String fieldAnnotation;

        private Example(String finalCurrency, String privateFinalCurrency) {

            this.finalCurrency = finalCurrency;
            this.privateFinalCurrency = privateFinalCurrency;
        }
    }

    private static class InnerExample {

        public String innerCurrency;
        public NestedInnerExample nestedInnerExample;
    }

    private static class NestedInnerExample {

        public String nestedInnerCurrency;
    }
}
