package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.channel.message.Message;
import com.asyncapi.v2.model.schema.Schema;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class MessageUtil {

    public static Message process(Class<?> targetClass) {

        Schema payload = ClassUtil.process(targetClass);

        var result = new Message();
        result.setTitle(targetClass.getName());
        result.setPayload(payload);

        return result;
    }
}
