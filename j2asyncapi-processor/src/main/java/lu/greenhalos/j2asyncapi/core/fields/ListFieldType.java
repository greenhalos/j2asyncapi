package lu.greenhalos.j2asyncapi.core.fields;

import com.asyncapi.v2.model.AsyncAPI;
import com.asyncapi.v2.model.schema.Schema;

import lu.greenhalos.j2asyncapi.core.ClassUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import java.util.List;
import java.util.Set;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
class ListFieldType implements FieldType {

    @Override
    public List<Class<?>> getAllowedClasses() {

        return List.of(List.class, Set.class);
    }


    @Override
    public List<Object> getExamples(Field field) {

        return null;
    }


    @Override
    public String getType(Field field) {

        return "array";
    }


    @Override
    public String getFormat(Field field) {

        return null;
    }


    @Override
    public void handleAdditionally(Field field, Schema fieldSchema, AsyncAPI asyncAPI) {

        if (field == null) {
            return;
        }

        ParameterizedType listType = (ParameterizedType) field.getGenericType();
        Class<?> listItemClass = (Class<?>) listType.getActualTypeArguments()[0];

        var items = ClassUtil.process(listItemClass, asyncAPI);
        fieldSchema.setItems(items);
    }
}
