package lu.greenhalos.j2asyncapi.core.fields;

import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.ClassUtil;

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
            new BooleanFieldType(), new DecimalNumberFieldType(), new ListFieldType(), new EnumFieldType());

    public static Schema process(Field field) {

        return process(field, field.getType());
    }


    public static Schema process(@Nullable Field field, Class<?> originalFieldType) {

        if (field == null) {
            LOG.info("Start processing field of type {}", originalFieldType);
        } else {
            if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                return null;
            }

            LOG.info("Start processing field {} with type {}", field.getName(), originalFieldType);
        }

        AsyncApi.Field fieldAnnotation;

        if (field != null && field.isAnnotationPresent(AsyncApi.Field.class)) {
            fieldAnnotation = field.getAnnotation(AsyncApi.Field.class);
        } else {
            fieldAnnotation = null;
        }

        Class<?> finalFieldType;

        if (fieldAnnotation != null && fieldAnnotation.type() != Void.class) {
            finalFieldType = fieldAnnotation.type();
        } else {
            finalFieldType = originalFieldType;
        }

        return FIELD_TYPES.stream()
            .filter(fieldType -> fieldType.canHandle(finalFieldType))
            .findFirst()
            .map(fieldType -> toSchema(field, fieldType, fieldAnnotation))
            .orElseGet(() -> {
                if (field != null) {
                    return ClassUtil.process(originalFieldType, field.getName());
                } else {
                    return ClassUtil.process(originalFieldType, null);
                }
            });
    }


    private static Schema toSchema(@Nullable Field field, FieldType fieldType, AsyncApi.Field fieldAnnotation) {

        var fieldSchema = new Schema();

        if (field != null) {
            fieldSchema.setTitle(field.getName());
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

        fieldType.handleAdditionally(field, fieldSchema);

        return fieldSchema;
    }
}
