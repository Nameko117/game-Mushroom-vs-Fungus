package ce1002.finalProject.s107502508.character;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Enemy extends Entity {
	
	int score;

	public Enemy(int ID, int HP, int ATK, int walkSpeed, ImageView img, ImageView atkImg, int score) {
		super(ID, HP, ATK, walkSpeed, img, atkImg);
		this.score = score;
	}
	
	public boolean walk(LinkedList<Player> _players, ImageView _playerTower) {
		if(this.getImg().getLayoutX()+this.getImg().getFitWidth() >= _playerTower.getLayoutX()) {
			return false;
		}
		else {
			for(Player player : _players) {
				if(this.getImg().getLayoutX()+this.getImg().getFitWidth() >= player.getImg().getLayoutX()) {
					return false;
				}
			}
		}
		this.getImg().setLayoutX(this.getImg().getLayoutX()+this.walkSpeed);
		return true;
	}
	
	public int getScore() {
		return this.score;
	}

	public void Active(Pane _field, LinkedList<Player> _player, ArrayList<ImageView> _atk,
			ArrayList<Integer> _atkTime) {
		this.atkCold -= 1;
		if(this.atkCold <= 0) {
			switch(ID) {
			case 1:
			case 2:
			case 3:
			case 5:
				for(Player player : _player) {
					if(this.getImg().getLayoutX()+this.getImg().getFitWidth() >= player.getImg().getLayoutX()) {
						if(this.getAtkCold() <= 0) {
							_atk.add(this.Attack(_field, player.getImg()));
							_atkTime.add(50);
							player.setHP(player.getHP()-this.ATK);
							break;
						}
					}
				}
				break;
			case 4:
				for(Player player : _player) {
					if(this.getImg().getLayoutX()+this.getImg().getFitWidth() >= player.getImg().getLayoutX()) {
						if(this.getAtkCold() <= 0) {
							_atk.add(this.Attack(_field, player.getImg()));
							_atkTime.add(50);
							player.setHP(player.getHP()-this.ATK);
						}
					}
				}
				break;
			}
		}
	}

	public ImageView Attack(Pane _field, ImageView _playerTower) {
		ImageView newAtk = new ImageView(this.atkImg.getImage());
		newAtk.setFitWidth(this.atkImg.getFitWidth());
		newAtk.setFitHeight(this.atkImg.getFitHeight());
		newAtk.setLayoutX(this.getImg().getLayoutX() + this.getImg().getFitWidth()/2);
		if(this.ID == 4 || this.ID == 5) {
			newAtk.setLayoutY(this.getImg().getLayoutY()-20);
		}
		else {
			newAtk.setLayoutY(this.getImg().getLayoutY()+20);
		}

		_field.getChildren().add(newAtk);
		this.atkCold = 250;
		
		return newAtk;
	}
}
