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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.MenuDB;
import logic.ShopDB;
import model.DataProvider;
import model.Menu;
import model.Shop;
import tts.Clova;


public class MenuController implements Initializable {
	private ListView<Menu> list = new ListView<>();
	private @FXML Label selectedMenu;
	private @FXML BorderPane rootLayout;
	private @FXML ChoiceBox<Shop> choiceBox;
	public static ArrayList<Menu> menuList = new ArrayList<>();
	public static int[] menuCountAry = new int[100];
	public static Shop selectedShopObj;
	Media  media;
	MediaPlayer mediaPlayer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startVoice();
		setChoiceBoxAndInitList(new ShopDB());
		rootLayout.setCenter(list);
	}
	
	public void startVoice() {
		media = new Media(new Clova("안녕하세요 고객님 어떤 매뉴를 주문하시겠습니까?").fileLoc);  
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
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

		choiceBox
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				selectedMenu.setText("");
				menuList.clear();
				list = setAndGetList(shopList.get((Integer)newValue));
				selectedShopObj = choiceBox.getItems().get((Integer)newValue);
			}
		});
	}

	private void setAndGetBottomLabel(Menu menu, boolean ctn) {
		if (ctn) {
			menuList.add(menu);
			setSumAndTextBottom();

			menuCountAry[menu.getMenuId()] = menuCountAry[menu.getMenuId()] + 1;

		} else {
			int index = menuList.indexOf(menu);
			if (index != -1) {
				menuList.remove(index);
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
		selectedMenu.setText(textBottom + "\n" + "총 합 = " + sum);
	}

	private ListView<Menu> setAndGetList(Shop selectedShop) {
		if (selectedShop != null) { 
			ArrayList<Menu> d = new MenuDB().findRecordBy(ShopDB.columShopId ,selectedShop.getShopId());
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

		startMenuReadingVoice();

		if (! menuList.isEmpty()) {
			FXMLLoader nextPage = new FXMLLoader(this.getClass().getResource("payment.fxml"));
			Parent root = (Parent) nextPage.load();

			PaymentController paymentController = nextPage.getController();
			paymentController.setMenuList(menuList);

			Scene nextPageScene = new Scene(root);
			Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			thisStage.setScene(nextPageScene);
			thisStage.show();
		}
	}

	public ArrayList<Menu> getMenuList() {
		return menuList;
	}

	public void startMenuReadingVoice() {
		String menuToSpeech = "";
		for (Menu i : menuList) {
			menuToSpeech = menuToSpeech + ", " + i.getMenuName();
		}

		media = new Media(new Clova("선택하신 매뉴는 " + menuToSpeech + "입니다. 결재하시겠습니까?").fileLoc);  
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	
	@FXML public void prevScene(ActionEvent event) throws IOException {
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("index.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}
}
