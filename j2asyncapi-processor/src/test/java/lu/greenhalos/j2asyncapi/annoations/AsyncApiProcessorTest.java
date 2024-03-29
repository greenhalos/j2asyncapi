package lu.greenhalos.j2asyncapi.annoations;

import lu.greenhalos.j2asyncapi.annoations.example.ExampleBaseApplication;
import lu.greenhalos.j2asyncapi.annoations.example.listener.ExampleListener;
import lu.greenhalos.j2asyncapi.annoations.example.publisher.ExamplePublisher;
import lu.greenhalos.j2asyncapi.core.Config;
import lu.greenhalos.j2asyncapi.core.MessageUtil;
import lu.greenhalos.j2asyncapi.schemas.AsyncApiDocumentRoot;
import lu.greenhalos.j2asyncapi.schemas.ChannelItem;
import lu.greenhalos.j2asyncapi.schemas.Operation;
import lu.greenhalos.j2asyncapi.schemas.Reference;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class AsyncApiProcessorTest {

    @Test
    void process() {

        var asyncAPI = new AsyncApiDocumentRoot();
        var config = Config.builder().withAsyncApi(asyncAPI).build();

        AsyncApiProcessor.process(ExampleBaseApplication.class, config);

        var subscribe = new Operation();
        subscribe.setMessage(MessageUtil.process(ExamplePublisher.ExamplePublisherMessage.class, config));

        var publisher = new Operation();
        publisher.setMessage(MessageUtil.process(ExampleListener.ExampleListenerMessage.class, config));

        var channelItem = new ChannelItem();
        channelItem.setSubscribe(subscribe);
        channelItem.setPublish(publisher);
        channelItem.setDescription("Description explaining exactly what happens here");

        Operation subscriberEmptyMessage = new Operation();
        subscriberEmptyMessage.setMessage(new Reference("#/components/messages/j.l.Void"));

        var channelItemEmptyMessage = new ChannelItem();
        channelItemEmptyMessage.setSubscribe(subscriberEmptyMessage);

        var expected = Map.of( //
                "exchange/routing.key", channelItem, //
                "exchange/queries", channelItemEmptyMessage, //
                "exchange/routing.key.multiple1", channelItemEmptyMessage, //
                "exchange/routing.key.multiple2", channelItemEmptyMessage, //
                "routing.key.default.exchange", channelItemEmptyMessage //
                );
        assertThat(asyncAPI.getChannels().getAdditionalProperties()).usingRecursiveComparison().isEqualTo(expected);
    }
}
