package lu.greenhalos.j2asyncapi.example;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import java.math.BigDecimal;

import static lu.greenhalos.j2asyncapi.annotations.AsyncApi.Type.LISTENER;
import static lu.greenhalos.j2asyncapi.annotations.AsyncApi.Type.PUBLISHER;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ExampleApplication {

    @AsyncApi(
        type = PUBLISHER, exchange = "exchange", routingKey = "routing.key", payload = ExamplePublisherMessage.class
    )
    public void publish() {

        System.out.println("publish the message ExampleListenerMessage");
    }


    @AsyncApi(
        type = LISTENER, exchange = "exchange", routingKey = "routing.key", payload = ExampleListenerMessage.class,
        description = "Description explaining exactly what happens here"
    )
    public void on(ExampleListenerMessage message) {
    }

    @AsyncApi.Message(description = "this is a message which gets published")
    public static class ExamplePublisherMessage {

        public BigDecimal amount;
        @AsyncApi.Field(type = Integer.class)
        public String currency;
    }

    public static class ExampleListenerMessage {

        public BigDecimal amount;
        @AsyncApi.Field(examples = { "EUR", "USD", "CHF" })
        public String currency;
        public ObjectRepresentingAnId id;
    }

    public static class ObjectRepresentingAnId {

        private final String value;

        public ObjectRepresentingAnId(String value) {

            this.value = value;
        }
    }
}
