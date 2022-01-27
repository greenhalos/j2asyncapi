package lu.greenhalos.j2asyncapi.core.annotations;

import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.FieldTestUtil;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class FieldTypeTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType("integer");
        fieldSchema.setFormat("int32");
        fieldSchema.setExamples(List.of(42, 352));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        @AsyncApi.Field(type = Integer.class)
        private String field;
    }
}
