package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.databaseConnection.StaffQueries;
import java.io.IOException;
import java.sql.*;

public class LogInPanelController {
    @FXML
    private CheckBox showPass;
    @FXML
    private PasswordField passWord;
    @FXML
    private TextField userName;
    private SwitchScene sc = new SwitchScene();

    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/LogIn.fxml");
    }

    public void logIn(ActionEvent actionEvent) throws IOException, SQLException {//i will add later some sql statements once the database is done
        StaffQueries login = new StaffQueries();
        int row = login.verifyStaffLogin(userName.getText(), passWord.getText());


        if (row == 1 && userName.getText().endsWith("@yahoo.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/admin.fxml");

        } else if (row == 1 && userName.getText().endsWith("@gmail.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/nurse.fxml");

        } else if (row == 1 && userName.getText().endsWith("@hotmail.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/planer.fxml");

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Sorry, logging in was not possible please make sure your account information is correct!"+"\n"+"Try again...");
        }
    }

    public void showPass(ActionEvent actionEvent) {
        if (showPass.isSelected()) {
            passWord.setPromptText(passWord.getText());
            passWord.setText("");
            passWord.setDisable(true);

        }else {
            passWord .setText(passWord.getPromptText());
            passWord.setPromptText("Password");
            passWord.setDisable(false);
        }
    }

    public void forgetPassword(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent,"/sample/view/ResetPassword.fxml");
    }
}