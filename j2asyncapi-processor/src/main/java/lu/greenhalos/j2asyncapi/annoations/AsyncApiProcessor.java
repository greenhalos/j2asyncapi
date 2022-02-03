package lu.greenhalos.j2asyncapi.annoations;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.Config;
import lu.greenhalos.j2asyncapi.core.MessageUtil;

import org.reflections.Reflections;

import org.reflections.scanners.Scanners;

import org.reflections.util.ConfigurationBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class AsyncApiProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static void process(Class<?> baseClass, Config config) {

        var packageName = baseClass.getPackageName();
        LOG.info("Start looking for AsyncApi annotations in package in {}", packageName);

        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackage(packageName)
                .setScanners(Scanners.SubTypes, Scanners.MethodsAnnotated));

        reflections.getMethodsAnnotatedWith(AsyncApi.class).stream()
            .map(m -> m.getAnnotation(AsyncApi.class))
            .forEach(a -> toChannel(getChannelName(a), a, config));
    }


    private static ChannelItem merge(ChannelItem a, ChannelItem b) {

        var result = new ChannelItem();

        merge(a, b, ChannelItem::getBindings, result::setBindings);
        merge(a, b, ChannelItem::getParameters, result::setParameters);
        merge(a, b, ChannelItem::getDescription, result::setDescription);
        merge(a, b, ChannelItem::getPublish, result::setPublish);
        merge(a, b, ChannelItem::getSubscribe, result::setSubscribe);

        return result;
    }


    private static <T> void merge(ChannelItem a, ChannelItem b, Function<ChannelItem, T> extractor,
        Consumer<T> setter) {

        var valueA = extractor.apply(a);
        var valueB = extractor.apply(b);

        if (valueA == null || valueB == null) {
            var value = Stream.of(valueA, valueB).filter(Objects::nonNull).findFirst().orElse(null);
            setter.accept(value);
        } else {
            if (Objects.equals(valueA, valueB)) {
                setter.accept(valueA);
            } else {
                throw new IllegalArgumentException(String.format(
                        "Unable to determin which value to take as both are non null but different: '%s' and '%s'",
                        valueA, valueB));
            }
        }
    }


    private static String getChannelName(AsyncApi annotation) {

        var exchange = annotation.exchange();
        var routingKey = annotation.routingKey();

        if (exchange != null) {
            return String.format("%s/%s", exchange, routingKey);
        }

        return routingKey;
    }


    private static void toChannel(String channelName, AsyncApi annotation, Config config) {

        var description = annotation.description();

        var result = new ChannelItem();

        if (!"".equals(description)) {
            result.setDescription(description);
        }

        switch (annotation.type()) {
            case PUBLISHER:
                toOperation(result::setSubscribe, annotation.payload(), config);
                break;

            case LISTENER:
                toOperation(result::setPublish, annotation.payload(), config);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + annotation.type());
        }

        var channels = config.asyncAPI.getChannels();

        if (channels.containsKey(channelName)) {
            result = merge(channels.get(channelName), result);
        }

        channels.put(channelName, result);
    }


    private static void toOperation(Consumer<Operation> operationConsumer, Class<?> payload, Config config) {

        var reference = MessageUtil.process(payload, config);

        var operation = new Operation();
        operation.setMessage(reference);
        operationConsumer.accept(operation);
    }
}
