package lu.greenhalos.j2asyncapi.core.fields;

import lu.greenhalos.j2asyncapi.core.Config;
import lu.greenhalos.j2asyncapi.schemas.Schema;

import java.lang.reflect.Field;

import java.util.List;

import javax.annotation.Nullable;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public interface FieldType {

    List<Class<?>> getAllowedClasses();


    default boolean canHandle(Class<?> originalFieldType) {

        return this.getAllowedClasses().stream().anyMatch(c -> c == originalFieldType);
    }


    List<Object> getExamples(@Nullable Field field);


    String getType(@Nullable Field field);


    String getFormat(@Nullable Field field);


    default void handleAdditionally(@Nullable Field field, Schema fieldSchema, Config config) {
    }


    default String getDescription() {

        return null;
    }
}
