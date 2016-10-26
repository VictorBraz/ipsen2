package Controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;

/**
 * Created by Roel on 12-10-2016.
 */
public class StudentController {
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private TextField birthDateTextField;
    private TextField phoneNumberTextField;
    private TextField emailAddressTextField;
    private TextField addressTextField;
    private TextField zipCodeTextField;
    private TextField cityTextField;
    private TextField studyTextField;

    private Button btnSubmit;
    private Button btnCancel;

    private String firstName;
    private String lastName;
    private String birthDate;
    private String phoneNumber;
    private String emailAddress;
    private String address;
    private String zipCode;
    private String city;
    private String study;

    private HashMap data;

    public void handleBtnSubmit(){
        firstName = firstNameTextField.getText();
        lastName = lastNameTextField.getText();
        birthDate = birthDateTextField.getText();
        phoneNumber = phoneNumberTextField.getText();
        emailAddress = emailAddressTextField.getText();
        address = addressTextField.getText();
        zipCode = zipCodeTextField.getText();
        city = cityTextField.getText();
        study = studyTextField.getText();

        data = new HashMap();

        data.put("firstName",firstName);
        data.put("lastName",lastName);
        data.put("birthDate",birthDate);
        data.put("phoneNumber",phoneNumber);
        data.put("emailAddress",emailAddress);
        data.put("address", address);
        data.put("zipCode", zipCode);
        data.put("city", city);
        data.put("study",study);
    }
}
