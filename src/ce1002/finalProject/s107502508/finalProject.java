package ce1002.finalProject.s107502508;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class finalProject extends Application {
	
	// declare public stage and scene
	public static Stage mainStage;
	public static Scene mainScene;
	public static MediaPlayer mediaplayer;
	public static Media btMusic;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		if(mediaplayer == null) {
			// play music
			Media media = new Media(getClass().getResource("music.mp3").toString());
			mediaplayer = new MediaPlayer(media);
			mediaplayer.setCycleCount(AudioClip.INDEFINITE);
			mediaplayer.play();
			
			// make music
			btMusic = new Media(getClass().getResource("buttonMusic.mp3").toString());
		}

		// goto Title
		finalProject.mainStage = mainStage;
		FXMLLoader loadder =
		        new FXMLLoader(
		            getClass().getResource("Title.fxml"));
		    Parent main = loadder.load();
		    mainScene = new Scene(main);
		    mainStage.setTitle("¤è¦N¤j¾Ô²Óµß");
		    mainStage.setScene(mainScene);
		    mainStage.setResizable(false);
		    mainStage.show();
	}

}
