## How to Run
- Download the repo
- open the project's root directory using terminal/cmd and run the following command:
  - `java -jar App.jar`
- The program will prompt the user to select a program mode with a command line user interface.
  - Enter `0` to send any existing message between any existing components (specify sender and receiver components).
  - Enter `1` to perform pre-defined scenario 1:
    - A gesture is recognized by the gesture recognition component, which means assistance is being requested by the patient.
    - The patient has not been called or visited before this gesture was recognized.
    - The patient will answer any call that occurs during the scenario.
  - Enter `2` to perform pre-defined scenario 2:
    - A gesture is recognized by the gesture recognition component, which means assistance is being requested by the patient.
    - The patient has not been called or visited before this gesture was recognized.
    - The patient will not answer any call that occurs during the scenario.
  - Enter `3` to perform pre-defined scenario 3:
    - A gesture is recognized by the gesture recognition component, which means assistance is being requested by the patient.
    - The patient has already been called before this gesture was recognized, so they will be visited by a staff member.
    - The patient will not answer any call that occurs during the scenario because they will be visited instead.  
    
## README by VSCode Java    
Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
