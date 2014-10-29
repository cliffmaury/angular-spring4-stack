package fr.valtech.angularspring.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by cliff.maury on 23/10/2014.
 */
@Entity
public class User extends BaseEntity {

    @Column(length = 50)
    private String name;

    @Column(length = 50)
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s %s", name, lastName);
    }
}
