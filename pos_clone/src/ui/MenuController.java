package ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import data.DataProvider;
import data.Menu;
import data.Shop;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.MenuDB;
import logic.ShopDB;

//TODO : �α��� ȭ�� ���� ��� ���̵�, �佺���� �ޱ�
//TODO : exe���� ����� https://www.youtube.com/watch?v=CQEPlGFYTr8
//TODO : Orders db�� ���ֱ�
public class MenuController implements Initializable {
	private ListView<Menu> list = new ListView<>();
	private @FXML Label selectedMenu;
	private @FXML BorderPane rootLayout;
	private @FXML ChoiceBox<Shop> choiceBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setChoiceBoxAndInitList(new ShopDB());
		rootLayout.setCenter(list);
	}

	@SuppressWarnings("unchecked")
	private void setChoiceBoxAndInitList(DataProvider shop) {
		ArrayList<Shop> shopList = (ArrayList<Shop>) new ArrayList<>(shop.getAllData());
		
		for (Shop i : shopList) {
			choiceBox.getItems().addAll(i);
		}

		choiceBox.setConverter(new StringConverter<Shop>() {
			@Override
			public String toString(Shop object) {
				return object.getShopName();
			}

			@Override
			public Shop fromString(String string) {
				return null;
			}
		});
		
		if (! shopList.isEmpty()) {
			choiceBox.getSelectionModel().select(0);
		}
		
		choiceBox
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				list = setAndGetList(shopList.get((Integer)newValue));
			}
		});
	}

	ArrayList<Menu> menuList = new ArrayList<>();
	private void setAndGetBottomLabel(Menu menu, boolean ctn) {
		if (ctn) {
			menuList.add(menu);
			setSumAndTextBottom();
		} else {
			if (menuList.contains(menu)) {
				menuList.set(menuList.indexOf(menu), null);
				setSumAndTextBottom();
			}
		}
	}
	
	private void setSumAndTextBottom() {
		String textBottom = "";
		int sum = 0;
		
		for (Menu s : menuList) {
			if (s != null) {
				textBottom = textBottom + s.getMenuName() + s.getMenuPrice() + "\n";
				sum = sum + s.getMenuPrice();
			}
		}
		selectedMenu.setText(textBottom + "\n" + "�� �� = " + sum);
		sum = 0;
		textBottom = "";
	}

	private ListView<Menu> setAndGetList(Shop selectedItem) {
		if (selectedItem != null) { 
			ArrayList<Menu> d = new MenuDB().findRecordById(ShopDB.columShopId ,selectedItem.getShopId());
			ArrayList<Menu> dataList = new ArrayList<>();
			dataList.addAll(d);
			ObservableList<Menu> data = (ObservableList<Menu>) FXCollections.observableArrayList(dataList);

			list.setItems(data);
			list.setCellFactory(new Callback<ListView<Menu>, ListCell<Menu>>() {
				@Override 
				public ListCell<Menu> call(ListView<Menu> data) {
					return new Cell();
				}
			});

			return list;
		}
		
		return null;
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
				//TODO : ��ư 2�� spinner�� ��ü�ϱ�
				btnCount = new Button("+");    
				btnCount.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("MenuName = " + getItem().getMenuName() 
								+ " Id = " + getItem().getMenuId());
						setAndGetBottomLabel(item, true);
					}
				});

				btnDeCount = new Button(" -");    
				btnDeCount.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						System.out.println("MenuName = " + getItem().getMenuName() 
								+ " Id = " + getItem().getMenuId());
						setAndGetBottomLabel(item, false);
					}
				});

				label = new Label(item.getMenuName());

				hbox = new HBox();
				hbox.setAlignment(Pos.CENTER);
				hbox.getChildren().addAll(label, btnCount, btnDeCount);
				setGraphic(hbox);
			}
		}
	}

	@FXML private void nextScene(ActionEvent event) throws IOException {
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("payment.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
		
		System.out.println(menuList.isEmpty()); //TODO : ����������˷��ڽ� ���� �� �������� �� �Ѿ����.
	}
	
//	private Label setAndGetLabel(String title, int fontSize) {
//		Label label = new Label(title);
//		label.setMaxWidth(Double.MAX_VALUE);
//		label.setAlignment(Pos.CENTER);
//		label.setFont(Font.font("system", FontWeight.BOLD, fontSize));
//
//		return label;
//	}
}
