package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.core.fields.FieldUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class ClassUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static Schema process(Class<?> targetClass) {

        return process(targetClass, null);
    }


    public static Schema process(Class<?> targetClass, String title) {

        LOG.info("Start processing class {}", targetClass.getName());

        var payload = new Schema();
        payload.setTitle(title);

        var properties = new HashMap<String, Schema>();

        var declaredFields = new ArrayList<Field>();
        var currentClass = targetClass;

        do {
            declaredFields.addAll(List.of(currentClass.getDeclaredFields()));
            currentClass = currentClass.getSuperclass();
        } while (currentClass != Object.class);

        for (Field field : declaredFields) {
            Schema fieldSchema = FieldUtil.process(field);

            if (fieldSchema != null) {
                properties.put(fieldSchema.getTitle(), fieldSchema);
            }
        }

        payload.setProperties(properties);

        return payload;
    }
}
