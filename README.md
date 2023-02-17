# SchedulingSystem

### <em>Video Walkthrough: </em>https://youtu.be/P8vMjete4uo
<a href="https://youtu.be/P8vMjete4uo">
<img src="https://user-images.githubusercontent.com/107213928/219811122-a2a6f50a-838b-40e3-a973-f5610db1126e.png" alt="video walkthrough"></a>

## Summary:
This is a Jave program built using the IntelliJ IDE.  It is an application with a JavaFX user-interface for managing customers and appointments that may be used in a variety of organizations. It has a multiple language support feature and automatically converts timezones for use in any geographic location.

The Login class launches the JavaFX UI, and sets the stage to the Login Screen FXML file. The language of the text on the login screen supports English and French, and more language support could be added. A user must enter a valid username and password to login otherwise error messages will be displayed. After logging in the user may add, modify and delete customers and appointments. They may also filter appointments for those coming up within one week, or one month. There is a reports screen which provides the user with additional filter options to view appointments. 

The connection to the MySQL database is managed by the JDBC class, which is kept in the utilities package. Two functional interfaces which are used by lambda expressions are also kept in the utilities package.  

There are five Controller classes, and five corresponding .fxml files for
each of the five JavaFX windows. Users are able to add modify and delete part and products using buttons on the main screen. They can search for parts and products
using the search fields on the main screen. There are error handling messages presented when various invalid operations are performed by the user, as well as
confirmation dialogue boxes for deleting parts, products, and associated parts for products.  

There are five class files.  The inventory class houses the majority of the methods used for managing parts and products. Three classes, Parts, InHouse and Outsourced
are related to parts. Parts is an abstract class with two children, InHouse and Outsourced. The Products class handles all product related members.  
There are Javadocs provided in the InventorySystem directory which includes descriptions of all classes and methods. Please see the Javadocs for more detailed descriptions.  

This application was built with JavaFX SDK version 18.0.1 and JDK 17.  The IntelliJ JavaFX project generator was used with the Maven Build System selection when creating the project in IntelliJ.  Scene Builder was used for creating the UI layouts of the fxml files.
