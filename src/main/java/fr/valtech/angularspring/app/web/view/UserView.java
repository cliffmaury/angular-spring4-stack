package fr.valtech.angularspring.app.web.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserView {

    @Size(min = 0, max = 50)
    @NotNull
    private String name;
    @Size(min = 0, max = 50)
    @NotNull
    private String lastName;

    public UserView() {
    }

    public UserView(String name, String lastName) {
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
}