package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.databaseConnection.StaffQueries;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {
    @FXML
    private TextField dOb;
    @FXML
    private ChoiceBox<String> role;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private TextField ssn;
    @FXML
    private TextField email;

    private SwitchScene sc = new SwitchScene();

    public void add(ActionEvent actionEvent) throws FileNotFoundException {
        try {
            String firstNameO = firstName.getText();
            String lastNameO = lastName.getText();
            String addressO = address.getText();
            String SSN = ssn.getText();
            String dob = dOb.getText();
            String Email = email.getText();
            String username = userName.getText();
            String password = passWord.getText();
            String roleO = role.getValue();
            StaffQueries logIn = new StaffQueries();
            logIn.insertIntoPStaffTable(firstNameO, lastNameO, SSN, addressO, dob, Email, 20000, roleO, username, password);
            System.out.println("Done ! Check DataBase");
        } catch (Exception e) {
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Adding a staff member did not go through!"+"\n"+"Please try again...");
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");
    }

    public void help(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/logIn.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role.getItems().addAll("Nurse", "Planer", "Assistant");
    }
}
