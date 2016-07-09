package mx.infotec.dads.insight.pdes.insight;

/**
 * Message Class used by InsightEngine
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Message {
    private MessageType messageType = MessageType.DEFAULT;
    private String text;

    private Message() {

    }

    public static Builder getBuilder() {
	return new Builder();
    }

    public MessageType getMessageType() {
	return messageType;
    }

    public void setMessageType(MessageType type) {
	this.messageType = type;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public static class Builder {
	Message message;
	StringBuilder sb = new StringBuilder();

	public Builder() {
	    message = new Message();
	}

	public Builder addMessageType(MessageType messageType) {
	    message.setMessageType(messageType);
	    return this;
	}

	public Builder addText(String text) {
	    sb.append(text);
	    return this;
	}

	public Builder addnewLine() {
	    sb.append(System.getProperty("line.separator"));
	    return this;
	}

	public Message build() {
	    message.setText(sb.toString());
	    return message;
	}
    }
}
