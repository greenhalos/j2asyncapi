package lu.greenhalos.j2asyncapi.annoations.example.publisher;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import java.math.BigDecimal;

import static lu.greenhalos.j2asyncapi.annotations.AsyncApi.Type.PUBLISHER;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ExamplePublisher {

    @AsyncApi(
        type = PUBLISHER, exchange = "exchange", routingKey = "routing.key", payload = ExamplePublisherMessage.class
    )
    public void publish() {

        System.out.println("publish the message ExampleListenerMessage");
    }

    @AsyncApi.Message(description = "this is a message which gets published")
    public static class ExamplePublisherMessage {

        public BigDecimal amount;
        @AsyncApi.Field(type = Integer.class)
        public String currency;
    }
}
