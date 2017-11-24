package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import logic.DBConnection;
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
	Shop s = null;

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
				s = shopList.get((Integer)newValue);
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
		commit();
		
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("index.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}
	
	private void commit() {
		try {
			DBConnection.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class Cell extends ListCell<Menu> {
		HBox hbox;
		Button btnCount;
		Button btnDeCount;
		Label label;
		Label labelCtn;
		ArrayList<Integer> btnItem;

		@SuppressWarnings("unchecked")
		@Override
		public void updateItem(Menu item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {		
				DataProvider dp = new StockDB();
				dp.getAllData();
				System.out.println(item.getMenuId());
				ArrayList<Stock> stock = (ArrayList<Stock>) dp.findRecordBy(StockDB.columMenuId, item.getMenuId());
				
				if (stock != null) {
					Label lbQuentity = new Label(String.valueOf(stock.get(0).getQuantity()));

					btnCount = new Button("+");    
					btnCount.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							System.out.println("MenuName = " + getItem().getMenuName() 
									+ " Id = " + getItem().getMenuId());
							updateStockQuantity(item, stock.get(0), 1);
						}
					});

					btnDeCount = new Button(" -");    
					btnDeCount.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							System.out.println("MenuName = " + getItem().getMenuName() 
									+ " Id = " + getItem().getMenuId());
							updateStockQuantity(item, stock.get(0), -1);
						}
					});

					Label lbMenuName = new Label(item.getMenuName());

					hbox = new HBox();
					hbox.setAlignment(Pos.CENTER);
					hbox.getChildren().addAll(lbMenuName, btnCount, lbQuentity, btnDeCount);
					setGraphic(hbox);
				}
			}
		}

		private void updateStockQuantity(Menu item, Stock stock, int add) {
			DataProvider dp = new StockDB();
			dp.update(new Stock(item.getMenuId(), stock.getQuantity() + add));
			listView = 	setList(s);
		}
	}
}

