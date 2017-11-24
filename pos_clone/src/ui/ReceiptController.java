package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.CustomerDB;
import logic.DBConnection;
import logic.ShopOwnerDB;
import model.Customer;
import model.DataProvider;
import model.Menu;
import model.ShopOwner;
import tts.Clova;

public class ReceiptController implements Initializable{
	@FXML GridPane gridPane;
	@SuppressWarnings("rawtypes")
	private ListView listView;
	private Customer cust = PaymentController.cust;
	
	@SuppressWarnings("rawtypes")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//gridPane.setGridLinesVisible(true);
		
		Label label = new Label("���������");
		HBox hbox = new HBox();
		hbox.getChildren().add(label);
		hbox.setAlignment(Pos.BOTTOM_LEFT);
		gridPane.add(hbox, 0, 0);
		
		Label label2 = new Label("�����̸�");
		HBox hbox2 = new HBox();
		hbox2.getChildren().add(label2);
		hbox2.setAlignment(Pos.TOP_LEFT);
		gridPane.add(hbox2, 0, 1);
		Label label22 = new Label(MenuController.selectedShopObj.getShopName());
		HBox hbox22 = new HBox();
		hbox22.getChildren().add(label22);
		hbox22.setAlignment(Pos.TOP_RIGHT);
		gridPane.add(hbox22, 1, 1);
		
		Label label3 = new Label("����� ��ȭ��ȣ");
		HBox hbox3 = new HBox();
		hbox3.getChildren().add(label3);
		hbox3.setAlignment(Pos.TOP_LEFT);
		gridPane.add(hbox3, 0, 2);
		DataProvider dp = new ShopOwnerDB();
		@SuppressWarnings({ "unchecked", "unused" })
		ArrayList<ShopOwner> soList = (ArrayList<ShopOwner>) dp.findRecordBy(ShopOwnerDB.columShopOwnerId, MenuController.selectedShopObj.getOwnerId());
		Label label33 = new Label(soList.get(0).getShopOwnerPhoneNumber());
		HBox hbox33 = new HBox();
		hbox33.getChildren().add(label33);
		hbox33.setAlignment(Pos.TOP_RIGHT);
		gridPane.add(hbox33, 1, 2);
		
		Label label4 = new Label("-------------------------------------------------");
		HBox hbox4 = new HBox();
		hbox4.getChildren().add(label4);
		hbox4.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox4, 0, 3, 2, 1);
		
		
		Label label5 = new Label("*���ι�ħ�� ���� ��ȯ/ȯ���� �ݵ�� �������� �����ϼž� ��");
		HBox hbox5 = new HBox();
		hbox5.getChildren().add(label5);
		hbox5.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox5, 0, 4, 2, 1);
		//TODO : ��ȿ������ ��� �ϳ��� ���ƺ���
		Label label6 = new Label("��, ī������� 30�� �̳� ī��� ������ ���� �� �����մϴ�.");
		HBox hbox6 = new HBox();
		hbox6.getChildren().add(label6);
		hbox6.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox6, 0, 5, 2, 1);
		
		Label label7 = new Label("-------------------------------------------------");
		HBox hbox7 = new HBox();
		hbox7.getChildren().add(label7);
		hbox7.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox7, 0, 6, 2, 1);
		
		listView = new ListView();
		listView = setAndGetList(MenuController.menuList); 
		listView.setMinHeight(210);
		gridPane.add(listView, 0, 7, 2, 1);
		
		int sum = 0;
		for (Menu i : MenuController.menuList) {
			sum = sum + i.getMenuPrice();
		}
		
		Label label11 = new Label("���� ����");
		HBox hbox11 = new HBox();
		hbox11.getChildren().add(label11);
		hbox11.setAlignment(Pos.TOP_LEFT);
		gridPane.add(hbox11, 0, 8);
		Label label12 = new Label((int)(sum - (sum * 0.1)) + "");
		HBox hbox112 = new HBox();
		hbox112.getChildren().add(label12);
		hbox112.setAlignment(Pos.TOP_RIGHT);
		gridPane.add(hbox112, 1, 8);
		
		Label label13 = new Label("�ΰ���");
		HBox hbox13 = new HBox();
		hbox13.getChildren().add(label13);
		hbox13.setAlignment(Pos.TOP_LEFT);
		gridPane.add(hbox13, 0, 9);
		Label label14 = new Label((int)(sum * 0.1) + "");
		HBox hbox114 = new HBox();
		hbox114.getChildren().add(label14);
		hbox114.setAlignment(Pos.TOP_RIGHT);
		gridPane.add(hbox114, 1, 9);
		
		Label label15 = new Label("�հ�");
		HBox hbox15 = new HBox();
		hbox15.getChildren().add(label15);
		hbox15.setAlignment(Pos.TOP_LEFT);
		gridPane.add(hbox15, 0, 10);
		Label label16 = new Label(sum + "");
		HBox hbox116 = new HBox();
		hbox116.getChildren().add(label16);
		hbox116.setAlignment(Pos.TOP_RIGHT);
		gridPane.add(hbox116, 1, 10);
		
		Label label17 = new Label("-------------------------------------------------");
		HBox hbox17 = new HBox();
		hbox17.getChildren().add(label17);
		hbox17.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox17, 0, 11, 2, 1);
		
		Label label18 = new Label("�ſ�ī�� ��ǥ(����)");
		HBox hbox18 = new HBox();
		hbox18.getChildren().add(label18);
		hbox18.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox18, 0, 12, 2, 1);
		
		Label label19 = new Label("ī���ȣ      " + cust.getCustCardNumber());
		HBox hbox19 = new HBox();
		hbox19.getChildren().add(label19);
		hbox19.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox19, 0, 13, 2, 1);
		
		CustomerDB custDB = new CustomerDB();
		Label label20 = new Label("���ι�ȣ     " + custDB.getMaxId());
		HBox hbox20 = new HBox();
		hbox20.getChildren().add(label20);
		hbox20.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox20, 0, 14, 2, 1);
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/mm/dd    hh:mm:ss");
		Label label25 = new Label("---------------" + sdf.format(today) + "---------------");
		HBox hbox25 = new HBox();
		hbox25.getChildren().add(label25);
		hbox25.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox25, 0, 15, 2, 1);
		
		Text label26 = new Text("�����ͺ��̽��� ���Ӱɱ����԰� �Բ� �ϰ� 10% ���� ���ʽ��� �Բ�!");
		label26.setWrappingWidth(250);
		HBox hbox26 = new HBox();
		hbox26.getChildren().add(label26);
		hbox26.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox26, 0, 16, 2, 1);
		
		Button btn = new Button("OK");
		btn.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {
				try {
					moveToIndex(event);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		HBox hbox27 = new HBox();
		hbox27.getChildren().add(btn);
		hbox27.setAlignment(Pos.TOP_CENTER);
		gridPane.add(hbox27, 0, 17, 2, 1);
		
		startVoice();
		//TODO : ��� ����ް� �����
	}
	
	private void commit() {
		try {
			DBConnection.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void moveToIndex(ActionEvent event) throws IOException {
		commit();
		
		Parent nextPage = FXMLLoader.load(this.getClass().getResource("index.fxml"));
		Scene nextPageScene = new Scene(nextPage);
		Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		thisStage.setScene(nextPageScene);
		thisStage.show();
	}
	
	public void startVoice() {
		Media media = new Media(new Clova("�����ͺ��̽��� ���Ӱɱ����԰� �Բ� �ϰ� 10% ���� ���ʽ��� �Բ�!").fileLoc);  
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	}
	@SuppressWarnings({ "unchecked", "unused" })
	private ListView<Menu> setAndGetList(ArrayList<Menu> orderedList) {
		Set<?> set = new HashSet<>(orderedList);
		if (orderedList != null) { 
			ObservableList<Menu> data = (ObservableList<Menu>) FXCollections.observableArrayList(set);

			listView.setItems(data);
			listView.setCellFactory(new Callback<ListView<Menu>, ListCell<Menu>>() {
				@Override 
				public ListCell<Menu> call(ListView<Menu> data) {
					return new Cell();
				}
			});

			return listView;
		}

		return null;
	}
	
	class Cell extends ListCell<Menu> {
		HBox hbox;
		Label menuName;
		Label menuCtn;
		Label menuPrice;
		ArrayList<Integer> btnItem;

		@Override
		public void updateItem(Menu item, boolean empty) {
			super.updateItem(item, empty);
			
			
			if (item != null) {		
				int itemCtn = MenuController.menuCountAry[item.getMenuId()];
				menuName = new Label(item.getMenuName());
				menuCtn = new Label("  " + itemCtn + "  ");
				int mul = item.getMenuPrice() * itemCtn;
				menuPrice = new Label(mul + "");
				
				hbox = new HBox();
				hbox.setAlignment(Pos.CENTER);
				hbox.getChildren().addAll(menuName, menuCtn, menuPrice);
				setGraphic(hbox);
			}
		}
	}
}
