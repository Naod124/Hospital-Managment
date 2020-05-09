package sample.databaseConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.StaffTable;

import java.sql.*;

public class StaffQueries {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement pstmt;



    private ObservableList<StaffTable> obList = FXCollections.observableArrayList();


    public StaffQueries() {
    }


    public void insertIntoPStaffTable(String firstName , String lastName , String ssn , String address, String dOb , String email , double salary , String role, String userName , String password) {
        try {

            connection = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
            PreparedStatement statement = connection.prepareStatement("insert into staff (FirstName,LastName,SSN,Adress,DateOfbirth,Email,Salary,Role) values (?,?,?,?,?,?,?,?);");
            PreparedStatement statement1 = connection.prepareStatement("insert into login (Username,Password,staff_SSN) values (?,?,?);");
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,ssn);
            statement.setString(4,address);
            statement.setString(5, dOb);
            statement.setString(6,email);
            statement.setString(7, String.valueOf(salary));
            statement.setString(8,role);
            statement1.setString(1,userName);
            statement1.setString(2,password);
            statement1.setString(3,ssn);
            statement.execute();
            statement.close();
            statement1.execute();
            statement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIntoStaffTable() {

    }
    public void viewAllStaffTable() throws SQLException {
        ResultSet rs;
        String selectQuery = "SELECT * FROM STAFF;";

        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        rs = connection.createStatement().executeQuery(selectQuery);



        while (rs.next()) {
            StaffTable pt = new StaffTable("FirstName", "LastName", "SSN", "E-mail", "Address", "Role");

            pt.setFirstName(rs.getString("FirstName"));
            pt.setLastName(rs.getString("LastName"));
            pt.setSsn(rs.getString("ssn"));
            pt.setEmail(rs.getString("Email"));
            pt.setAddress(rs.getString("Adress"));
            pt.setRole(rs.getString("Role"));

            obList.add(pt);

        }


    }    public void viewNurseTable() throws SQLException {
        ResultSet rs;
        String selectQuery = "select * from staff where Role = 'nurse' ";

        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        rs = connection.createStatement().executeQuery(selectQuery);



        while (rs.next()) {
            StaffTable pt = new StaffTable("FirstName", "LastName", "SSN", "E-mail", "Address", "Role");

            pt.setFirstName(rs.getString("FirstName"));
            pt.setLastName(rs.getString("LastName"));
            pt.setSsn(rs.getString("ssn"));
            pt.setEmail(rs.getString("Email"));
            pt.setAddress(rs.getString("Adress"));
            pt.setRole(rs.getString("Role"));

            obList.add(pt);

        }


    }      public void viewPlanerTable() throws SQLException {
        ResultSet rs;
        String selectQuery = "select * from staff where Role = 'planer' ";

        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        rs = connection.createStatement().executeQuery(selectQuery);



        while (rs.next()) {
            StaffTable pt = new StaffTable("FirstName", "LastName", "SSN", "E-mail", "Address", "Role");

            pt.setFirstName(rs.getString("FirstName"));
            pt.setLastName(rs.getString("LastName"));
            pt.setSsn(rs.getString("ssn"));
            pt.setEmail(rs.getString("Email"));
            pt.setAddress(rs.getString("Adress"));
            pt.setRole(rs.getString("Role"));

            obList.add(pt);

        }


    }


    public void removeStaff(String ssn){
        try {

            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
            String deleteQuery = "DELETE FROM staff WHERE SSN = ?";
            pstmt = connection.prepareStatement(deleteQuery);
            pstmt.setString(1, ssn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int verifyStaffLogin(String username, String password) { // The Log in verification method. Returns 1 incase found, else
        try {
            Connection connect = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
            PreparedStatement statement = connect.prepareStatement("select Username,Password FROM login where Username =? AND Password =?;");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            System.out.println();
            rs.next();
            return rs.getRow();
        } catch (SQLException a) {
            return 0;
        }
    }

    public void planerApply(Object patient_name, Object time_from, Object time_to, String description){

        try {

            Connection connect = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
            PreparedStatement statement = connect.prepareStatement("INSERT INTO schedule (patient_name, time_from, time_to, description) VALUES(?,?,?,?);");
            statement.setString(1, (String) patient_name);
            statement.setString(2, (String) time_from);
            statement.setString(3, (String) time_to);
            statement.setString(4, description);
            statement.executeUpdate();
        } catch (SQLException e){

        }
    }
    public ObservableList<StaffTable> getObList() {
        return obList;
    }

    public void setObList(ObservableList<StaffTable> obList) {
        this.obList = obList;
    }
}
