package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.channel.message.Message;
import com.asyncapi.v2.model.component.Components;
import com.asyncapi.v2.model.schema.Schema;

import lombok.SneakyThrows;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public final class FieldTestUtil {

    public static void assertSchemaOnClass(Class<?> exampleClass, Schema fieldSchema) {

        Class<?> fieldType = getFieldType(exampleClass);

        var fieldSchemaHashCode = fieldSchema.hashCode();
        var schemaName = String.format("%s-%x", fieldType.getName(), fieldSchemaHashCode);
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

        // given
        var schemas = new HashMap<String, Object>();
        var messages = new HashMap<String, Object>();

        var components = new Components();
        components.setSchemas(schemas);
        components.setMessages(messages);

        AsyncAPI asyncAPI = new AsyncAPI();
        asyncAPI.setComponents(components);

        // then
        var messageReference = String.format("#/components/messages/%s", exampleClass.getName());
        var reference = MessageUtil.process(exampleClass, asyncAPI);

        assertThat(reference).usingRecursiveComparison().isEqualTo(new Reference(messageReference));

        var message = new Message();
        message.setPayload(new Reference(String.format("#/components/schemas/%s", exampleClass.getName())));
        message.setTitle(exampleClass.getName());

        Map<String, Object> expectedMessages = Map.of(exampleClass.getName(), message);
        assertThat(messages).usingRecursiveComparison().isEqualTo(expectedMessages);

        var fieldReferenceSchema = new Schema();
        fieldReferenceSchema.setRef(String.format("#/components/schemas/%s-%x", fieldType.getName(),
                fieldSchemaHashCode));

        var exampleSchema = new Schema();
        exampleSchema.setTitle("Example");
        exampleSchema.setProperties(Map.of("field", fieldReferenceSchema));

        Map<String, Object> expectedSchemas = new HashMap<>();
        expectedSchemas.put(exampleClass.getName(), exampleSchema);
        expectedSchemas.putAll(expectedSchemasForField);
        assertThat(schemas).usingRecursiveComparison().isEqualTo(expectedSchemas);
    }


    @SneakyThrows
    private static Class<?> getFieldType(Class<?> exampleClass) {

        return getAllFields(exampleClass).stream()
            .filter(f -> "field".equals(f.getName()))
            .findFirst()
            .map(Field::getType)
            .orElseThrow(() -> new NoSuchFieldException("field"));
    }


    private static List<Field> getAllFields(Class<?> type) {

        var result = new ArrayList<>(List.of(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            result.addAll(getAllFields(type.getSuperclass()));
        }

        return result;
    }
}
