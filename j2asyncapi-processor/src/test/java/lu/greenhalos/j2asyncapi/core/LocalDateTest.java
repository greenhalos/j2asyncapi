package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class LocalDateTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType("string");
        fieldSchema.setFormat("date");
        fieldSchema.setExamples(List.of("2022-01-31", "1985-04-12"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private LocalDate field;
    }
}
