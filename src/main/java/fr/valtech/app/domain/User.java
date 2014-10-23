package fr.valtech.app.domain;

/**
 * Created by cliff.maury on 23/10/2014.
 */
public class User {

    private String name;
    private String lastName;

    public User() {

    }

    public User(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, lastName);
    }
}
