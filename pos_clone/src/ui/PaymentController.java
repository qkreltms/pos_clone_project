package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Customer;
import data.DataProvider;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.CustomerDB;
import javafx.event.ActionEvent;

public class PaymentController implements Initializable{
	private @FXML TextField tfName;
	private @FXML TextField tfPhone;
	private @FXML TextField tfCard;
	private String name;
	private String phone;
	private String card;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getTextFromtextField();
	}

	private void getTextFromtextField() {
		tfName.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				name = tfName.getText();	
			}
		});
		tfPhone.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				phone = tfPhone.getText();	
			}
		});
		tfCard.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				card = tfCard.getText();	
			}
		});
	}
	
	private void insertIntoCustomerDb() {
		DataProvider dataProvider = new CustomerDB();
		if (name != null && phone != null && card != null) { //TODO : 하나라도 널일경우 알럿박스 만들기
			dataProvider.insert(new Customer(name, phone, card));
		}
		dataProvider.getAllData();
	}

	@FXML private void nextScene(ActionEvent event) {
		System.out.println(name+phone+card);
		insertIntoCustomerDb();
	}

	@FXML private void prevScene(ActionEvent event) throws IOException {
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("menu.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}
}
