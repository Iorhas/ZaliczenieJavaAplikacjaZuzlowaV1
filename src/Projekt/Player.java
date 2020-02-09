package Projekt;

import java.util.Collections;

public class Player {
    private String firstName;
    private String lastName;
    private int points;
    private String status;

    public Player(String firstName, String lastName, int points, String status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.points = points;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Player() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String toString() {
        return firstName + " " + lastName + " " + points + " " + status;
    }
}


