package lu.greenhalos.j2asyncapi.annoations.example.listener;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import java.math.BigDecimal;

import static lu.greenhalos.j2asyncapi.annotations.AsyncApi.Type.LISTENER;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ExampleListener {

    @AsyncApi(
        type = LISTENER, exchange = "exchange", routingKey = "routing.key", payload = ExampleListenerMessage.class,
        description = "Description explaining exactly what happens here"
    )
    public void on(ExampleListenerMessage message) {
    }

    public static class ExampleListenerMessage {

        public BigDecimal amount;
        @AsyncApi.Field(examples = { "EUR", "USD", "CHF" })
        public String currency;
    }
}
