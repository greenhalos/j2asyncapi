package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class CollectionSetTest {

    @Test
    void testField() {

        var itemSchema = new Schema();
        itemSchema.setType("string");
        itemSchema.setFormat(null);
        itemSchema.setExamples(List.of("blah", "blub"));

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType("array");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(null);
        fieldSchema.setItems(itemSchema);

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private Set<String> field;
    }
}
