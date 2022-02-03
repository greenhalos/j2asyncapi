package lu.greenhalos.j2asyncapi.annoations.example.publisher;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import static lu.greenhalos.j2asyncapi.annotations.AsyncApi.Type.PUBLISHER;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ExamplePublisherEmptyBody {

    @AsyncApi(type = PUBLISHER, exchange = "exchange", routingKey = "queries")
    public void publish() {

        System.out.println("publish the message ExampleListenerMessage");
    }
}
