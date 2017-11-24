package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import logic.MenuDB;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleGroup;
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
import javafx.scene.control.RadioButton;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;

public class ModifyController implements Initializable{  
	@FXML ListView<Menu> listView;
	@FXML ChoiceBox<Shop> choice_box;
	@FXML Button btnPrev;
	@FXML Button btnUpdate;
	@FXML Button btnDelete;
	@FXML Button btnAdd;
	private int option = 0;
	private final int UPDATE = 1;
	private final int DELETE = 2;
	private final int ADD = 3;
	private Shop shop;
	public static Menu menu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		setChoiceBoxAndInitList(new ShopDB());
	}

	@SuppressWarnings("unchecked")
	private void setChoiceBoxAndInitList(DataProvider dp) {
		ArrayList<Shop> shopList = (ArrayList<Shop>) new ArrayList<>(dp.getAllData());

		for (Shop i : shopList) {
			choice_box.getItems().addAll(i);
		}

		choice_box.setConverter(new StringConverter<Shop>() {
			@Override
			public String toString(Shop object) {
				return object.getShopName();
			}

			@Override
			public Shop fromString(String string) {
				return null;
			}
		});

		choice_box
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				shop = shopList.get((Integer)newValue);
				listView = setList(shop);
			}
		});
	}

	private ListView<Menu> setList(Shop selectedShop) {
		ArrayList<Menu> d = new MenuDB().findRecordBy(ShopDB.columShopId ,selectedShop.getShopId());
		ObservableList<Menu> data = (ObservableList<Menu>) FXCollections.observableArrayList(d);

		listView.setItems(data);
		listView.setCellFactory(new Callback<ListView<Menu>, ListCell<Menu>>() {
			@Override 
			public ListCell<Menu> call(ListView<Menu> data) {
				return new CellForMenu();
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

	//버튼 클릭시 option 값 설정.
	@FXML public void update(ActionEvent event) {
		option = UPDATE;
	}

	@FXML public void delete(ActionEvent event) {
		option = DELETE;
	}

	@FXML public void add(ActionEvent event) {
		StockDB s = new StockDB();
		s.getAllData();
		if (!choice_box.getSelectionModel().isEmpty()) {
			DataProvider dp = new MenuDB();
			//빈 껍때기 넣어줌
			menu = new Menu("", 0, 0);
			new Dialog().disply(menu);
			
			//TODO : 제대로된 널값처리하기 (아무 값도 안받고 종료될 때)
			if(menu.getMenuPrice() != 0) {
				dp.insert(menu);
				listView = 	setList(shop);
			}
		}
	}

	private void modify(Menu menu) {
		DataProvider dp = new MenuDB();

		switch(option) {
		case UPDATE :
			new Dialog().disply(menu);
			dp.update(menu);
			listView = 	setList(shop);
			break;
		case DELETE :
			dp.delete(menu);
			listView = 	setList(shop);
			break;
		default : 
			break;
		}
	}

	public void setModifiedMenuFromDialog(Menu menu) {
		this.menu = menu;
	}

	class CellForMenu extends ListCell<Menu> {
		HBox hbox;
		Button btn;
		Label label;
		ArrayList<Integer> btnItem;

		@Override
		public void updateItem(Menu item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {		
				btn = new Button(" ");    
				btn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("MenuName = " + getItem().getMenuName() 
								+ " Id = " + getItem().getMenuId());
						modify(item);
					}
				});

				label = new Label(item.getMenuName());

				hbox = new HBox();
				hbox.setAlignment(Pos.CENTER);
				hbox.getChildren().addAll(label, btn);
				setGraphic(hbox);
			}
		}
	}
}



