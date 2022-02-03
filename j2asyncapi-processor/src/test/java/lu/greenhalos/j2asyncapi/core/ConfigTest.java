package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.AsyncAPI;

import lu.greenhalos.j2asyncapi.core.fields.FieldType;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import java.util.List;

import javax.annotation.Nullable;

import static org.assertj.core.api.Assertions.assertThat;


class ConfigTest {

    @Test
    void defaultConfig() {

        var sut = Config.defaultConfig();
        assertThat(sut.fieldTypes).hasSize(8);
        assertThat(sut.asyncAPI).isNotNull();
    }


    @Test
    void builder_empty() {

        var result = Config.builder().build();

        assertThat(result.fieldTypes).hasSize(8);
    }


    @Test
    void builder_addFieldType() {

        var result = Config.builder()
                .add(new ExampleFieldType())
                .build();

        assertThat(result.fieldTypes)
            .hasSize(9);
    }


    @Test
    void builder_asyncAPI() {

        var asyncAPI = new AsyncAPI();
        var result = Config.builder().withAsyncApi(asyncAPI).build();

        assertThat(result.asyncAPI).isSameAs(asyncAPI);
    }

    private static class ExampleFieldType implements FieldType {

        @Override
        public List<Class<?>> getAllowedClasses() {

            return null;
        }


        @Override
        public List<Object> getExamples(@Nullable Field field) {

            return null;
        }


        @Override
        public String getType(@Nullable Field field) {

            return null;
        }


        @Override
        public String getFormat(@Nullable Field field) {

            return null;
        }
    }
}
