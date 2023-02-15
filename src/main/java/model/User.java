package model;

/** Class User is used to create user instances.
 * It contains a user constructor method as well as getters for the user name and password.
 * @author Kush Mirchandani*/
public class User {
    private int id;
    private String name;
    private String password;

/** This is the User constructor method.
 * This method is used to create User instances.
 * @param id the id of the new User
 * @param name the name of the new User
 * @param password the password of the new User */
    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
