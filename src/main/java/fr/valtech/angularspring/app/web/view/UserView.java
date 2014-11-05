package fr.valtech.angularspring.app.web.view;

public class UserView {

    private String name;
    private String lastName;

    public UserView(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }
}