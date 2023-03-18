public class HomecareStaffComponent implements Component {

    public Message handleMessage(Message inpMessage) {
        switch (inpMessage.messageType) {
            case REGISTER:
                return new Message(
                    MessageType.REGISTER_SUCCESS,
                    ComponentName.HOMECARE_STAFF,
                    ComponentName.SYSTEM
                );
            case VISIT_PATIENT:
                return new Message(
                    MessageType.VISITING_PATIENT_SUCCESS,
                    ComponentName.HOMECARE_STAFF,
                    ComponentName.SYSTEM
                );
            default:
                return new Message(
                    MessageType.FAILURE,
                    ComponentName.HOMECARE_STAFF,
                    ComponentName.SYSTEM
                );
        }
    }

    public Message handleMessage(Message inpMessage, boolean doesAnswerCall) {
        switch (inpMessage.messageType) {
            case CALL_PATIENT:
                if (doesAnswerCall) return new Message(
                    MessageType.CALLING_PATIENT_SUCCESS,
                    ComponentName.HOMECARE_STAFF,
                    ComponentName.SYSTEM
                );
                else return new Message(
                    MessageType.CALLING_PATIENT_FAILURE,
                    ComponentName.HOMECARE_STAFF,
                    ComponentName.EMERGENCY_MANAGER
                );
            default:
                return new Message(
                    MessageType.FAILURE,
                    ComponentName.HOMECARE_STAFF,
                    ComponentName.SYSTEM
                );
        }
    }
    
}
