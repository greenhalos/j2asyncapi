package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class BigDecimalTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setType("number");
        fieldSchema.setFormat("float");
        fieldSchema.setExamples(List.of(42.42, 352.01));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private BigDecimal field;
    }
}
