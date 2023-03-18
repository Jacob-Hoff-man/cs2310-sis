public class Message {
    public MessageType messageType;
    public ComponentName sender;
    public ComponentName receiver;

    public Message(MessageType messageType, ComponentName sender, ComponentName receiver) {
        this.messageType = messageType;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "{" +
            " messageType='" + messageType + "'" +
            ", sender='" + sender + "'" +
            ", receiver='" + receiver + "'" +
            "}";
    }

}
