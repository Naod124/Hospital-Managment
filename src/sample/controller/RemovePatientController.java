package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.model.PatientTable;

import java.io.IOException;
import java.sql.*;

public class RemovePatientController {

    ManagePatientController mp = new ManagePatientController();

    private Connection conn;
    PreparedStatement pstmt;
    private ObservableList<PatientTable> obList = FXCollections.observableArrayList();


    @FXML private TableView<PatientTable> table;


    @FXML private Button deletebutton;
    @FXML private Button viewbutton;
    @FXML private Button backbutton;
    @FXML private Button exitbutton;
    @FXML private TextField ssntextfield;
    @FXML private TableColumn<PatientTable, String> ssncol;
    @FXML private TableColumn <PatientTable, String> firstnamecol;
    @FXML private TableColumn <PatientTable, String> lastnamecol;
    @FXML private TableColumn <PatientTable, String> dobcol;
    @FXML private TableColumn <PatientTable, String> gendercol;

@FXML public void viewpatient() throws SQLException {
    ResultSet rs;
    String selectQuery = "SELECT * FROM PATIENT;";

    conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
            "nursinghome", "Vw3J!60l-0kd");
    rs = conn.createStatement().executeQuery(selectQuery);


    ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
    firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
    lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
    dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
    gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));


    while (rs.next()){
        PatientTable pt = new PatientTable("SSN", "FirstName", "LastName","DateOfBirth","Gender");


        pt.setSsn(rs.getString("SSN"));
        pt.setFirstName(rs.getString("FirstName"));
        pt.setLastName(rs.getString("LastName"));
        pt.setDateOfBirth(rs.getString("DateOfBirth"));
        pt.setGender(rs.getString("Gender"));

        obList.add(pt);

    }

    table.setItems(obList);

}
    @FXML public void removePatient() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        try {
            String updateQuery = "DELETE FROM patient WHERE SSN = ?";
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, ssntextfield.getText());
            pstmt.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Information removed");
            a.showAndWait();
            table.getItems().clear();
            viewpatient();
            ssntextfield.clear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void back(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/nurse.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Hi");
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }





}