package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.schemas.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class ClassExtendsTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setType("string");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(List.of("Lorem", "ipsum"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example extends BaseExample {
    }

    private static class BaseExample {

        private String field;
    }
}
