package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    public Label welcomeLabel;

    public void back(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/sample/view/LogIn.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addNewStuff(ActionEvent actionEvent) {
    }

    public void removeStaff(ActionEvent actionEvent) {
    }

    public void viewPatients(ActionEvent actionEvent) {
    }

    public void help(ActionEvent actionEvent) {
    }
}
