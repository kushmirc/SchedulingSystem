package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/** Class Appointment is used to create appointment instances.
 * It contains an appointment constructor method as well as getters and setters for each instance variable.
 * @author Kush Mirchandani*/
public class Appointment {

    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;
    private int contactId;

    /** This is the Appointment constructor method.
     * This method is used to create Appointment instances.
     * @param id the id of the new Appointment
     * @param title the title of the new Appointment
     * @param description the description of the new Appointment
     * @param location the location of the new Appointment
     * @param type the type of the new Appointment
     * @param start the start date and time of the new Appointment
     * @param end the end date and time of the new Appointment
     * @param customerId the customerId of the new Appointment
     * @param userId the userId of the new Appointment
     * @param contactId the contactId of the new Appointment */
    public Appointment(int id, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the start date and time
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * @param start the start date and time to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * @return the end date and time
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * @param end the end date and time to set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the contactId
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * @param contactId the contactId to set
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
}
