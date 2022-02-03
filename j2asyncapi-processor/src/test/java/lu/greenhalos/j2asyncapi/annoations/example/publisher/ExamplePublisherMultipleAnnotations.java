package lu.greenhalos.j2asyncapi.annoations.example.publisher;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import static lu.greenhalos.j2asyncapi.annotations.AsyncApi.Type.PUBLISHER;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ExamplePublisherMultipleAnnotations {

    @AsyncApi(type = PUBLISHER, exchange = "exchange", routingKey = "routing.key.multiple1")
    @AsyncApi(type = PUBLISHER, exchange = "exchange", routingKey = "routing.key.multiple2")
    public void publish() {

        System.out.println("publish the message ExampleListenerMessage");
    }
}
