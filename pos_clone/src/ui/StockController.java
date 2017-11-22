package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.MenuDB;
import logic.ShopDB;
import logic.StockDB;
import model.DataProvider;
import model.Menu;
import model.Shop;
import model.Stock;

//TODO : update го╠Б
public class StockController implements Initializable {
	@FXML Button btnPrev;
	@FXML ChoiceBox<Shop> cbSelectShop;
	DataProvider dp = new StockDB();
	@FXML ListView<Menu> listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setChoiceBoxAndInitList(new ShopDB());
	}

	@SuppressWarnings("unchecked")
	private void setChoiceBoxAndInitList(DataProvider shop) {
		ArrayList<Shop> shopList = (ArrayList<Shop>) new ArrayList<>(shop.getAllData());

		for (Shop i : shopList) {
			cbSelectShop.getItems().addAll(i);
		}

		cbSelectShop.setConverter(new StringConverter<Shop>() {
			@Override
			public String toString(Shop object) {
				return object.getShopName();
			}

			@Override
			public Shop fromString(String string) {
				return null;
			}
		});

		cbSelectShop
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				listView = 	setList(shopList.get((Integer)newValue));
			}
		});
	}

	private ListView<Menu> setList(Shop selectedItem) {
		ArrayList<Menu> m = new MenuDB().findRecordBy(ShopDB.columShopId ,selectedItem.getShopId());
		ObservableList<Menu> data = (ObservableList<Menu>) FXCollections.observableArrayList(m);

		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<Menu>, ListCell<Menu>>() {
			@Override 
			public ListCell<Menu> call(ListView<Menu> data) {
				return new Cell();
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

class Cell extends ListCell<Menu> {
	HBox hbox;
	Button btnCount;
	Button btnDeCount;
	Label label;
	Label labelCtn;
	ArrayList<Integer> btnItem;

	@Override
	public void updateItem(Menu item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {		
			btnCount = new Button("+");    
			btnCount.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("MenuName = " + getItem().getMenuName() 
							+ " Id = " + getItem().getMenuId());
				}
			});

			btnDeCount = new Button(" -");    
			btnDeCount.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("MenuName = " + getItem().getMenuName() 
							+ " Id = " + getItem().getMenuId());
				}
			});

			Label lbMenuName = new Label(item.getMenuName());

			DataProvider dp = new StockDB();
			@SuppressWarnings("unchecked")
			ArrayList<Stock> stock = (ArrayList<Stock>) dp.findRecordBy(StockDB.columMenuId, item.getMenuId());
			if (stock != null) {
				Label lbQuentity = new Label(String.valueOf(stock.get(0).getQuantity()));

				hbox = new HBox();
				hbox.setAlignment(Pos.CENTER);
				hbox.getChildren().addAll(lbMenuName, btnCount, lbQuentity, btnDeCount);
				setGraphic(hbox);
			}
		}
	}
}
