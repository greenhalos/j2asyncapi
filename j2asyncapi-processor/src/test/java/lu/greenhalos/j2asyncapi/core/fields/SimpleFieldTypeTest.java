package lu.greenhalos.j2asyncapi.core.fields;

import org.junit.jupiter.api.Test;

import java.util.List;

import static lu.greenhalos.j2asyncapi.core.fields.SimpleFieldType.fieldType;

import static org.assertj.core.api.Assertions.assertThat;


class SimpleFieldTypeTest {

    @Test
    void fieldsGetSet_minimal() {

        var result = fieldType(Object.class)
                .build();

        assertThat(result.getAllowedClasses()).isEqualTo(List.of(Object.class));
        assertThat(result.getType(null)).isNull();
        assertThat(result.getExamples(null)).isNull();
        assertThat(result.getFormat(null)).isNull();
        assertThat(result.getDescription()).isNull();
    }


    @Test
    void fieldsGetSet() {

        var result = fieldType(Object.class)
                .type("string")
                .examples("Fooo", "Baaaa")
                .format("format")
                .description("description")
                .build();

        assertThat(result.getAllowedClasses()).isEqualTo(List.of(Object.class));
        assertThat(result.getType(null)).isEqualTo("string");
        assertThat(result.getExamples(null)).isEqualTo(List.of("Fooo", "Baaaa"));
        assertThat(result.getFormat(null)).isEqualTo("format");
        assertThat(result.getDescription()).isEqualTo("description");
    }


    @Test
    void fieldsGetSet_examples_asList() {

        var result = fieldType(Object.class)
                .type("string")
                .examples(List.of("Fooo", "Baaaa"))
                .format("format")
                .description("description")
                .build();

        assertThat(result.getAllowedClasses()).isEqualTo(List.of(Object.class));
        assertThat(result.getType(null)).isEqualTo("string");
        assertThat(result.getExamples(null)).isEqualTo(List.of("Fooo", "Baaaa"));
        assertThat(result.getFormat(null)).isEqualTo("format");
        assertThat(result.getDescription()).isEqualTo("description");
    }
}
