package lu.greenhalos.j2asyncapi.core.annotations;

import lu.greenhalos.j2asyncapi.annotations.AsyncApi;
import lu.greenhalos.j2asyncapi.core.Config;
import lu.greenhalos.j2asyncapi.core.MessageUtil;
import lu.greenhalos.j2asyncapi.schemas.Message;

import org.junit.jupiter.api.Test;

import static lu.greenhalos.j2asyncapi.core.ClassNameUtil.name;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class MessageDescriptionTest {

    @Test
    void testDescription() {

        var config = Config.defaultConfig();
        MessageUtil.process(ExampleMessage.class, config);

        var messages = config.asyncAPI.getComponents().getMessages().getAdditionalProperties();
        assertThat(messages).hasSize(1);

        var message = (Message) messages.get(name(ExampleMessage.class));
        assertThat(message.getDescription()).isEqualTo("fancy description");
    }

    @AsyncApi.Message(description = "fancy description")
    private static class ExampleMessage {

        private String field;
    }
}
