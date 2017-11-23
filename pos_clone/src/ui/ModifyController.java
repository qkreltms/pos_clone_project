package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import logic.MenuDB;
import logic.ShopDB;
import model.DataProvider;
import model.Menu;
import model.Shop;
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
	@FXML ToggleGroup modifyOrDelete;
	@FXML RadioButton rbModify;
	@FXML RadioButton rbDelete;
	@FXML ListView<Menu> listView;
	@FXML ChoiceBox<Shop> choice_box;

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		setChoiceBoxAndInitList(new ShopDB());
	}
	
	@SuppressWarnings("unchecked")
	private void setChoiceBoxAndInitList(DataProvider shop) {
		ArrayList<Shop> shopList = (ArrayList<Shop>) new ArrayList<>(shop.getAllData());

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
				listView = setList(shopList.get((Integer)newValue));
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
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("index.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}

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

