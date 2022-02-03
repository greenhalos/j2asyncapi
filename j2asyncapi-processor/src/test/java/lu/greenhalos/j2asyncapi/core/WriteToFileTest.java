package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import com.asyncapi.v2.model.component.Components;
import com.asyncapi.v2.model.info.Contact;
import com.asyncapi.v2.model.info.Info;
import com.asyncapi.v2.model.server.Server;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import org.apache.commons.io.FileUtils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import java.math.BigDecimal;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        var components = new Components();
        components.setMessages(new HashMap<>());
        components.setSchemas(new HashMap<>());

        var asyncAPI = new AsyncAPI();
        asyncAPI.setComponents(components);
        asyncAPI.setInfo(info());
        asyncAPI.setServers(servers());

        var config = Config.builder().withAsyncApi(asyncAPI).build();
        var messageReference = MessageUtil.process(Example.class, config);

        var subscribe = new Operation();
        subscribe.setMessage(messageReference);

        var channelItem = new ChannelItem();
        channelItem.setDescription("Publish information");
        channelItem.setSubscribe(subscribe);

        asyncAPI.setChannels(Map.of("channelName", channelItem));
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
