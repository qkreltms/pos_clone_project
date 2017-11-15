package ui;

import data.DataProvider;
import data.Menu;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.MenuDB;

public class StartPos extends Application {
	private Stage primaryStage; //¶ç¿ï fxmlÆÄÀÏ
	private BorderPane rootLayout;

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Java & Oracle Team Project : POS");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StartPos.class.getResource("menu.fxml"));
		rootLayout = (BorderPane) loader.load();
		Scene scene = new Scene(rootLayout);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}