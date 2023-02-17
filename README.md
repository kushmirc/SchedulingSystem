# SchedulingSystem

### <em>Video Walkthrough: </em>https://youtu.be/P8vMjete4uo
<a href="https://youtu.be/P8vMjete4uo">
<img src="https://user-images.githubusercontent.com/107213928/219811122-a2a6f50a-838b-40e3-a973-f5610db1126e.png" alt="video walkthrough"></a>

## Summary:
This is a Jave program built using the IntelliJ IDE.  It is an application with a JavaFX user-interface for managing customers and appointments that may be used in a variety of organizations. It has a multiple language support feature and automatically converts timezones for use in any geographic location.

The Login class launches the JavaFX UI, and sets the stage to the Login Screen FXML file. The language of the text on the login screen supports English and French, and more language support could be added. A user must enter a valid username and password to login otherwise error messages will be displayed. After logging in the user may add, modify and delete customers and appointments. They may also filter appointments for those coming up within one week, or one month. There is a reports screen which provides the user with additional filter options to view appointments. 

The connection to the MySQL database is managed by the JDBC class, which is kept in the utilities package. Two functional interfaces which are used by lambda expressions are also kept in the utilities package.  

There are seven Controller classes, and five corresponding .fxml files foreach of the five JavaFX windows used in the user interface. Users can add, modify, and delete customers and appointments.  A customer can only be deleted if all associated appointments are deleted first, and a display notification gives the user the option to have this done. Notifications are also displayed when an attempt is made to schedule an appointment outside of established business hours or when overlapping with another appointment.  There are three class files.  One for customers, appointments, and users. A text file tracks all login attempts to the system. Resource bundle property files define the multi-language support.

This application was built with JavaFX SDK version 17.0.2 and JDK 17.0.4.  The IntelliJ JavaFX project generator was used with the Maven Build System selection when creating the project in IntelliJ.  Scene Builder was used for creating the UI layouts of the fxml files.
