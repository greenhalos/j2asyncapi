package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.component.Components;

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
import java.util.TreeMap;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class Config {

    final List<FieldType> fieldTypes;
    public final AsyncAPI asyncAPI;

    private Config(Builder builder) {

        this.fieldTypes = List.copyOf(builder.fieldTypes);
        this.asyncAPI = builder.asyncAPI;

        var components = new Components();
        components.setMessages(new TreeMap<>(String::compareTo));
        components.setSchemas(new TreeMap<>(String::compareTo));

        // TODO check if there are no components yet
        this.asyncAPI.setChannels(new TreeMap<>(String::compareTo));
        this.asyncAPI.setComponents(components);
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
        private AsyncAPI asyncAPI;

        public Builder add(FieldType fieldType) {

            this.fieldTypes.add(fieldType);

            return this;
        }


        public Builder withAsyncApi(AsyncAPI asyncAPI) {

            this.asyncAPI = asyncAPI;

            return this;
        }


        public Config build() {

            if (this.asyncAPI == null) {
                this.asyncAPI = new AsyncAPI();
            }

            return new Config(this);
        }
    }
}
