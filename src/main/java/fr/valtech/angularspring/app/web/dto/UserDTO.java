package fr.valtech.angularspring.app.web.dto;

public class UserDTO {

    private String name;
    private String lastName;

    public UserDTO(String name, String lastName) {
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