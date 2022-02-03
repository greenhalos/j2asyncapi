package lu.greenhalos.j2asyncapi.core.fields;

import java.lang.reflect.Field;

import java.time.LocalDate;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class DateFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        return List.of(LocalDate.class);
    }


    @Override
    public List<Object> getExamples(Field field) {

        return List.of("2022-01-31", "1985-04-12");
    }


    @Override
    public String getType(Field field) {

        return "string";
    }


    @Override
    public String getFormat(Field field) {

        return "date";
    }
}
