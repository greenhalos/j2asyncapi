package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.channel.message.Message;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import static lu.greenhalos.j2asyncapi.core.ClassNameUtil.name;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class MessageUtil {

    public static Reference process(Class<?> targetClass, Config config) {

        var payload = ClassUtil.process(targetClass, config);

        var result = new Message();
        result.setTitle(targetClass.getSimpleName());
        result.setPayload(payload);

        if (targetClass.isAnnotationPresent(AsyncApi.Message.class)) {
            var annotation = targetClass.getAnnotation(AsyncApi.Message.class);
            result.setDescription(annotation.description());
        }

        config.asyncAPI.getComponents().getMessages().put(name(targetClass), result);

        return new Reference(String.format("#/components/messages/%s", name(targetClass)));
    }
}
