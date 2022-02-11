package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.schemas.Message;
import lu.greenhalos.j2asyncapi.schemas.Reference;
import lu.greenhalos.j2asyncapi.schemas.Schema;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lu.greenhalos.j2asyncapi.core.ClassNameUtil.name;
import static lu.greenhalos.j2asyncapi.core.Config.defaultConfig;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public final class FieldTestUtil {

    public static void assertSchemaOnClass(Class<?> exampleClass, Schema fieldSchema) {

        Class<?> fieldType = getFieldType(exampleClass);

        var fieldSchemaHashCode = fieldSchema.hashCode();
        var schemaName = String.format("%s-%x", name(fieldType), fieldSchemaHashCode);
        var expectedSchemasForField = Map.of(schemaName, fieldSchema);
        assertSchemaOnClass(exampleClass, expectedSchemasForField, fieldSchemaHashCode);
    }


    public static void assertSchemaOnClass(Class<?> exampleClass, Map<String, Schema> expectedSchemasForField,
        int fieldSchemaHashCode) {

        var fieldType = getFieldType(exampleClass);

        assertSchemaOnClass(exampleClass, expectedSchemasForField, fieldType, fieldSchemaHashCode);
    }


    public static void assertSchemaOnClass(Class<?> exampleClass, Map<String, Schema> expectedSchemasForField,
        Class<?> fieldType, int fieldSchemaHashCode) {

        // when
        var config = defaultConfig();
        var reference = MessageUtil.process(exampleClass, config);

        // then
        var messageReference = String.format("#/components/messages/%s", name(exampleClass));
        assertThat(reference).usingRecursiveComparison().isEqualTo(new Reference(messageReference));

        var message = new Message();
        message.setPayload(new Reference(String.format("#/components/schemas/%s", name(exampleClass))));
        message.setTitle(exampleClass.getSimpleName());

        Map<String, Object> expectedMessages = Map.of(name(exampleClass), message);
        assertThat(config.asyncAPI.getComponents().getMessages().getAdditionalProperties()).usingRecursiveComparison()
            .isEqualTo(expectedMessages);

        var fieldReferenceSchema = new Schema();
        fieldReferenceSchema.set$ref(String.format("#/components/schemas/%s-%x", name(fieldType),
                fieldSchemaHashCode));

        var exampleSchema = new Schema();
        exampleSchema.setTitle("Example");
        exampleSchema.setProperties(Map.of("field", fieldReferenceSchema));

        Map<String, Object> expectedSchemas = new HashMap<>();
        expectedSchemas.put(name(exampleClass), exampleSchema);
        expectedSchemas.putAll(expectedSchemasForField);

        assertThat(config.asyncAPI.getComponents().getSchemas().getAdditionalProperties()).usingRecursiveComparison()
            .isEqualTo(expectedSchemas);
    }


    private static Class<?> getFieldType(Class<?> exampleClass) {

        try {
            return getAllFields(exampleClass).stream()
                .filter(f -> "field".equals(f.getName()))
                .findFirst()
                .map(Field::getType)
                .orElseThrow(() -> new NoSuchFieldException("field"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }


    private static List<Field> getAllFields(Class<?> type) {

        var result = new ArrayList<>(List.of(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            result.addAll(getAllFields(type.getSuperclass()));
        }

        return result;
    }
}
