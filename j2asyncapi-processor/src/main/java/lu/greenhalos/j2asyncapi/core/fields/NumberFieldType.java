package lu.greenhalos.j2asyncapi.core.fields;

import java.lang.reflect.Field;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class NumberFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        return List.of(Integer.class, Long.class, int.class, long.class);
    }


    @Override
    public List<Object> getExamples(Field field) {

        return List.of(42, 352);
    }


    @Override
    public String getType(Field field) {

        return "integer";
    }


    @Override
    public String getFormat(Field field) {

        if (field != null && (field.getType() == Long.class || field.getType() == long.class)) {
            return "int64";
        }

        return "int32";
    }
}
