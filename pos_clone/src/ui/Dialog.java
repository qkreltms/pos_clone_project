package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Menu;

public class Dialog implements EventHandler<ActionEvent>{
	Menu item = null;
	TextField tfName;
	TextField tfPrice;
	TextField tfShopId;
	Stage window = null;
	
	public void displyUpdete(Menu item) {
		this.item = item;
		window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("update");
		window.setMinWidth(250);
		
		tfName = new TextField(item.getMenuName());
		tfPrice = new TextField(item.getMenuPrice() + "");
		tfShopId = new TextField(item.getShopId() + ""); //TODO choice box로 선택하는 것으로 바꾸기
		
		Button btnOK = new Button("OK");
		btnOK.setOnAction(this); 

		VBox layout = new VBox();
		layout.getChildren().addAll(tfName, tfPrice, tfShopId, btnOK);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
	
	private void getMenuDataFromOtherStage(Menu menu) {
		ModifyController.menu = menu;
	}

	@Override
	public void handle(ActionEvent event) {
		item.setMenuName(tfName.getText());
		item.setMenuPrice(Integer.parseInt(tfPrice.getText()));
		item.setShopId(Integer.parseInt(tfShopId.getText()));
		getMenuDataFromOtherStage(item);
		window.hide();
	}
}
