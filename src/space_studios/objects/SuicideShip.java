package space_studios.objects;


import space_studios.objects.BaseshipObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class SuicideShip extends BaseshipObject {

	public ShipTypes getType() {
		return ShipTypes.SuicideShip; 
	}
	
	public SuicideShip() {
		super();
	}
	
	public void Init() {
		Life = Constants.sShipLife;
		Damage = Constants.sShipDam;
		Mask = new Rectangle (0.0f,0.0f,64.0f,32.0f);
		Texture = new Texture(Gdx.files.internal("assets/sprites/Ships & Bases/Blue Sprites/Ships/Basic Suicide Drone.png"));
		Sprite = new Sprite(Texture,0,0,64,32);
		Blue=true; 
		Speed=4;
		this.setPlace(1024, 480);
	}
	public void set() {
		Life = Constants.sShipLife;
	}
}