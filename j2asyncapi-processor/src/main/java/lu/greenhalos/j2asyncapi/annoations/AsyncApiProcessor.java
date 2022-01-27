package lu.greenhalos.j2asyncapi.annoations;

import com.asyncapi.v2.model.channel.ChannelItem;
import com.asyncapi.v2.model.channel.operation.Operation;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.MessageUtil;

import org.reflections.Reflections;

import org.reflections.scanners.Scanners;

import org.reflections.util.ConfigurationBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toConcurrentMap;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class AsyncApiProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static Map<String, ChannelItem> process(Class<?> baseClass) {

        var packageName = baseClass.getPackageName();
        LOG.info("Start looking for AsyncApi annotations in package in {}", packageName);

        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackage(packageName)
                .setScanners(Scanners.SubTypes, Scanners.MethodsAnnotated));

        return reflections.getMethodsAnnotatedWith(AsyncApi.class)
            .stream()
            .collect(toConcurrentMap(AsyncApiProcessor::getChannelName, AsyncApiProcessor::toChannel,
                    AsyncApiProcessor::merge));
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


    private static String getChannelName(Method method) {

        var annotation = method.getAnnotation(AsyncApi.class);
        var exchange = annotation.exchange();
        var routingKey = annotation.routingKey();

        if (exchange != null) {
            return String.format("%s/%s", exchange, routingKey);
        }

        return routingKey;
    }


    private static ChannelItem toChannel(Method method) {

        var annotation = method.getAnnotation(AsyncApi.class);

        var description = annotation.description();

        var message = MessageUtil.process(annotation.payload());

        var operation = new Operation();
        operation.setMessage(message);

        var result = new ChannelItem();

        if (!"".equals(description)) {
            result.setDescription(description);
        }

        switch (annotation.type()) {
            case PUBLISHER:
                result.setSubscribe(operation);
                break;

            case LISTENER:
                result.setPublish(operation);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + annotation.type());
        }

        return result;
    }
}
