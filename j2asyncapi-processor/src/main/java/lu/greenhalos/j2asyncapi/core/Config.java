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
import lu.greenhalos.j2asyncapi.schemas.AsyncApiDocumentRoot;
import lu.greenhalos.j2asyncapi.schemas.Channels;
import lu.greenhalos.j2asyncapi.schemas.Components;
import lu.greenhalos.j2asyncapi.schemas.Messages;
import lu.greenhalos.j2asyncapi.schemas.Schemas;

import java.util.ArrayList;
import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class Config {

    final List<FieldType> fieldTypes;
    public final AsyncApiDocumentRoot asyncAPI;

    private Config(Builder builder) {

        this.fieldTypes = List.copyOf(builder.fieldTypes);
        this.asyncAPI = builder.asyncAPI;

        var components = new Components();

        components.setMessages(new Messages());
        components.setSchemas(new Schemas());

        // TODO check if there are no components yet
        this.asyncAPI.setChannels(new Channels());
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
        private AsyncApiDocumentRoot asyncAPI;

        public Builder add(FieldType fieldType) {

            this.fieldTypes.add(fieldType);

            return this;
        }


        public Builder withAsyncApi(AsyncApiDocumentRoot asyncAPI) {

            this.asyncAPI = asyncAPI;

            return this;
        }


        public Config build() {

            if (this.asyncAPI == null) {
                this.asyncAPI = new AsyncApiDocumentRoot();
            }

            return new Config(this);
        }
    }
}
