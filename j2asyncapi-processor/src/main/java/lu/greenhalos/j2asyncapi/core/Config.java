package lu.greenhalos.j2asyncapi.core;

import lu.greenhalos.j2asyncapi.core.fields.BooleanFieldType;
import lu.greenhalos.j2asyncapi.core.fields.DateFieldType;
import lu.greenhalos.j2asyncapi.core.fields.DateTimeFieldType;
import lu.greenhalos.j2asyncapi.core.fields.DecimalNumberFieldType;
import lu.greenhalos.j2asyncapi.core.fields.EnumFieldType;
import lu.greenhalos.j2asyncapi.core.fields.FieldType;
import lu.greenhalos.j2asyncapi.core.fields.ListFieldType;
import lu.greenhalos.j2asyncapi.core.fields.NumberFieldType;
import lu.greenhalos.j2asyncapi.core.fields.StringFieldType;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class Config {

    final List<FieldType> fieldTypes;

    private Config(Builder builder) {

        this.fieldTypes = List.copyOf(builder.fieldTypes);
    }

    public static Config defaultConfig() {

        return Config.builder().build();
    }


    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {

        private static final List<FieldType> DEFAULT_FIELD_TYPES = List.of(new NumberFieldType(),
                new StringFieldType(), new BooleanFieldType(), new DecimalNumberFieldType(), new ListFieldType(),
                new EnumFieldType(), new DateFieldType(), new DateTimeFieldType());

        private final List<FieldType> fieldTypes = new ArrayList<>(DEFAULT_FIELD_TYPES);

        public Builder addFieldType(FieldType fieldType) {

            this.fieldTypes.add(fieldType);

            return this;
        }


        public Config build() {

            return new Config(this);
        }
    }
}
