package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.schema.Schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

import static java.lang.reflect.Modifier.isStatic;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ClassUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static Reference process(Class<?> targetClass, AsyncAPI asyncAPI, Config config) {

        return process(asyncAPI, null, targetClass, config);
    }


    private static Reference process(Field field, AsyncAPI asyncAPI, Config config) {

        Class<?> targetClass = field.getType();

        return process(asyncAPI, field, targetClass, config);
    }


    private static Reference process(AsyncAPI asyncAPI, @Nullable Field field, Class<?> targetClass, Config config) {

        if (FieldUtil.isRawType(targetClass, config)) {
            LOG.info("{} is a raw type", targetClass.getName());

            return FieldUtil.process(targetClass, asyncAPI, field, config);
        }

        LOG.info("{} is not a raw type", targetClass.getName());

        var properties = new HashMap<String, Schema>();

        var declaredFields = getAllFields(targetClass);

        for (Field declaredField : declaredFields) {
            if (isStatic(declaredField.getModifiers())) {
                continue;
            }

            var fieldSchema = ClassUtil.process(declaredField, asyncAPI, config);
            var schema = new Schema();
            schema.setRef(fieldSchema.getRef());
            properties.put(declaredField.getName(), schema);
        }

        var schema = new Schema();
        schema.setTitle(targetClass.getSimpleName());
        schema.setProperties(properties);

        asyncAPI.getComponents().getSchemas().put(targetClass.getName(), schema);

        return new Reference(String.format("#/components/schemas/%s", targetClass.getName()));
    }


    private static List<Field> getAllFields(Class<?> type) {

        var result = new ArrayList<>(List.of(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            result.addAll(getAllFields(type.getSuperclass()));
        }

        return result;
    }
}
