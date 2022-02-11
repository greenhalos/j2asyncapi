package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.schemas.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class BooleanPrimitiveTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setType("boolean");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(List.of(true, false));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private boolean field;
    }
}
