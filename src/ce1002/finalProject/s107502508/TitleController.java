package ce1002.finalProject.s107502508;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

public class TitleController {
	
	// goto choose level
	public void onStartPressed(ActionEvent e) throws Exception{
		MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
		buttonMusic.play();
		FXMLLoader loadder = new
			      FXMLLoader(
			        getClass().getResource("level/Level.fxml"));
		
		Parent level = loadder.load();
	    Scene levelScene = new Scene(level);
	    
	    finalProject.mainStage.setScene(levelScene);
	}
	
	// close window
	public void onExitPressed(ActionEvent e) throws Exception{
		MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
		buttonMusic.play();
		finalProject.mainStage.close();
	}
}
