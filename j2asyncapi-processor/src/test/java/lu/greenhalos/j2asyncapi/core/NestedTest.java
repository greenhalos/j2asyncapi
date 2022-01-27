package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class NestedTest {

    @Test
    void testField() {

        var field2Schema = new Schema();
        field2Schema.setTitle("field2");
        field2Schema.setType("string");
        field2Schema.setFormat(null);
        field2Schema.setExamples(List.of("blah", "blub"));

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType(null);
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(null);
        fieldSchema.setProperties(Map.of("field2", field2Schema));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private Nested field;
    }

    private static class Nested {

        private String field2;
    }
}
