package ui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.DBConnection;
import tts.Clova;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;

public class SignUpController implements Initializable{
	@FXML PasswordField pfInput;
	@FXML TextField tfInput;
	@FXML Label textError;
	//가비지컬렉터가 인스턴스 삭제하는 것으로 보임 밖으로 빼줌.
	Media media;
	MediaPlayer mediaPlayer;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startVoice();
	}
	
	public void startVoice() {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh시 mm분 ss초");
		media = new Media(new Clova("현재시간은 " + sdf.format(today) + "입니다. 좋은하루되세요").fileLoc);  
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
	
	}

	@FXML public void nextScene(ActionEvent event) throws IOException {
		if (checkIdAndPw()) {
			Parent nextPage = FXMLLoader.load(this.getClass().getResource("index.fxml"));
			Scene nextPageScene = new Scene(nextPage);
			Stage thisStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			thisStage.setScene(nextPageScene);
			thisStage.show();
		}
	}

	public boolean checkIdAndPw() {
		String id = "system";
		String pw = "111111";

		//TODO : 제대로 만들기
		if (id.equals(tfInput.getText()) && pw.equals(pfInput.getText())) {
			//DBConnection.userId = id;
			//DBConnection.pwd = pw;
			return true;
		} else {
			textError.setText("Id또는 Pw가 일치하지 않습니다.");
		}

		return false;
	}

}
