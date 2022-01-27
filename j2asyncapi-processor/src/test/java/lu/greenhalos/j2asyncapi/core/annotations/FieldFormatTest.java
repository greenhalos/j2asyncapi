package lu.greenhalos.j2asyncapi.core.annotations;

import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.FieldTestUtil;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class FieldFormatTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType("string");
        fieldSchema.setFormat("flapping");
        fieldSchema.setExamples(List.of("blah", "blub"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        @AsyncApi.Field(format = "flapping")
        private String field;
    }
}
