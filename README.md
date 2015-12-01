# Workshop-2
Workshop 2 Member Registry 


1: Getting started
Requirements: The project is created as a Maven project in Eclipse and to run it you will need to install Eclipse (https://www.eclipse.org/). In Eclipse go to Help → Marketplace and download the tools for JavaFX in Eclipse (E(fx)eclipse and the  Maven plugin.
The framework Hibernate is needed and is available here: (http://hibernate.org/).
The database is running with MySQL.
Several external dependencies are included and can be found here: https://github.com/rillan/Workshop-2/tree/master/Workshop 2/src/lib


The username and password to enter the Editmode is ”user”.

2: Running the application

Run the main class found in the view package.

The application has two modes ”Edit” and ”View”. Upon starting the application you get the choice of logging in or just use the simple view mode.
After that you will be put to the main application and the options you get to choice from there will depend on if you have logged in or not(The view mode will show less information than the Edit mode).

Inside the application there will be several buttons who are connected to different FXML-files that will load upon selection e.g the button for Create Member will send you to the scene for creating a member.

The controller is done by three classes: the Main class, the BoatController and the MemberController. The main class handles the controll for selecting the view, the others handle 
