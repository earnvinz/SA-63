package SA_63.Controller;

import SA_63.Model.Deposit_Withdraw;
import SA_63.Static.Count_history_transaction;
import SA_63.Static.OnlineUser;
import SA_63.Static.StaticAllmember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class TransactionController implements Initializable {
    ArrayList<Deposit_Withdraw> Myhistory ;
    @FXML
    Label name;
    @FXML
    TableView table;
    @FXML
    TableColumn<Deposit_Withdraw,String> refid;
    @FXML
    TableColumn<Deposit_Withdraw,String> date;
    @FXML
    TableColumn<Deposit_Withdraw,String> type;
    @FXML
    TableColumn<Deposit_Withdraw,String> amount;
    @FXML
    TableColumn<Deposit_Withdraw,String> balance;

    public void back(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("MemberPage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root, 408, 445));
        stage.show();
        stage.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Myhistory = new ArrayList<>();
        name.setText(name.getText()+" "+ OnlineUser.getOnline().getFirstname()+" "+OnlineUser.getOnline().getLastname());
        for(Deposit_Withdraw deposit_withdraw : Count_history_transaction.getHistory()){

            if(deposit_withdraw.getAccount_ID().equals(OnlineUser.getOnline().getId())){
                Myhistory.add(deposit_withdraw);

            }

        }
        Collections.sort(Myhistory);


        double total = 0;
        for(Deposit_Withdraw deposit_withdraw : Myhistory){
            total =  deposit_withdraw.find_total(total);
        }




        refid.setCellValueFactory(new PropertyValueFactory<>("DepWith_ID"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        type.setCellValueFactory(new PropertyValueFactory<>("status"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        balance.setCellValueFactory(new PropertyValueFactory<>("final_balance"));
        for(Deposit_Withdraw deposit_withdraw : Myhistory){
            table.getItems().add(deposit_withdraw);
        }


    }
}
