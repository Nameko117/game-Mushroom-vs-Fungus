package ce1002.finalProject.s107502508.level;

import ce1002.finalProject.s107502508.finalProject;
import ce1002.finalProject.s107502508.game.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;

public class LevelController {

	// choose level to start game
	public void onEasyPressed(ActionEvent e) throws Exception {
		MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
		buttonMusic.play();
		GameController.level = GameController.difficult.easy;
		
		FXMLLoader loadder = new
			      FXMLLoader(
			        getClass().getResource("../game/Game.fxml"));
		
		Parent game = loadder.load();
	    Scene gameScene = new Scene(game);
	    
	    finalProject.mainStage.setScene(gameScene);
	}
	
	public void onNormalPressed(ActionEvent e) throws Exception {
		MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
		buttonMusic.play();
		GameController.level = GameController.difficult.normal;
		
		FXMLLoader loadder = new
			      FXMLLoader(
			        getClass().getResource("../game/Game.fxml"));
		
		Parent game = loadder.load();
	    Scene gameScene = new Scene(game);
	    
	    finalProject.mainStage.setScene(gameScene);
	}
	
    public void onHardPressed(ActionEvent e) throws Exception {
    	MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
		buttonMusic.play();
    	GameController.level = GameController.difficult.hard;
    	
    	FXMLLoader loadder = new
			      FXMLLoader(
			        getClass().getResource("../game/Game.fxml"));
		
		Parent game = loadder.load();
	    Scene gameScene = new Scene(game);
	    
	    finalProject.mainStage.setScene(gameScene);
	}
    
    // back to title
    public void onBackPressed(ActionEvent e) throws Exception {
    	MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
		buttonMusic.play();
    	FXMLLoader loadder = new
			      FXMLLoader(
			        getClass().getResource("../Title.fxml"));
		
		Parent title = loadder.load();
	    Scene titleScene = new Scene(title);
	    
	    finalProject.mainStage.setScene(titleScene);
	}
}
