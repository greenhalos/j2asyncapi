package lu.greenhalos.j2asyncapi.core;

import com.asyncapi.v2.model.channel.message.Message;
import com.asyncapi.v2.model.schema.Schema;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public final class FieldTestUtil {

    public static void assertSchemaOnClass(Class<?> exampleClass, Schema fieldSchema) {

        var result = MessageUtil.process(exampleClass);

        var expectedSchema = new Schema();
        expectedSchema.setProperties(Map.of("field", fieldSchema));

        var expected = new Message();
        expected.setTitle(exampleClass.getName());
        expected.setPayload(expectedSchema);

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
