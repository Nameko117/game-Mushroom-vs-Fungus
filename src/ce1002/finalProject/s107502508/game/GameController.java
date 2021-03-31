package ce1002.finalProject.s107502508.game;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

import ce1002.finalProject.s107502508.finalProject;
import ce1002.finalProject.s107502508.character.Enemy;
import ce1002.finalProject.s107502508.character.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameController implements Initializable {
	@FXML
	public Pane _field;
	public Pane _playerTowerHP;
	public Pane _enemyTowerHP;
	public Text _NP;
	public Text _word;
	public Text _win;
	public Text _lose;
	public ImageView _player1;
	public ImageView _player2;
	public ImageView _player3;
	public ImageView _player4;
	public ImageView _player5;
	public ImageView _playerAtk1;
	public ImageView _playerAtk2;
	public ImageView _playerAtk3;
	public ImageView _playerAtk4;
	public ImageView _playerAtk5;
	public ImageView _playerTower;
	public ImageView _enemy1;
	public ImageView _enemy2;
	public ImageView _enemy3;
	public ImageView _enemy4;
	public ImageView _enemy5;
	public ImageView _enemyAtk1;
	public ImageView _enemyAtk2;
	public ImageView _enemyAtk3;
	public ImageView _enemyAtk4;
	public ImageView _enemyAtk5;
	public ImageView _enemyTower;
	public ImageView _ground1;
	public ImageView _ground2;
	
	public LinkedList<Player> _player = new LinkedList<Player>();
	public LinkedList<Enemy> _enemy = new LinkedList<Enemy>();
	public ArrayList<ImageView> _atk;
	public ArrayList<Integer> _atkTime;
	
	public int money = 0;
	public int moneyCold = 50;
	public int time = 1;
	public int playerTowerHP = 200;
	public int enemyTowerHP = 200;
	
	public enum difficult{easy, normal, hard};
	public static difficult level;
	
	// Game Start
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ArrayList<ImageView> _atk = new ArrayList<ImageView>();
		ArrayList<Integer> _atkTime = new ArrayList<Integer>();
		
		// call enemy
		switch(level) {
		case easy:
			money = 100;
			enemyTowerHP = 50;
			decideLevel(3);
			break;
		case normal:
			money = 100;
			decideLevel(2);
			break;
		case hard:
			decideLevel(1);
			playerTowerHP = 10;
			break;
		}
		
		Timeline playing = new Timeline(new KeyFrame(Duration.millis(20),(e)-> {
			// display money and HP
			if(playerTowerHP<0) {
				playerTowerHP = 0;
			}
			else if(enemyTowerHP<0) {
				enemyTowerHP = 0;
			}
			_playerTowerHP.setPrefWidth(300*playerTowerHP/200);
			_enemyTowerHP.setPrefWidth(300*enemyTowerHP/200);
			_NP.setText("NP：" + money);
			
			// detect ending
			if(!detectEnding()) {
				// add money
				if(moneyCold == 0) {
					money += 1;
					moneyCold = 50;
				}
				else {
					moneyCold -= 1;
				}

				// remove attack
				for(int i=0;i<_atk.size();i++) {
					if(i>=_atk.size()) {
						break;
					}
					if(_atkTime.get(i) <= 0) {
						_field.getChildren().remove(_atk.get(i));
						_atk.remove(i);
						_atkTime.remove(i);
						i -=1;
					}
					else {
						_atkTime.set(i, _atkTime.get(i)-1);
					}
				}
				
				ArrayList<Player> players = new ArrayList<Player>(_player);
				ArrayList<Enemy> enemys = new ArrayList<Enemy>(_enemy);
				// player action
				for(Player player : players) {
					if(player.getHP()<=0) {
						_player.remove(player);
						_field.getChildren().remove(player.getImg());
						continue;
					}
					if(!player.walk(_enemy, _enemyTower)) {
						player.Active(_field, _player, _enemy, _atk, _atkTime);
						if(player.getImg().getLayoutX() <= _enemyTower.getLayoutX()+_enemyTower.getFitWidth()) {
							if(player.getAtkCold() <= 0 && player.getID()!=4) {
								_atk.add(player.Attack(_field, _enemyTower));
								_atkTime.add(50);
								enemyTowerHP -= player.getATK();
							}
						}
					}
				}
				
				//enemy action
				for(Enemy enemy : enemys) {
					if(enemy.getHP()<=0) {
						money += enemy.getScore();
						_enemy.remove(enemy);
						_field.getChildren().remove(enemy.getImg());
						continue;
					}
					if(!enemy.walk(_player, _playerTower)) {
						enemy.Active(_field, _player, _atk, _atkTime);
						if(enemy.getImg().getLayoutX()+enemy.getImg().getFitWidth() >= _playerTower.getLayoutX()) {
							if(enemy.getAtkCold() <= 0) {
								_atk.add(enemy.Attack(_field, _playerTower));
								_atkTime.add(50);
								playerTowerHP -= enemy.getATK();
							}
						}
					}
				}
			}
		}));
		playing.setCycleCount(Timeline.INDEFINITE);
		playing.play();
	}
	
	// detect ending
	public boolean detectEnding() {
		if(playerTowerHP<=0) {
			_lose.setVisible(true);
			return true;
		}
		else if(enemyTowerHP<=0) {
			_win.setVisible(true);
			return true;
		}
		else {
			return false;
		}
	}
	
	// choose level to call enemy
	public void decideLevel(int speed) {
		
		Timeline callEnemy = new Timeline(new KeyFrame(Duration.millis(1000),(e)-> {
			if(time%(5*speed) == 0) {
				addEnemy(1);
			}
			if(time%(10*speed) == 0) {
				addEnemy(2);
			}
			if(time%(20*speed) == 0) {
				addEnemy(3);
			}
			if(time%(30*speed) == 0) {
				addEnemy(4);
			}
			if(time%(60*speed) == 0) {
				addEnemy(5);
			}
			time += 1;
		}));
		callEnemy.setCycleCount(Timeline.INDEFINITE);
		callEnemy.play();
	}
	
	// add enemy
	public void addEnemy(int ID) {
		ImageView newEImg = null;
		Enemy newE = null;
		switch(ID) {
		case 1:
			newEImg = newImg(_enemy1);
			newE = new Enemy(1, 20, 3, 3, newEImg, _enemyAtk1, 3);
			break;
		case 2:
			newEImg = newImg(_enemy2);
			newE = new Enemy(2, 10, 7, 2, newEImg, _enemyAtk2, 5);
			break;
		case 3:
			newEImg = newImg(_enemy3);
			newE = new Enemy(3, 40, 1, 2, newEImg, _enemyAtk3, 10);
			break;
		case 4:
			newEImg = newImg(_enemy4);
			newE = new Enemy(4, 10, 3, 2, newEImg, _enemyAtk4, 15);
			break;
		case 5:
			newEImg = newImg(_enemy5);
			newE = new Enemy(5, 20, 30, 1, newEImg, _enemyAtk5, 30);
			break;
		}
		newEImg = setEnemyPosition(newEImg);
		_field.getChildren().add(newEImg);
		_enemy.add(newE);
	}
	public ImageView setEnemyPosition(ImageView img) {
		img.setLayoutX(_enemyTower.getLayoutX() + _enemyTower.getFitWidth() - img.getFitWidth());
		img.setLayoutY(_ground1.getLayoutY() - img.getFitHeight() + 30);
		return img;
	}
	
	// add player
	public void player1Pressed() {
		if(money>=3) {
			MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
			buttonMusic.play();
			ImageView newPImg = newImg(_player1);
			newPImg = setPlayerPosition(newPImg);
			_field.getChildren().add(newPImg);
			Player newP = new Player(1, 20, 3, 3, newPImg, _playerAtk1);
			_player.add(newP);
			money -= 3;
		}
	}
	public void player2Pressed() {
		if(money>=8) {
			MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
			buttonMusic.play();
			ImageView newPImg = newImg(_player2);
			newPImg = setPlayerPosition(newPImg);
			_field.getChildren().add(newPImg);
			Player newP = new Player(2, 10, 3, 2, newPImg, _playerAtk2);
			_player.add(newP);
			money -= 8;
		}
	}
	public void player3Pressed() {
		if(money>=15) {
			MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
			buttonMusic.play();
			ImageView newPImg = newImg(_player3);
			newPImg = setPlayerPosition(newPImg);
			_field.getChildren().add(newPImg);
			Player newP = new Player(3, 40, 1, 2, newPImg, _playerAtk3);
			_player.add(newP);
			money -= 15;
		}
	}
	public void player4Pressed() {
		if(money>=25) {
			MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
			buttonMusic.play();
			ImageView newPImg = newImg(_player4);
			newPImg = setPlayerPosition(newPImg);
			_field.getChildren().add(newPImg);
			Player newP = new Player(4, 10, 2, 2, newPImg, _playerAtk4);
			_player.add(newP);
			money -= 25;
		}
	}
	public void player5Pressed() {
		if(money>=50) {
			MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
			buttonMusic.play();
			ImageView newPImg = newImg(_player5);
			newPImg = setPlayerPosition(newPImg);
			_field.getChildren().add(newPImg);
			Player newP = new Player(5, 20, 30, 1, newPImg, _playerAtk5);
			_player.add(newP);
			money -= 50;
		}
	}
	public ImageView setPlayerPosition(ImageView img) {
		img.setLayoutX(_playerTower.getLayoutX());
		img.setLayoutY(_ground1.getLayoutY() - img.getFitHeight() + 30);
		return img;
	}
	public ImageView newImg(ImageView sampleImg) {
		ImageView newImg = new ImageView(sampleImg.getImage());
		newImg.setFitHeight(sampleImg.getFitHeight());
		newImg.setFitWidth(sampleImg.getFitWidth());
		return newImg;
	}
	
	// when enter or exit button will change _word
	public void onExitButton() throws Exception{
		_word.setText("");
	}
	public void onEnterButton1() throws Exception{
		_word.setText("菇菇：需要3NP\n只是普通的菇菇，使用普通的單體攻擊，走得很快。");
	}
	public void onEnterButton2() throws Exception{
		_word.setText("激辣菇菇：需要8NP\n喜歡甜食，可以一次攻擊很多敵人。");
	}
	public void onEnterButton3() throws Exception{
		_word.setText("大塊頭菇菇：需要15NP\n血很厚，使用微弱的單體攻擊。");
	}
	public void onEnterButton4() throws Exception{
		_word.setText("天使菇菇：需要25NP\n沒有攻擊力，能夠治療同伴。");
	}
	public void onEnterButton5() throws Exception{
		_word.setText("小小菇菇：需要50NP\n非常可愛，攻擊力超群，走得很慢。");
	}
	
	
	// GoBack to choose level
	public void onBackPressed(ActionEvent e) throws Exception{
		MediaPlayer buttonMusic = new MediaPlayer(finalProject.btMusic);
		buttonMusic.play();
		FXMLLoader loadder = new
			      FXMLLoader(
			        getClass().getResource("../level/Level.fxml"));
		
		Parent level = loadder.load();
	    Scene levelScene = new Scene(level);
	    
	    finalProject.mainStage.setScene(levelScene);
	}
}
