package lu.greenhalos.j2asyncapi.core.annotations;

import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.FieldTestUtil;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class FieldTypeTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setType("integer");
        fieldSchema.setFormat("int32");
        fieldSchema.setExamples(List.of(42, 352));

        var expectedSchemasForField = Map.of("j.l.Integer-decfea64", fieldSchema);

        FieldTestUtil.assertSchemaOnClass(Example.class, expectedSchemasForField, Integer.class,
            fieldSchema.hashCode());
    }

    private static class Example {

        @AsyncApi.Field(type = Integer.class)
        private String field;
    }
}
