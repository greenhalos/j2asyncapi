package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.Reference;
import com.asyncapi.v2.model.schema.Schema;

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
        stringSchema.setExamples(List.of("blah", "blub"));

        var itemSchema = new Reference("#/components/schemas/java.lang.String-216cb264");
        var fieldSchema = new Schema();
        fieldSchema.setType("array");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(null);
        fieldSchema.setItems(itemSchema);

        var expectedSchemasForField = Map.of( //
                "java.lang.String-216cb264", stringSchema, //
                "java.util.Set-39a15bb0", fieldSchema //
                );

        FieldTestUtil.assertSchemaOnClass(Example.class, expectedSchemasForField, fieldSchema.hashCode());
    }

    private static class Example {

        private Set<String> field;
    }
}
