package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class EnumTest {

    private enum ExampleEnum {

        VALUE_1,
        VALUE_2
    }

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType("string");
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(List.of("VALUE_1", "VALUE_2"));
        fieldSchema.setEnumValue(List.of("VALUE_1", "VALUE_2"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private ExampleEnum field;
    }
}
