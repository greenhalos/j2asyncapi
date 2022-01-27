package lu.greenhalos.j2asyncapi.core.fields;

import java.lang.reflect.Field;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class StringFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        return List.of(String.class);
    }


    @Override
    public List<Object> getExamples(Field field) {

        return List.of("blah", "blub");
    }


    @Override
    public String getType(Field field) {

        return "string";
    }


    @Override
    public String getFormat(Field field) {

        return null;
    }
}
