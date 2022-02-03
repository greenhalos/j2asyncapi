package lu.greenhalos.j2asyncapi.core.fields;

import java.lang.reflect.Field;

import java.math.BigDecimal;

import java.util.List;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class DecimalNumberFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        return List.of(Float.class, float.class, Double.class, double.class, BigDecimal.class);
    }


    @Override
    public List<Object> getExamples(Field field) {

        return List.of(42.42, 352.01);
    }


    @Override
    public String getType(Field field) {

        return "number";
    }


    @Override
    public String getFormat(Field field) {

        if (field != null && (field.getType() == Double.class || field.getType() == double.class)) {
            return "double";
        }

        return "float";
    }
}
