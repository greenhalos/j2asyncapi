package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.schemas.Reference;
import lu.greenhalos.j2asyncapi.schemas.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class CollectionSetTest {

    @Test
    void testField() {

        var stringSchema = new Schema();
        stringSchema.setType("string");
        stringSchema.setFormat(null);
        stringSchema.setExamples(List.of("Lorem", "ipsum"));

        var itemSchema = new Reference("#/components/schemas/j.l.String-714f7ea0");
        var fieldSchema = new Schema();
        fieldSchema.setType("array");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(null);
        fieldSchema.setItems(itemSchema);

        var expectedSchemasForField = Map.of( //
                "j.l.String-714f7ea0", stringSchema, //
                "j.u.Set-b1afaed2", fieldSchema //
                );

        FieldTestUtil.assertSchemaOnClass(Example.class, expectedSchemasForField, fieldSchema.hashCode());
    }

    private static class Example {

        private Set<String> field;
    }
}
