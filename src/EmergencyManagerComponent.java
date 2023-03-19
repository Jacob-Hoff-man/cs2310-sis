public class EmergencyManagerComponent implements Component {
    public ComponentName name;

    public EmergencyManagerComponent() {
        this.name = ComponentName.EMERGENCY_MANAGER;
    }

    public Message handleMessage(Message inpMessage) {
        switch (inpMessage.messageType) {
            case REGISTER:
                return new Message(
                    MessageType.REGISTER_SUCCESS,
                    ComponentName.EMERGENCY_MANAGER,
                    ComponentName.SYSTEM
                );
            case CALLING_PATIENT_FAILURE:
                return new Message(
                    MessageType.VISIT_PATIENT,
                    ComponentName.EMERGENCY_MANAGER,
                    ComponentName.HOMECARE_STAFF
                );
            default:
                return new Message(
                    MessageType.FAILURE,
                    ComponentName.EMERGENCY_MANAGER,
                    ComponentName.SYSTEM
                );
        }
    }

    public Message handleMessage(Message inpMessage, boolean didCallPatient) {
        switch (inpMessage.messageType) {
            case NEEDS_HELP:
                if (didCallPatient) return new Message(
                    MessageType.VISIT_PATIENT,
                    ComponentName.EMERGENCY_MANAGER,
                    ComponentName.HOMECARE_STAFF
                );
                else return new Message(
                    MessageType.CALL_PATIENT,
                    ComponentName.EMERGENCY_MANAGER,
                    ComponentName.HOMECARE_STAFF
                );
            default:
                return new Message(
                    MessageType.FAILURE,
                    ComponentName.EMERGENCY_MANAGER,
                    ComponentName.SYSTEM
                );
        }
    }
}
