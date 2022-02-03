package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.fields.FieldType;

import java.lang.reflect.Field;

import java.util.List;

import javax.annotation.Nullable;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class FieldUtil {

    public static boolean isRawType(Class<?> targetClass, Config config) {

        return config.fieldTypes.stream().anyMatch(ft -> ft.canHandle(targetClass));
    }


    public static Reference process(Class<?> originalTargetClass, AsyncAPI asyncAPI, @Nullable Field field,
        Config config) {

        Class<?> targetClass;

        if (field != null && field.isAnnotationPresent(AsyncApi.Field.class)
                && field.getAnnotation(AsyncApi.Field.class).type() != Void.class) {
            targetClass = field.getAnnotation(AsyncApi.Field.class).type();
        } else {
            targetClass = originalTargetClass;
        }

        var schema = config.fieldTypes.stream()
                .filter(fieldType -> fieldType.canHandle(targetClass))
                .findFirst()
                .map(fieldType -> toSchema(field, fieldType, asyncAPI, config))
                .orElseThrow(() -> new IllegalArgumentException(""));

        var schemaName = String.format("%s-%x", targetClass.getName(), schema.hashCode());
        asyncAPI.getComponents().getSchemas().put(schemaName, schema);

        return new Reference(String.format("#/components/schemas/%s", schemaName));
    }


    private static Schema toSchema(@Nullable Field field, FieldType fieldType, AsyncAPI asyncAPI, Config config) {

        var fieldSchema = new Schema();

        AsyncApi.Field fieldAnnotation = null;

        if (field != null) {
            fieldAnnotation = field.getAnnotation(AsyncApi.Field.class);
        }

        fieldSchema.setType(fieldType.getType(field));

        if (fieldAnnotation != null && !"".equals(fieldAnnotation.format())) {
            fieldSchema.setFormat(fieldAnnotation.format());
        } else {
            fieldSchema.setFormat(fieldType.getFormat(field));
        }

        if (fieldAnnotation != null && fieldAnnotation.examples().length > 0) {
            fieldSchema.setExamples(List.of(fieldAnnotation.examples()));
        } else {
            fieldSchema.setExamples(fieldType.getExamples(field));
        }

        fieldType.handleAdditionally(field, fieldSchema, asyncAPI, config);

        return fieldSchema;
    }
}
