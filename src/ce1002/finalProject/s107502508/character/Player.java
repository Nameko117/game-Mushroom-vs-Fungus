package ce1002.finalProject.s107502508.character;

import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Player extends Entity {

	public Player(int ID, int HP, int ATK, int walkSpeed, ImageView img, ImageView atkImg) {
		super(ID, HP, ATK, walkSpeed, img, atkImg);
	}
	
	public void Active(Pane _field, LinkedList<Player> _players, LinkedList<Enemy> _enemys, ArrayList<ImageView> _atk, ArrayList<Integer> _atkTime) {
		this.atkCold -= 1;
		if(this.atkCold <= 0) {
			switch(ID) {
			case 1:
			case 3:
			case 5:
				for(Enemy enemy : _enemys) {
					if(this.getImg().getLayoutX() <= enemy.getImg().getLayoutX()+enemy.getImg().getFitWidth()) {
						if(this.getAtkCold() <= 0) {
							_atk.add(this.Attack(_field, enemy.getImg()));
							_atkTime.add(50);
							enemy.setHP(enemy.getHP()-this.ATK);
							break;
						}
					}
				}
				break;
			case 2:
				for(Enemy enemy : _enemys) {
					if(this.getImg().getLayoutX() <= enemy.getImg().getLayoutX()+enemy.getImg().getFitWidth()) {
						if(this.getAtkCold() <= 0) {
							_atk.add(this.Attack(_field, enemy.getImg()));
							_atkTime.add(50);
							enemy.setHP(enemy.getHP()-this.ATK);
						}
					}
				}
				break;
			case 4:
				for(Player player : _players) {
					if(player.equals(this)) {
						continue;
					}
					_atk.add(this.Attack(_field, player.getImg()));
					_atkTime.add(50);
					player.setHP(player.getHP()+this.ATK);
					break;
				}
				break;
			}
		}
	}
	
	public ImageView Attack(Pane _field, ImageView Img) {
		ImageView newAtk = new ImageView(this.atkImg.getImage());
		newAtk.setFitWidth(this.atkImg.getFitWidth());
		newAtk.setFitHeight(this.atkImg.getFitHeight());
		if(this.ID == 4) {
			newAtk.setLayoutX(Img.getLayoutX()-20);
		}
		else
		{
			newAtk.setLayoutX(this.getImg().getLayoutX() - this.getImg().getFitWidth());
		}
		if(this.ID == 5) {
			newAtk.setLayoutY(this.getImg().getLayoutY() - 50);
		}
		else {
			newAtk.setLayoutY(this.getImg().getLayoutY());
		}
		
		_field.getChildren().add(newAtk);
		this.atkCold = 250;
		
		return newAtk;
	}
	
	public boolean walk(LinkedList<Enemy> _enemys, ImageView _enemyTower) {
		if(this.getImg().getLayoutX() <= _enemyTower.getLayoutX()+_enemyTower.getFitWidth()) {
			return false;
		}
		else {
			for(Enemy enemy : _enemys) {
				if(this.getImg().getLayoutX() <= enemy.getImg().getLayoutX()+enemy.getImg().getFitWidth()) {
					return false;
				}
			}
		}
		this.getImg().setLayoutX(this.getImg().getLayoutX()-this.walkSpeed);
		return true;
	}
}
