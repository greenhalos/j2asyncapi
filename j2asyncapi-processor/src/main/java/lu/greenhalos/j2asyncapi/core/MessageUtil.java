package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.channel.message.Message;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class MessageUtil {

    public static Reference process(Class<?> targetClass, AsyncAPI asyncAPI, Config config) {

        var payload = ClassUtil.process(targetClass, asyncAPI, config);

        var result = new Message();
        result.setTitle(targetClass.getName());
        result.setPayload(payload);

        asyncAPI.getComponents().getMessages().put(targetClass.getName(), result);

        return new Reference(String.format("#/components/messages/%s", targetClass.getName()));
    }
}
