package lu.greenhalos.j2asyncapi.core.fields;

import com.asyncapi.v2.model.schema.Schema;

import java.lang.reflect.Field;

import java.util.List;

import javax.annotation.Nullable;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
interface FieldType {

    List<Class<?>> getAllowedClasses();


    default boolean canHandle(Class<?> originalFieldType) {

        return this.getAllowedClasses().stream().anyMatch(c -> c == originalFieldType);
    }


    List<Object> getExamples(@Nullable Field field);


    String getType(@Nullable Field field);


    String getFormat(@Nullable Field field);


    default void handleAdditionally(@Nullable Field field, Schema fieldSchema) {
    }
}
