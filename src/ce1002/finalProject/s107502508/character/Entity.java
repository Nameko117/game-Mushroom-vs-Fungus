package ce1002.finalProject.s107502508.character;

import javafx.scene.image.ImageView;

public abstract class Entity {
	int ID;
	int HP;
	int ATK;
	int walkSpeed;
	int atkCold;
	ImageView img;
	ImageView atkImg;
	
	public Entity(int ID, int HP, int ATK, int walkSpeed, ImageView img, ImageView atkImg) {
		this.ID = ID;
		this.HP = HP;
		this.ATK = ATK;
		this.walkSpeed = walkSpeed;
		this.img = img;
		this.atkImg = atkImg;
		atkCold = 0;
	}
	
	public int getID() {
		return ID;
	}
	
	public ImageView getImg() {
		return img;
	}
	
	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public int getAtkCold() {
		return atkCold;
	}
	
	public int getHP() {
		return this.HP;
	}
	
	public int getATK() {
		return this.ATK;
	}
	
	public int getWalkSpeed() {
		return this.walkSpeed;
	}
}
