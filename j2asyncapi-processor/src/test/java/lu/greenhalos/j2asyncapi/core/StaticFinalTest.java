package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class StaticFinalTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setType("string");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(List.of("Lorem", "ipsum"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private static final String FIELD_TO_IGNORE = "ignoreMe";

        private String field;
    }
}
