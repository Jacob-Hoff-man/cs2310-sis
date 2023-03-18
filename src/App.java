import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    protected static final String [] modes = {"Any Input Message", "Scenario 1", "Scenario 2", "Scenario 3"};
    protected static final String intRegex = "^[0-9][0-9]{0,2}(?:,[0-9]{3}){0,3}$";
    public static boolean stringIsValidIntValue(String inputStr) {
        return inputStr.matches(intRegex);
    }

    public static Message generateMessage(MessageType inpMessageType, ComponentName inpSender, ComponentName inpReceiver) {
        Message retMessage = new Message(
            inpMessageType,
            inpSender,
            inpReceiver
        );
        return retMessage;
    }

    public static Message getInputMessage() {
        // get user input from system.in
        Scanner myScanner = new Scanner(System.in);
        System.out.println("----------");
        List<MessageType> messageTypes = Arrays.asList(MessageType.class.getEnumConstants());
        for (int i = 0; i < messageTypes.size(); i++) {
            System.out.println("# " + i + " : " + messageTypes.get(i));
        }
        System.out.println("Enter Number For Desired Message Type:");
        String messageType = myScanner.nextLine();
        while(
            !stringIsValidIntValue(messageType) ||
            Integer.parseInt(messageType) > (messageTypes.size() - 1) ||
            Integer.parseInt(messageType) < 0
        ) {
            System.out.println("Enter Number For Desired Message Type (Integer value between 0-" + (messageTypes.size() - 1) + " only):");
            messageType = myScanner.nextLine();
        }

        System.out.println("----------");
        List<ComponentName> compNames = Arrays.asList(ComponentName.class.getEnumConstants());
        for (int i = 0; i < compNames.size(); i++) {
            System.out.println("# " + i + " : " + compNames.get(i));
        }
        System.out.println("Enter Number For Desired Sender Component Name:");
        String sender = myScanner.nextLine();
        while(
            !stringIsValidIntValue(sender) ||
            Integer.parseInt(sender) > (compNames.size() - 1) ||
            Integer.parseInt(sender) < 0
        ) {
            System.out.println("Enter Number For Desired Sender Component Name (Integer value between 0-" + (compNames.size() - 1) + " only):");
            sender = myScanner.nextLine();
        }

        System.out.println("----------");
        for (int i = 0; i < compNames.size(); i++) {
            System.out.println("# " + i + " : " + compNames.get(i));
        }
        System.out.println("Enter Number For Desired Receiver Component Name:");
        String receiver = myScanner.nextLine();
        while(
            !stringIsValidIntValue(receiver) ||
            Integer.parseInt(receiver) > (compNames.size() - 1) ||
            Integer.parseInt(receiver) < 0
        ) {
            System.out.println("Enter Number For Desired Receiver Component Name (Integer value between 0-" + (compNames.size() - 1) + " only):");
            receiver = myScanner.nextLine();
        }
        // build and return message obj
        return generateMessage(
            messageTypes.get(Integer.parseInt(messageType)),
            compNames.get(Integer.parseInt(sender)),
            compNames.get(Integer.parseInt(receiver))
        );
    }

    public static boolean getInputDidCallPatient() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("----------");
        System.out.println("Has the patient already been called in this scenario (enter 0 for false or 1 for true):");
        String ret = myScanner.nextLine();
        while(
            !stringIsValidIntValue(ret) ||
            Integer.parseInt(ret) > 1 ||
            Integer.parseInt(ret) < 0
        ) {
            System.out.println("enter 0 for false or 1 for true:");
            ret = myScanner.nextLine();
        }
        if (Integer.parseInt(ret) == 1) return true;
        return false;
    }

    public static boolean getInputDidVisitPatient() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("----------");
        System.out.println("Has the patient already been visited in this scenario (enter 0 for false or 1 for true):");
        String ret = myScanner.nextLine();
        while(
            !stringIsValidIntValue(ret) ||
            Integer.parseInt(ret) > 1 ||
            Integer.parseInt(ret) < 0
        ) {
            System.out.println("enter 0 for false or 1 for true:");
            ret = myScanner.nextLine();
        }
        if (Integer.parseInt(ret) == 1) return true;
        return false;
    }

    public static boolean getInputPatientAnswersPhone() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("----------");
        System.out.println("Does the patient pick up a phone call (if any) in this scenario (enter 0 for false or 1 for true):");
        String ret = myScanner.nextLine();
        while(
            !stringIsValidIntValue(ret) ||
            Integer.parseInt(ret) > 1 ||
            Integer.parseInt(ret) < 0
        ) {
            System.out.println("enter 0 for false or 1 for true:");
            ret = myScanner.nextLine();
        }
        if (Integer.parseInt(ret) == 1) return true;
        return false;
    }

    public static int handleInputMode() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("----------");
        for (int i = 0; i < modes.length; i++) {
            System.out.println("# " + i + " : " + modes[i]);
        }
        System.out.println("Enter Number For Desired Mode:");
        String ret = myScanner.nextLine();
        while(
            !stringIsValidIntValue(ret) ||
            Integer.parseInt(ret) > (modes.length - 1) ||
            Integer.parseInt(ret) < 0
        ) {
            System.out.println("Enter Number For Desired Mode (Integer value between 0-" + (modes.length - 1) + " only):");
            ret = myScanner.nextLine();
        }
        return Integer.parseInt(ret);
    }

    public static void performInputAnyMsgMode() {
        Message inpMsg = getInputMessage();
        boolean inpDidCallPatient = getInputDidCallPatient();
        boolean inpDidVisitPatient = getInputDidVisitPatient();
        boolean inpPatientAnswersPhone = getInputPatientAnswersPhone();

        PersonalHealthcareSystem phs = new PersonalHealthcareSystem();
        phs.runScenario(inpMsg, inpDidCallPatient, inpDidVisitPatient, inpPatientAnswersPhone);
        phs.printMessageResponseHistory();
    }

    public static void performScenario1() {
        PersonalHealthcareSystem phs = new PersonalHealthcareSystem();
        phs.scenario1();
        phs.printMessageResponseHistory();
    }

    public static void performScenario2() {
        PersonalHealthcareSystem phs = new PersonalHealthcareSystem();
        phs.scenario2();
        phs.printMessageResponseHistory();
    }

    public static void performScenario3() {
        PersonalHealthcareSystem phs = new PersonalHealthcareSystem();
        phs.scenario3();
        phs.printMessageResponseHistory();
    }

    public static void main(String[] args) throws Exception {
        int modeVal = handleInputMode();
        switch (modeVal) {
            case 0:
                performInputAnyMsgMode();
                break;
            case 1:
                performScenario1();
                break;
            case 2:
                performScenario2();
                break;
            case 3:
                performScenario3();
                break;
            default:
                break;
        }
    }
}
