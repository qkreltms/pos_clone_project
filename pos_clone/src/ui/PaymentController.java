package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import logic.OrderDB;
import model.Customer;
import model.DataProvider;
import model.Menu;
import model.Order;
import javafx.event.ActionEvent;

public class PaymentController implements Initializable{
	private @FXML TextField tfName;
	private @FXML TextField tfPhone;
	private @FXML TextField tfCard;
	private String name;
	private String phone;
	private String card;
	private ArrayList<Menu> menuList;
	
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

	@FXML private void nextScene(ActionEvent event) throws IOException {
		insertIntoCustomerDB();
		insertIntoOrderDB();
		
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("receipt.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}
	
	private void insertIntoCustomerDB() {
		CustomerDB custDB = new CustomerDB();
		if (name != null && phone != null && card != null) { //TODO : 하나라도 널일경우 알럿박스 만들기
			int custId = (int) custDB.getMaxId();
			custDB.insert(new Customer(custId, name, phone, card));
		}
		custDB.getAllData();
	}
	
	private void insertIntoOrderDB() {
		DataProvider dataProvider = new OrderDB();
		int orderId = (int)dataProvider.getMaxId();
		
		CustomerDB custDB = new CustomerDB();//TODO : 비효율적, 방법찾기
		int custId = (int) custDB.getMaxId();
		dataProvider.insert(new Order(orderId, custId, menuList));
		dataProvider.getAllData(); 
	}

	@FXML private void prevScene(ActionEvent event) throws IOException {
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("menu.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}
	
	//getArrayList from prev scene
	public void setMenuList(ArrayList<Menu> menuList) {
		this.menuList = menuList;
	}
}
