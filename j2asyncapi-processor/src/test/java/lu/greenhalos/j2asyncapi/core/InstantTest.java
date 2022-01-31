package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class InstantTest {

    @Test
    void testField() {

        var fieldSchema = new Schema();
        fieldSchema.setTitle("field");
        fieldSchema.setType("string");
        fieldSchema.setFormat("date-time");
        fieldSchema.setExamples(List.of("2022-01-31T23:20:50.52Z", "1985-04-12T15:59:55-08:00"));

        FieldTestUtil.assertSchemaOnClass(Example.class, fieldSchema);
    }

    private static class Example {

        private Instant field;
    }
}
