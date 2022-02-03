package lu.greenhalos.j2asyncapi.core.fields;

import java.lang.reflect.Field;

import java.time.Instant;
import java.time.LocalDateTime;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class DateTimeFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        return List.of(LocalDateTime.class, Instant.class);
    }


    @Override
    public List<Object> getExamples(Field field) {

        return List.of("2022-01-31T23:20:50.52Z", "1985-04-12T15:59:55-08:00");
    }


    @Override
    public String getType(Field field) {

        return "string";
    }


    @Override
    public String getFormat(Field field) {

        return "date-time";
    }
}
