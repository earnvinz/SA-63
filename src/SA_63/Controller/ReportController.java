package SA_63.Controller;

import SA_63.Model.Deposit_Withdraw;
import SA_63.Static.Count_history_transaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    private ArrayList<Deposit_Withdraw> depolist ;
    private ArrayList<Deposit_Withdraw> withdrawlist ;

    private double depo_amount;
    private double with_amount;

    @FXML
    private AnchorPane anchor;
    @FXML
    private Label list,labelamount;

    @FXML
    private TableView table;

    @FXML
    private TableColumn<Deposit_Withdraw, String> date;

    @FXML
    private TableColumn<Deposit_Withdraw, String> type;

    @FXML
    private TableColumn<Deposit_Withdraw, String> amount;

    @FXML
    private TableColumn<Deposit_Withdraw, String> idnumber;

    @FXML
    private TableColumn<Deposit_Withdraw, String> iddepwith;

    public void back(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("OfficerPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 624, 578));
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void deposit(ActionEvent event) {
        table.getItems().clear();
        list.setText("รายการบันทึกการฝากทั้งหมด");
        list.setVisible(true);
        for(Deposit_Withdraw deposit_withdraw : depolist){
            depo_amount += Double.parseDouble(deposit_withdraw.getAmount());
            table.getItems().add(deposit_withdraw);
        }
        String a = String.format("%.2f",depo_amount);
        labelamount.setText("จำนวนเงินทั้งหมด :  " + a);
        anchor.setVisible(true);
    }

    @FXML
    void withdraw(ActionEvent event) {
        table.getItems().clear();
        list.setText("รายการบันทึกการถอนทั้งหมด");
        list.setVisible(true);
        for(Deposit_Withdraw deposit_withdraw : withdrawlist){
            with_amount += Double.parseDouble(deposit_withdraw.getAmount());
            table.getItems().add(deposit_withdraw);
        }
        String a = String.format("%.2f",with_amount);
        labelamount.setText("จำนวนเงินทั้งหมด :  "+ a);
        anchor.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        depo_amount = 0.0;
        with_amount = 0.0;
        anchor.setVisible(false);

        depolist = new ArrayList<>();
        withdrawlist = new ArrayList<>();

        idnumber.setCellValueFactory(new PropertyValueFactory<>("account_ID"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        type.setCellValueFactory(new PropertyValueFactory<>("status"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        iddepwith.setCellValueFactory(new PropertyValueFactory<>("DepWith_ID"));

        for(Deposit_Withdraw deposit_withdraw : Count_history_transaction.getHistory()){
            if(deposit_withdraw.getStatus().equals("CDP")){
                depolist.add(deposit_withdraw);
            }
            else{
                withdrawlist.add(deposit_withdraw);
            }
        }
        Collections.sort(depolist);
        Collections.sort(withdrawlist);
    }
}
