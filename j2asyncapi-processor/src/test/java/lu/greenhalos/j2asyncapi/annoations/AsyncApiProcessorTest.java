package lu.greenhalos.j2asyncapi.annoations;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;
import lu.greenhalos.j2asyncapi.annoations.example.ExampleBaseApplication;
import lu.greenhalos.j2asyncapi.annoations.example.listener.ExampleListener;
import lu.greenhalos.j2asyncapi.annoations.example.publisher.ExamplePublisher.ExamplePublisherMessage;
import lu.greenhalos.j2asyncapi.core.MessageUtil;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class AsyncApiProcessorTest {

    @Test
    void process() {

        var result = AsyncApiProcessor.process(ExampleBaseApplication.class);

        var subscribe = new Operation();
        subscribe.setMessage(MessageUtil.process(ExamplePublisherMessage.class));

        var publisher = new Operation();
        publisher.setMessage(MessageUtil.process(ExampleListener.ExampleListenerMessage.class));

        var channelItem = new ChannelItem();
        channelItem.setSubscribe(subscribe);
        channelItem.setPublish(publisher);
        channelItem.setDescription("Description explaining exactly what happens here");

        var expected = Map.of("exchange/routing.key", channelItem);
        assertThat(result).hasSize(1).usingRecursiveComparison().isEqualTo(expected);
    }
}
