package lu.greenhalos.j2asyncapi.schemas;

import java.util.Objects;


/**
 * @author  Ben Antony - antony@greenhalos.lu
 */
public class Message {

    private String title;
    private Reference payload;
    private String description;

    public void setTitle(String title) {

        this.title = title;
    }


    public String getTitle() {

        return title;
    }


    public void setPayload(Reference payload) {

        this.payload = payload;
    }


    public Reference getPayload() {

        return payload;
    }


    public void setDescription(String description) {

        this.description = description;
    }


    public String getDescription() {

        return description;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message message = (Message) o;

        return Objects.equals(title, message.title) && Objects.equals(payload, message.payload)
            && Objects.equals(description, message.description);
    }


    @Override
    public int hashCode() {

        return Objects.hash(title, payload, description);
    }


    @Override
    public String toString() {

        return "Message{"
            + "title='" + title + '\''
            + ", payload=" + payload
            + ", description='" + description + '\'' + '}';
    }
}
