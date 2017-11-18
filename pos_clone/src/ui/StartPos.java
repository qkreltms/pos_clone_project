package ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.MenuDB;
import model.DataProvider;
import model.Menu;

public class StartPos extends Application {
	private Stage primaryStage; //띄울 fxml파일


	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane rootLayout;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Java & Oracle Team Project : POS");
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(StartPos.class.getResource("signUpPage.fxml")); //TODO menu로 돌려놓기
		rootLayout = (BorderPane) loader.load();
		Scene scene = new Scene(rootLayout);
		
//		BorderPane rootLayout;
//		this.primaryStage = primaryStage;
//		this.primaryStage.setTitle("Java & Oracle Team Project : POS");
//		
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(StartPos.class.getResource("menu.fxml")); //TODO menu로 돌려놓기
//		rootLayout = (BorderPane) loader.load();
//		Scene scene = new Scene(rootLayout);
//		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}