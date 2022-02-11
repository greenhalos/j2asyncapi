package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.schemas.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class LongPrimitiveTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setType("integer");
        fieldSchema.setFormat("int64");
        fieldSchema.setExamples(List.of(42, 352));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private long field;
    }
}
