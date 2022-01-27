package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class FinalStringTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType("string");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(List.of("blah", "blub"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private final String field;

        private Example(String field) {

            this.field = field;
        }
    }
}
