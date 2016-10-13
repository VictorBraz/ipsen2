package Controller;

import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Bernd on 12-10-2016.
 */
public class ClientController {
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField birthDateTextField;
    private TextField emailAddressTextField;
    private TextField telephoneNumberTextField;
    private TextField addressTextField;
    private TextField houseNumberTextField;
    private TextField zipCodeTextField;
    private TextField cityTextField;
    private TextField studyTextField;

    private Pane cancelButton, submitButton;

    private String firstName;
    private String lastName;
    private String birthDate;
    private String emailAddress;
    private String telephoneNumber;
    private String study;
    private String address;
    private String zipCode;
    private String city;

    private HashMap data;

    public void handleSubmitButton() {
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        birthDate =  birthDateTextField.getText();
        emailAddress = emailAddressTextField.getText();
        telephoneNumber = telephoneNumberTextField.getText();
        study = studyTextField.getText();
        address = addressTextField.getText();
        zipCode = zipCodeTextField.getText();
        city = cityTextField.getText();

        data = new HashMap();

        data.put("firstname", firstName);
        data.put("lastname", lastName);
        data.put("birthdate", birthDate);
        data.put("emailaddress", emailAddress);
        data.put("telephonenumber", telephoneNumber);
        data.put("study", study);
        data.put("address", address);
        data.put("zipcode", zipCode);
        data.put("city", city);

    }

}
