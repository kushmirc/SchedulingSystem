# SchedulingSystem

### <em>Video Walkthrough: </em>https://youtu.be/P8vMjete4uo
<a href="https://youtu.be/YN203X8kfw0">
<img src="https://user-images.githubusercontent.com/107213928/219811122-a2a6f50a-838b-40e3-a973-f5610db1126e.png" alt="video walkthrough"></a>


## Summary:
This is a Jave program built using the IntelliJ IDE.  It is an application with a JavaFX user-interface for managing parts and products that may be used by a manufacturing company. The demonstration parts and products loaded in the Main method are for a pinball machine manufacturing company.

The main method launches the JavaFX UI, and sets the stage to the Main Screen FXML file. There are five Controller classes, and five corresponding .fxml files for
each of the five JavaFX windows. Users are able to add modify and delete part and products using buttons on the main screen. They can search for parts and products
using the search fields on the main screen. There are error handling messages presented when various invalid operations are performed by the user, as well as
confirmation dialogue boxes for deleting parts, products, and associated parts for products.  

There are five class files.  The inventory class houses the majority of the methods used for managing parts and products. Three classes, Parts, InHouse and Outsourced
are related to parts. Parts is an abstract class with two children, InHouse and Outsourced. The Products class handles all product related members.  
There are Javadocs provided in the InventorySystem directory which includes descriptions of all classes and methods. Please see the Javadocs for more detailed descriptions.  

This application was built with JavaFX SDK version 18.0.1 and JDK 17.  The IntelliJ JavaFX project generator was used with the Maven Build System selection when creating the project in IntelliJ.  Scene Builder was used for creating the UI layouts of the fxml files.
