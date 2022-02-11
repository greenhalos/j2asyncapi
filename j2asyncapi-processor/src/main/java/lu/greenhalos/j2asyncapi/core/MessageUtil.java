package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.schemas.Message;
import lu.greenhalos.j2asyncapi.schemas.Reference;

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

        config.asyncAPI.getComponents().getMessages().getAdditionalProperties().put(name(targetClass), result);

        return new Reference(String.format("#/components/messages/%s", name(targetClass)));
    }
}
