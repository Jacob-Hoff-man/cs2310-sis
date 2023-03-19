import java.util.ArrayList;
import java.util.List;

public class PersonalHealthcareSystem {
    public List<Message> messageResponseHistory;
    public boolean didCallPatient = false;
    public boolean didVisitPatient = false;
    // components
    public GestureRecognitionComponent grc;
    public EmergencyManagerComponent emc;
    public HomecareStaffComponent hsc;
    
    public PersonalHealthcareSystem() {
        // init
        this.messageResponseHistory = new ArrayList<Message>();
        this.grc = new GestureRecognitionComponent();
        this.emc = new EmergencyManagerComponent();
        this.hsc = new HomecareStaffComponent();
        // send register messages to components
        Message registerMessage = new Message(
            MessageType.REGISTER,
            ComponentName.SYSTEM,
            ComponentName.GESTURE_RECOGNITION
        );
        messageResponseHistory.add(this.grc.handleMessage(registerMessage));
        registerMessage.receiver = ComponentName.EMERGENCY_MANAGER;
        messageResponseHistory.add(this.emc.handleMessage(registerMessage));
        registerMessage.receiver = ComponentName.HOMECARE_STAFF;
        messageResponseHistory.add(this.hsc.handleMessage(registerMessage));
    }
    
    public void printMessageResponseHistory() {
        System.out.println("CURRENT messageResponseHistory VALUE:");
        for (int i = 0; i < messageResponseHistory.size(); i++) {
            Message m = messageResponseHistory.get(i);
            System.out.println("#" + i + ": " + m.toString());
        }
    }

    public void printDidCallPatient() {
        System.out.println("CURRENT didCallPatient VALUE: " + didCallPatient);
    }

    public void printDidVisitPatient() {
        System.out.println("CURRENT didVisitPatient VALUE: " + didVisitPatient);
    }

    public void printState() {
        printMessageResponseHistory();
        printDidCallPatient();
        printDidVisitPatient();
    }

    public List<Message> clearMessageResponseHistory() {
        List<Message> oldList = messageResponseHistory;
        messageResponseHistory.clear();
        return oldList;
    }

    // send a test message that causes an emergency to be detected by the gesture recognition component
    // this is mocked by the system, but could be replaced with a physical sensor... i.e. sender could be replaced
    // return the response message
    public Message mockGesture() {
        Message emergencyDetectedMessage = new Message(
            MessageType.EMERGENCY,
            ComponentName.SYSTEM,
            ComponentName.GESTURE_RECOGNITION
        );
        Message responseMessage = this.grc.handleMessage(emergencyDetectedMessage);
        messageResponseHistory.add(responseMessage);
        return responseMessage;
    }

    // run a mock scenario based on certain input params
    // inpMessage - the initial message of the scenario
    // didCallPatient - preset whether the scenario occurs before/after a patient has already been called
    // didVisitPatient - preset whether the scenario occurs before/after a patient has already been visited
    // patientAnswersPhone - preset whether or not the patient will answer a possible call from the healthcare staff
    public void runScenario(Message inpMessage, boolean didCallPatient, boolean didVisitPatient, boolean patientAnswersPhone) {
        this.didCallPatient = didCallPatient;
        this.didVisitPatient = didVisitPatient;

        Message responseMessage = inpMessage;
        if (responseMessage.receiver == ComponentName.SYSTEM) {
            // initial message is meant for SYSTEM component
            // handle didCallPatient state var
            if (responseMessage.messageType == MessageType.CALLING_PATIENT_SUCCESS) didCallPatient = true;
            // handle didVisitPatient state var
            if (responseMessage.messageType == MessageType.VISITING_PATIENT_SUCCESS) didVisitPatient = true;
            messageResponseHistory.add(responseMessage);

        } else {
            // initial message is not meant for SYSTEM component
            // final message to be sent in a scenario or failure message are the only time SYSTEM is receiver
            wloop: while (responseMessage.receiver != ComponentName.SYSTEM) {
                sw: switch(responseMessage.receiver) {
                    case GESTURE_RECOGNITION:
                        responseMessage = this.grc.handleMessage(responseMessage);
                        break sw;
                    case EMERGENCY_MANAGER:
                        if (responseMessage.messageType == MessageType.NEEDS_HELP)
                        responseMessage = this.emc.handleMessage(responseMessage, this.didCallPatient);
                        else responseMessage = this.emc.handleMessage(responseMessage);
                        break sw;
                    case HOMECARE_STAFF:
                        if (responseMessage.messageType == MessageType.CALL_PATIENT)
                        responseMessage = this.hsc.handleMessage(responseMessage, patientAnswersPhone);
                        else responseMessage = this.hsc.handleMessage(responseMessage);
                        break sw;
                    default:
                        break wloop;
                }
                // new message in responseMessage
                messageResponseHistory.add(responseMessage);
                // handle didCallPatient state var
                if (responseMessage.messageType == MessageType.CALLING_PATIENT_SUCCESS) this.didCallPatient = true;
                // handle didVisitPatient state var
                if (responseMessage.messageType == MessageType.VISITING_PATIENT_SUCCESS) this.didVisitPatient = true;
            }
        }
    }
    
    // the system has been initialized
    // a gesture is recognized, meaning assistance is requested
    // the patient has not been called or visited before
    // the patient does answer the call
    public void scenario1() {
        runScenario(
            mockGesture(),
            false,
            false,
            true
        );
    }

    // the system has been initialized
    // a gesture is recognized, meaning assistance is requested
    // the patient has not been called or visited before
    // the patient does not answer the call
    public void scenario2() {
        runScenario(
            mockGesture(),
            false,
            false,
            false
        );
    }

    // the system has been initialized
    // a gesture is recognized, meaning assistance is requested
    // the patient has been called before, so the staff member will visit
    // the patient does not answer the call because there is no call this time (false/true does not matter)
    public void scenario3() {
        runScenario(
            mockGesture(),
            true,
            false,
            false
        );
    }
}
