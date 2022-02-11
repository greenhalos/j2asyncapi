package lu.greenhalos.j2asyncapi.core.fields;

import lu.greenhalos.j2asyncapi.core.Config;
import lu.greenhalos.j2asyncapi.schemas.Schema;

import java.lang.reflect.Field;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static lu.greenhalos.j2asyncapi.core.ClassNameUtil.name;

import static java.util.stream.Collectors.toList;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class EnumFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        throw new UnsupportedOperationException(name(EnumFieldType.class)
            + " handels canHandle(Class<?>) differently. getAllowedClasses is not implemented");
    }


    @Override
    public boolean canHandle(Class<?> originalFieldType) {

        return originalFieldType.isEnum();
    }


    @Override
    public List<Object> getExamples(Field field) {

        if (field == null) {
            return null;
        }

        return enumValues(field);
    }


    @Override
    public String getType(Field field) {

        return "string";
    }


    @Override
    public String getFormat(Field field) {

        return null;
    }


    @Override
    public void handleAdditionally(Field field, Schema fieldSchema, Config config) {

        if (field == null) {
            return;
        }

        fieldSchema.setEnumValue(enumValues(field));
    }


    private static List<Object> enumValues(Field field) {

        return Arrays.stream(field.getType().getEnumConstants())
            .map(Objects::toString)
            .collect(toList());
    }
}
