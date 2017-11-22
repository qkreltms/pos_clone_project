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
import javafx.scene.control.Toggle;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.MenuDB;
import logic.OrderDB;
import logic.ShopDB;
import logic.StockDB;
import model.DataProvider;
import model.Menu;
import model.Order;
import model.Shop;
import model.Stock;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.RadioButton;

//TODO : update 하기
public class StockController implements Initializable {
	@FXML Button btnPrev;
	@FXML Button btnNext;
	@FXML Button btnUpdate;
	@FXML Button btnDelete;
	@FXML ChoiceBox<Shop> cbSelectShop;
	DataProvider dp = new StockDB();
	@FXML ToggleGroup orderOrStock;
	int menu = 0;
	@FXML BorderPane rootLayout;
	@FXML RadioButton rbOrder;
	@FXML RadioButton rbStock;
	@SuppressWarnings("rawtypes")
	@FXML ListView listView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rbOrder.setUserData("order");
		rbStock.setUserData("stock");

		setChoiceBoxAndInitList(new ShopDB());
		addListenerOnRadioBtn();
	}

	public void addListenerOnRadioBtn () {
		orderOrStock.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (orderOrStock.getSelectedToggle() != null) {
					switch (orderOrStock.getSelectedToggle().getUserData().toString()) {
					case "order" :
						menu = 0;
						break;
					case "stock" :
						menu = 1;
						break;
					}
				}
			}
		});
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ListView setList(Shop selectedItem) {
		if (selectedItem != null) { 
			if (menu == 0) {
				//오더에서 
				ArrayList<Order> d = new OrderDB().findRecordBy(OrderDB.columShopId ,selectedItem.getShopId());
				ArrayList<Order> dataList = new ArrayList<>();
				dataList.addAll(d);
				ObservableList<Order> data = (ObservableList<Order>) FXCollections.observableArrayList(dataList);

				listView.setItems(data);
				listView.setCellFactory(new Callback<ListView<Order>, ListCell<Order>>() {
					@Override 
					public ListCell<Order> call(ListView<Order> data) {
						return new CellForOrder();
					}
				});
			} else if (menu == 1) {
				ArrayList<Menu> d = new MenuDB().findRecordBy(ShopDB.columShopId ,selectedItem.getShopId());
				ArrayList<Menu> dataList = new ArrayList<>();
				dataList.addAll(d);
				ObservableList<Menu> data = (ObservableList<Menu>) FXCollections.observableArrayList(dataList);

				listView.setItems(data);
				listView.setCellFactory(new Callback<ListView<Menu>, ListCell<Menu>>() {
					@Override 
					public ListCell<Menu> call(ListView<Menu> data) {
						return new Cell();
					}
				});
			}
			return listView;
		}
		return null;
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
			Label lbOrderDate = new Label(String.valueOf(item.getOrderDate()));
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
