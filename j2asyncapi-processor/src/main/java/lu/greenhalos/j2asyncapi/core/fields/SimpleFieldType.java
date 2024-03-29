package lu.greenhalos.j2asyncapi.core.fields;

import java.lang.reflect.Field;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class SimpleFieldType implements FieldType {

    private final List<Class<?>> allowedClass;
    private final List<Object> examples;
    private final String type;
    private final String format;
    private final String description;

    private SimpleFieldType(Builder builder) {

        this.allowedClass = List.of(builder.allowedClass);
        this.examples = Optional.ofNullable(builder.examples).map(List::copyOf).orElse(null);
        this.type = builder.type;
        this.format = builder.format;
        this.description = builder.description;
    }

    @Override
    public List<Class<?>> getAllowedClasses() {

        return this.allowedClass;
    }


    @Override
    public List<Object> getExamples(@Nullable Field field) {

        return this.examples;
    }


    @Override
    public String getType(@Nullable Field field) {

        return this.type;
    }


    @Override
    public String getFormat(@Nullable Field field) {

        return this.format;
    }


    @Override
    public String getDescription() {

        return this.description;
    }


    public static Builder fieldType(Class<?> allowedClass) {

        return new Builder(allowedClass);
    }

    public static class Builder {

        private final Class<?> allowedClass;
        private List<Object> examples;
        private String type;
        private String format;
        private String description;

        public Builder(Class<?> allowedClass) {

            this.allowedClass = allowedClass;
        }

        public Builder examples(Object... examples) {

            this.examples = List.of(examples);

            return this;
        }


        public Builder examples(List<Object> examples) {

            this.examples = examples;

            return this;
        }


        public Builder type(String type) {

            this.type = type;

            return this;
        }


        public Builder description(String description) {

            this.description = description;

            return this;
        }


        public Builder format(String format) {

            this.format = format;

            return this;
        }


        public SimpleFieldType build() {

            return new SimpleFieldType(this);
        }
    }
}
