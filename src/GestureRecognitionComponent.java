public class GestureRecognitionComponent implements Component {
    public ComponentName name;

    public GestureRecognitionComponent() {
        this.name = ComponentName.GESTURE_RECOGNITION;
    }

    public Message handleMessage(Message inpMessage) {
        switch (inpMessage.messageType) {
            case REGISTER:
                return new Message(
                    MessageType.REGISTER_SUCCESS,
                    ComponentName.GESTURE_RECOGNITION,
                    ComponentName.SYSTEM
                );
            case EMERGENCY:
                return new Message(
                    MessageType.NEEDS_HELP,
                    ComponentName.GESTURE_RECOGNITION,
                    ComponentName.EMERGENCY_MANAGER);
            default:
                return new Message(
                    MessageType.FAILURE,
                    ComponentName.GESTURE_RECOGNITION,
                    ComponentName.SYSTEM
                );
        }
    }
}
