package lu.greenhalos.j2asyncapi.core.fields;

import java.lang.reflect.Field;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class BooleanFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        return List.of(Boolean.class, boolean.class);
    }


    @Override
    public List<Object> getExamples(Field field) {

        return List.of(true, false);
    }


    @Override
    public String getType(Field field) {

        return "boolean";
    }


    @Override
    public String getFormat(Field field) {

        return null;
    }
}
