package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.schema.Schema;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class NestedTest {

    @Test
    void testField() {

        var stringSchema = new Schema();
        stringSchema.setType("string");
        stringSchema.setFormat(null);
        stringSchema.setExamples(List.of("blah", "blub"));

        var nestedSchemaReference = new Schema();
        nestedSchemaReference.setRef("#/components/schemas/java.lang.String-216cb264");

        var nestedSchema = new Schema();
        nestedSchema.setTitle("Nested");
        nestedSchema.setProperties(Map.of("field2", nestedSchemaReference));

        var field2Reference = new Schema();
        field2Reference.setRef("#/components/schemas/lu.greenhalos.j2asyncapi.core.NestedTest$Nested");

        var fieldSchema = new Schema();
        fieldSchema.setTitle("Example");
        fieldSchema.setType(null);
        fieldSchema.setFormat(null);
        fieldSchema.setExamples(null);
        fieldSchema.setProperties(Map.of("field", field2Reference));

        var expectedSchemasForField = Map.of( //
                "java.lang.String-216cb264", stringSchema, //
                "lu.greenhalos.j2asyncapi.core.NestedTest$Example", fieldSchema, //
                "lu.greenhalos.j2asyncapi.core.NestedTest$Nested", nestedSchema //
                );

        FieldTestUtil.assertSchemaOnClass(Example.class, expectedSchemasForField, fieldSchema.hashCode());
    }

    private static class Example {

        private Nested field;
    }

    private static class Nested {

        private String field2;
    }
}
