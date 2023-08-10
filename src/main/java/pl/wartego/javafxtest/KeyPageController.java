package pl.wartego.javafxtest;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class KeyPageController implements Initializable {
    public static Connection connectDB;
    private Stage stage;
    private Scene scene;
    public String ipAddress;
    @FXML
    private Button buttonClose;
    @FXML
    private TextField textKeyBefore;
    @FXML
    private TextField textKeyAfter;
    @FXML
    private TableView tableDB;

    @FXML
    private TextField fieldCount;
    @FXML
    private ObservableList<ObservableList> data;
    @FXML
    private ResultSet queryResult;


    @FXML
    protected void buttonCloseAction() {
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void buttonGenerateAction() throws SQLException{
        SequanceChange sequanceChange = new SequanceChange();
        String keyAfterChanges = sequanceChange.changeSequence(textKeyBefore.getText());
        textKeyAfter.setText(keyAfterChanges);
        addToTableKeys();
        countAllRows();
    }

    @FXML
    protected void addToTableKeys() throws SQLException {
        connectDB = ConnectionDBMethods.getDataBaseConnect();
        LocalDate date = LocalDate.now();
        String formatedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String addRowToTable = "INSERT INTO encryptionkeys (keybefore, keyafter, userProvide, inputDate, IPAddress) VALUES (?, ?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(addRowToTable);

            preparedStatement.setString(1, textKeyBefore.getText());
            preparedStatement.setString(2, textKeyAfter.getText());
            preparedStatement.setString(3, HelloController.loginUser);
            preparedStatement.setString(4, formatedDate);
            preparedStatement.setString(5, ipAddress);
            int queryResult = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectDB.close();
        }

        updateTableRows();
    }

    @FXML
    protected void listAllRowsFromDB() throws SQLException, IOException {

        connectDB = ConnectionDBMethods.getDataBaseConnect();


        data = FXCollections.observableArrayList();
        String listAllQuery = "SELECT * FROM encryptionkeys";

        try {
            Statement statement = connectDB.createStatement();
            queryResult = statement.executeQuery(listAllQuery);

            addColumns();
            while (queryResult.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= queryResult.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(queryResult.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            tableDB.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    @FXML
    protected void updateTableRows() {

        connectDB = ConnectionDBMethods.getDataBaseConnect();


        data = FXCollections.observableArrayList();
        String listAllQuery = "SELECT * FROM encryptionkeys";

        try {
            Statement statement = connectDB.createStatement();
            queryResult = statement.executeQuery(listAllQuery);

            while (queryResult.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= queryResult.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(queryResult.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);
            }
            tableDB.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    protected void addColumns() throws SQLException {
        for (int i = 0; i < queryResult.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn(queryResult.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            tableDB.getColumns().addAll(col);
            System.out.println("Column [" + i + "] ");

        }
    }
    @FXML
    protected int countAllRows() throws SQLException {
        int countRows = ConnectionDBMethods.getCountRows();
        fieldCount.setText("Count: "+ countRows);
        return countRows;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listAllRowsFromDB();
            countAllRows();
            ipAddress = new NetworkIP().getIP();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
