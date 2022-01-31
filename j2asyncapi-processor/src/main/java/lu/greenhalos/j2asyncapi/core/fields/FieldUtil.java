package lu.greenhalos.j2asyncapi.core.fields;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

import java.util.List;

import javax.annotation.Nullable;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class FieldUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final List<FieldType> FIELD_TYPES = List.of(new NumberFieldType(), new StringFieldType(),
            new BooleanFieldType(), new DecimalNumberFieldType(), new ListFieldType(), new EnumFieldType(),
            new DateFieldType(), new DateTimeFieldType());

    public static boolean isRawType(Class<?> targetClass) {

        return FIELD_TYPES.stream().anyMatch(ft -> ft.canHandle(targetClass));
    }


    public static Reference process(Class<?> originalTargetClass, AsyncAPI asyncAPI, @Nullable Field field) {

        Class<?> targetClass;

        if (field != null && field.isAnnotationPresent(AsyncApi.Field.class)
                && field.getAnnotation(AsyncApi.Field.class).type() != Void.class) {
            targetClass = field.getAnnotation(AsyncApi.Field.class).type();
        } else {
            targetClass = originalTargetClass;
        }

        var schema = FIELD_TYPES.stream()
                .filter(fieldType -> fieldType.canHandle(targetClass))
                .findFirst()
                .map(fieldType -> toSchema(field, fieldType, asyncAPI))
                .orElseThrow(() -> new IllegalArgumentException(""));

        var schemaName = String.format("%s-%x", targetClass.getName(), schema.hashCode());
        asyncAPI.getComponents().getSchemas().put(schemaName, schema);

        return new Reference(String.format("#/components/schemas/%s", schemaName));
    }


    private static Schema toSchema(@Nullable Field field, FieldType fieldType, AsyncAPI asyncAPI) {

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

        fieldType.handleAdditionally(field, fieldSchema, asyncAPI);

        return fieldSchema;
    }
}
