package lu.greenhalos.j2asyncapi.core.annotations;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.FieldTestUtil;
import lu.greenhalos.j2asyncapi.schemas.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class FieldNothingSetTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setType("string");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(List.of("Lorem", "ipsum"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        @AsyncApi.Field
        private String field;
    }
}
