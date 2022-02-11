package lu.greenhalos.j2asyncapi.schemas;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class Schema {

    private String $ref;
    private String title;
    private Map<String, ?> properties;
    private String type;
    private String format;
    private List<?> examples;
    private String description;
    private Object items;
    private List<Object> enumValue;

    public void set$ref(String $ref) {

        this.$ref = $ref;
    }


    public String get$ref() {

        return $ref;
    }


    public void setTitle(String title) {

        this.title = title;
    }


    public String getTitle() {

        return title;
    }


    public void setProperties(Map<String, ?> properties) {

        this.properties = properties;
    }


    public Map<String, ?> getProperties() {

        return properties;
    }


    public void setType(String type) {

        this.type = type;
    }


    public String getType() {

        return type;
    }


    public void setFormat(String format) {

        this.format = format;
    }


    public String getFormat() {

        return format;
    }


    public void setExamples(List<?> examples) {

        this.examples = examples;
    }


    public List<?> getExamples() {

        return examples;
    }


    public void setDescription(String description) {

        this.description = description;
    }


    public String getDescription() {

        return description;
    }


    public void setItems(Object items) {

        this.items = items;
    }


    public Object getItems() {

        return items;
    }


    public void setEnumValue(List<Object> enumValue) {

        this.enumValue = enumValue;
    }


    public List<Object> getEnumValue() {

        return enumValue;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Schema schema = (Schema) o;

        return Objects.equals($ref, schema.$ref) && Objects.equals(title, schema.title)
            && Objects.equals(properties, schema.properties) && Objects.equals(type, schema.type)
            && Objects.equals(format, schema.format) && Objects.equals(examples, schema.examples)
            && Objects.equals(description, schema.description) && Objects.equals(items, schema.items)
            && Objects.equals(enumValue, schema.enumValue);
    }


    @Override
    public int hashCode() {

        return Objects.hash($ref, title, properties, type, format, examples, description, items, enumValue);
    }


    @Override
    public String toString() {

        return "Schema{"
            + "$ref='" + $ref + '\''
            + ", title='" + title + '\''
            + ", properties=" + properties
            + ", type='" + type + '\''
            + ", format='" + format + '\''
            + ", examples=" + examples
            + ", description='" + description + '\''
            + ", items=" + items
            + ", enumValue=" + enumValue + '}';
    }
}
