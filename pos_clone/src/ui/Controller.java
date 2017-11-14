package ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import data.DataProvider;
import data.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import logic.MenuDB;

public class Controller implements Initializable {
	private ListView<Menu> list = new ListView<>();
	ObservableList<Menu> data;
	@FXML Label selectedMenu;
	@FXML BorderPane rootLayout;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectedMenu.setText("test");
		rootLayout.setTop(setAndGetLabel("ÀÌµð¾ß", 32));
		try {
			rootLayout.setCenter(setAndGetList());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Label setAndGetLabel(String title, int fontSize) {
		Label label = new Label(title);
		label.setMaxWidth(Double.MAX_VALUE);
		label.setAlignment(Pos.CENTER);
		label.setFont(Font.font("system", FontWeight.BOLD, fontSize));

		return label;
	}

	ArrayList<Menu> menuList = new ArrayList<>();
	public void setAndGetBottomLabel(Menu menu, boolean ctn) {
		String textBottom = "";
		int sum = 0;
		
		if (ctn) {
			menuList.add(menu);
			for (Menu s : menuList) {
				if (s != null) {
					textBottom = textBottom + s.getMenuName() + s.getMenuPrice() + "\n";
					sum = sum + s.getMenuPrice();
				}
			}
			selectedMenu.setText(textBottom + "\n" + "ÃÑ ÇÕ = " + sum);
			sum = 0;
			textBottom = "";

		} else {
			if (menuList.contains(menu)) {
				menuList.set(menuList.indexOf(menu), null);
				for (Menu s : menuList) {
					if (s != null) {
						textBottom = textBottom + s.getMenuName() + s.getMenuPrice() + "\n";
						sum = sum + s.getMenuPrice();
					}
				}
				selectedMenu.setText(textBottom + "\n" + "ÃÑ ÇÕ = " + sum);
				sum = 0;
				textBottom = "";
			}
		}
	}

	@SuppressWarnings("unchecked")
	public ListView<Menu> setAndGetList() throws SQLException {
		DataProvider d = new MenuDB();
		ArrayList<Menu> dataList = new ArrayList<>();
		dataList.addAll((Collection<? extends Menu>) d.getAllData());
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

}
