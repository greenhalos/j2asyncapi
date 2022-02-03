package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class CollectionListTest {

    @Test
    void testField() {

        var stringSchema = new Schema();
        stringSchema.setType("string");
        stringSchema.setFormat(null);
        stringSchema.setExamples(List.of("Lorem", "ipsum"));

        var itemSchema = new Reference("#/components/schemas/java.lang.String-38af4fe9");
        var fieldSchema = new Schema();
        fieldSchema.setType("array");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(null);
        fieldSchema.setItems(itemSchema);

        var expectedSchemasForField = Map.of( //
                "java.lang.String-38af4fe9", stringSchema, //
                "java.util.List-33eeac26", fieldSchema //
                );

        FieldTestUtil.assertSchemaOnClass(Example.class, expectedSchemasForField, fieldSchema.hashCode());
    }

    private static class Example {

        private List<String> field;
    }
}
