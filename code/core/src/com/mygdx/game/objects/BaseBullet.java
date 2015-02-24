package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class BaseBullet {
	
	protected Rectangle Mask;
	protected Sprite Sprite;
	protected Texture Texture;
	protected float X;
	protected float Y;
	protected int Speed;
	protected Boolean Blue;
	protected Boolean Created;
	protected int ShipSpeed;
	
	public BaseBullet(){
		Created = false;
	}
	
	public void create(float Xposition, float Yposition, int speed, int shipspeed, Boolean BBlue){
		if (Created){
			return;
		}
		Blue = BBlue;
		Created = true;
		Speed = speed;
		ShipSpeed = shipspeed;
		Mask = new Rectangle (0.0f,0.0f,32.0f,16.0f);
		if (Blue){
			Texture = new Texture(Gdx.files.internal("sprites/BlueBullet.png"));
		}
		else {
			Texture = new Texture(Gdx.files.internal("sprites/RedBullet.png"));
		}
		Sprite = new Sprite(Texture,0,0,32,16);
		this.setPlace(Xposition, Yposition);
	}
	
	public void update(){
		if (!Created){
			return;
		}
		
		if (Mask == null){
			Mask = new Rectangle (0.0f,0.0f,32.0f,16.0f);
		}
		if (Blue) {
			this.setPlace(X + (ShipSpeed + Speed), Y);
		}
		else {
			this.setPlace(X - (ShipSpeed + Speed), Y);
		}
		//if out of play
		if (X<-64){
			Created=false;
		}
		if (X>1024){
			Created=false;
		}
	}
	
	public void setPlace(float xPosition,float yPosition){
		Mask.x = xPosition;
		Mask.y = yPosition;
		Sprite.setPosition(xPosition, yPosition);
		X = xPosition;
		Y = yPosition;
	}
}