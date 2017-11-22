package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.OrderDB;
import model.Menu;
import model.Order;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class OrderController implements Initializable{

	@FXML ListView<Order> listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listView = 	setList();
	}

	private ListView<Order> setList() {
		ArrayList<Order> d = new OrderDB().getAllData();
		ObservableList<Order> data = (ObservableList<Order>) FXCollections.observableArrayList(d);

		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<Order>, ListCell<Order>>() {
			@Override 
			public ListCell<Order> call(ListView<Order> data) {
				return new CellForOrder();
			}
		});
		return listView;
	}


	@FXML public void moveToPrev(ActionEvent event) throws IOException {
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("index.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}

}

class CellForOrder extends ListCell<Order> {
	HBox hbox;
	Button btnCount;
	Button btnDeCount;
	Label label;
	Label labelCtn;
	ArrayList<Integer> btnItem;

	@Override
	public void updateItem(Order item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {		
			Label lbOrderId = new Label(String.valueOf(item.getOrderId()));
			Label lbCustId = new Label(String.valueOf(item.getCustId()));
			Label lbOrderDate = new Label(item.getOrderDate() + "");
			
			String menuName = "";
			String menuPrice = "";
			for (Menu i : item.getMenuList()) {
				menuName = menuName + i.getMenuName();
				menuPrice = menuPrice + i.getMenuPrice();
			}
			Label lbMenuName = new Label(menuName);
			Label lbMenuPrice = new Label(menuPrice);
			
			hbox = new HBox();
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().addAll(lbOrderId, lbCustId, lbOrderDate, lbMenuName, lbMenuPrice);
			setGraphic(hbox);
		}
	}
}



