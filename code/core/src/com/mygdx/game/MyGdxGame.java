package com.mygdx.game;
//import list and arraylist
import java.util.ArrayList;
import java.util.List;


//import libgdx game stuff
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import baseShip class
import com.mygdx.game.objects.BaseshipObject;
//import bases
import com.mygdx.game.objects.BlueBase;
import com.mygdx.game.objects.BulletBlue;
import com.mygdx.game.objects.BulletRed;
import com.mygdx.game.objects.RedBase;
//import enum shipTypes
import com.mygdx.game.objects.ShipTypes;
//import blue ships
import com.mygdx.game.objects.BlockerShip;
import com.mygdx.game.objects.ShooterShip;
import com.mygdx.game.objects.SuicideShip;
//import red ships
import com.mygdx.game.objects.RedShooterShip;
import com.mygdx.game.objects.RedSuicideShip;
import com.mygdx.game.objects.RedBlockerShip;

public class MyGdxGame extends ApplicationAdapter {
		
		//camera
		private OrthographicCamera camera;
		
		//spritebatch for drawing the sprites on
		private SpriteBatch batch;
		
		//----TEXTURES AND SPRITES DEFINED----\\
		//base
		private RedBase mRedbase = new RedBase();
		private BlueBase mBluebase = new BlueBase();
		
		//background(hope you like it!!!)
		private Texture tex_space;
		private Sprite spr_space;
		
		//lanes
		private static int lane1 = 178-32;//y value
		private static int lane2 = 178; //y value
		private static int lane3 = 178+32;//y value
		private static int blueSelected;
		private static int redSelected;
		
		//keypushes
		private static Boolean Q = false;
		private static Boolean W = false;
		private static Boolean E = false;
		private static Boolean num1 = false;
		private static Boolean num2 = false;
		private static Boolean num3 = false;

		private static Boolean num8 = false;
		private static Boolean num9 = false;
		private static Boolean num0 = false;
		private static Boolean I = false;
		private static Boolean O = false;
		private static Boolean P = false;
		
		//basic ship list
		//arraylist of ships
		//TIP: dont look at the ships. They are for my understanding only (they include no/little comments)
		//blue ship list
		private static List<BaseshipObject> allBShips = new ArrayList<BaseshipObject>();
		//red ship list
		private static List<BaseshipObject> allRShips = new ArrayList<BaseshipObject>();
		//all ships
		private static List<BaseshipObject> allShips = new ArrayList<BaseshipObject>();
		
		//ship cooldown. cool/30 = cooldown in seconds
		private static final int cooldown = 30;
		private static int currentTick;
		
		//resoucesCooldown. same time as the ship cooldown
		private static final int cooldown2 = 60;
		private static int currentTick2;
		
		//resouces variables
		private static int blueMoney;
		private static int redMoney;
		
		//font
		private static BitmapFont font;
		
		
		// EPIC TIP: 0,0 is the lower left hand corner
		@Override
		public void create () {
			//--font stuff--\\
			//make font
			font = new BitmapFont();
			//sets font color
			font.setColor(Color.WHITE);
			
			//--Timer stuff--\\
			//sets currentTick to 0
			currentTick=0;
			
			//--Resources stuff--\\
			//set resources to 20
			blueMoney=20;
			redMoney=20;
			
			//--Ship Stuff--\\
			// Create all ships in the game
			//red first
			//ten suicide ships
			for(int i = 0; i<10; i++) {
				allRShips.add(new RedSuicideShip());
			}
			
			//five shooter ships
			for(int i = 0; i<5; i++) {
				allRShips.add(new RedShooterShip());
			}
			//ten red bullets
			for(int i = 0; i<10; i++) {
				allRShips.add(new BulletRed());
			}
			
			//three blocker ships
			for(int i = 0; i<3; i++) {
				allRShips.add(new RedBlockerShip());
			}
			//then blue
			//ten suicide ships
			for(int i = 0; i<10; i++) {
				allBShips.add(new SuicideShip());
			}
			
			//five shooter ships
			for(int i = 0; i<5; i++) {
				allBShips.add(new ShooterShip());
			}
			//ten blue bullets
			for(int i = 0; i<10; i++) {
				allBShips.add(new BulletBlue());
			}
			
			//three blocker ships
			for(int i = 0; i<3; i++) {
				allBShips.add(new BlockerShip());
			}
			//add stuff to the allships list
			allShips.addAll(allRShips);
			allShips.addAll(allBShips);
			// init the ships
			for(int len = allBShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allBShips.get(i);
				ship.Init();
			}
			for(int len = allRShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allRShips.get(i);
				ship.Init();
			}
			// init the bases
			mRedbase.Init();
			mBluebase.Init();
			
			//set camera
			camera=new OrthographicCamera();
			camera.setToOrtho(false,1024,480);
			batch=new SpriteBatch();
	
			//background for the game
			tex_space = new Texture(Gdx.files.internal("sprites/SPACE!!!!!.png"));
			spr_space = new Sprite(tex_space,0,0,1024,480);
			
			//sets position for stationary things
			spr_space.setPosition(0, 0);
			//red stuff
			mRedbase.setPlace(736, 178);
			//blue stuff
			mBluebase.setPlace(128, 178);		
		}
		
		@Override
		public void dispose(){
			//disposes all the game textures and objects
			batch.dispose();
			tex_space.dispose();
		}

		@Override
		public void render () {
			Gdx.gl.glClearColor(1, 1, 1, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			

			///drawing. The ones drawn first are behind the others
			batch.begin();
			//reset the color, just in case
			batch.setColor(0, 0, 0, 1);
			//draw the background
			spr_space.draw(batch);
			//draw the bases
			mRedbase.show(batch); 
			mBluebase.show(batch);
			//draw the ships
			drawShips(allBShips,batch);
			//draw Money
			if (blueMoney==100) {
				font.draw(batch, "Money:$"+blueMoney+"   MONEY CAP REACHED!!!", 128-32, 178-16);
			}
			else{
				font.draw(batch, "Money:$"+blueMoney, 128, 178-16);
			}
			if (redMoney==100) {
				font.draw(batch, "Money:$"+redMoney+"   MONEY CAP REACHED!!!", 736-32, 178-16);
			}
			else{
				font.draw(batch, "Money:$"+redMoney, 736, 178-16);
			}
			//end the drawing
			batch.end();
			
			//Updates
			updateShips(allShips);
			mRedbase.update(allShips);
			mBluebase.update(allShips);
			//update keys
			updateKeys();
			//get right selected variable
			getLane();
			
			//create ships
			createShips();
			
			//update money
			giveMoney();
		} 
		
		//-------FUNCTIONS-------\\
		private static void updateShips(List<BaseshipObject> allShips){
			for(int len = allBShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allBShips.get(i);
				ship.update(Gdx.graphics.getRawDeltaTime(), allShips);
			}
			for(int len = allRShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allRShips.get(i);
				ship.update(Gdx.graphics.getRawDeltaTime(), allShips);
			}
		}
		
		private static void drawShips(List<BaseshipObject> allShips, SpriteBatch batch){
			for(int len = allRShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allRShips.get(i);
				ship.draw(batch);
			}
			for(int len = allBShips.size(), i = 0; i < len; i++) {
				BaseshipObject ship = allBShips.get(i);
				ship.draw(batch);
			}
		}
		
		private static void getLane(){
			if (num1){
				blueSelected = lane1;
			}
			if (num2){
				blueSelected = lane2;
			}
			if (num3){
				blueSelected = lane3;
			}
			if (num8){
				redSelected = lane1;
			}
			if (num9){
				redSelected = lane2;
			}
			if (num0){
				redSelected = lane3;
			}
		}
		
		private static void updateKeys(){
			//updates ship keys 
			if(Gdx.input.isKeyPressed(Input.Keys.Q)){
				 Q = true;
			 }
			 else{
				 Q = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.W)){
				 W = true;
			 }
			 else{
				 W = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.E)){
				 E = true;
			 }
			 else{
				 E = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.I)){
				 I = true;
			 }
			 else{
				 I = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.O)){
				 O = true;
			 }
			 else{
				 O = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.P)){
				 P = true;
			 }
			 else{
				 P = false;
			 }
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
				 num1 = true;
			 }
			 else{
				 num1 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
				 num2 = true;
			 }
			 else{
				 num2 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
				 num3 = true;
			 }
			 else{
				 num3 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_8)){
				 num8 = true;
			 }
			 
			 else{
				 num8 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_9)){
				 num9 = true;
			 }
			 else{
				 num9 = false;
			 }
			 
			 if(Gdx.input.isKeyPressed(Input.Keys.NUM_0)){
				 num0 = true;
			 }
			 else{
				 num0 = false;
			 }
			 
		}
		public static void createShips(){
			//cooldown management
			if (currentTick < cooldown){
				currentTick++;
				return;
			}
			currentTick = 0;	
			
			//actually make ships:
			//----BLUE----\\
			//suicide ship
			if (Q==true){
				//goes through the list of ships
				for(int len = allBShips.size(), i = 0; i < len; i++) {
					//gets current ship
					BaseshipObject ship = allBShips.get(i);
					//if it is a suicide ship
					if (ship.getType().equals(ShipTypes.SuicideShip)){
						//if you have enough money for it
						if (blueMoney>=5){	
							//if its create function returns true, break
							if (ship.create(blueSelected,0)==true){
								Q=false;
								blueMoney-=5;
								break;
							}
							
						}
					}
				}
			}
			//shooter ship
			//same as suicide ship
			if (W==true){
				for(int len = allBShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allBShips.get(i);
					if (ship.getType().equals(ShipTypes.ShooterShip)){
						//if you have enough money for it
						if (blueMoney>=10){	
							//if its create function returns true, break
							if (ship.create(blueSelected,0)==true){
								W=false;
								blueMoney-=10;
								break;
							}
							
						}
					}
				}
			}
			//blocker ship
			if (E==true){
				for(int len = allBShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allBShips.get(i);
					if (ship.getType().equals(ShipTypes.BlockerShip)){
						//if you have enough money for it
						if (blueMoney>=15){	
							//if its create function returns true, break
							if (ship.create(blueSelected,0)==true){
								E=false;
								blueMoney-=15;
								break;
							}
							
						}
					}
				}
			}
			//----RED----\\
			//suicide ship
			if (I==true){
				//goes through the list of ships
				for(int len = allRShips.size(), i = 0; i < len; i++) {
					//gets current ship
					BaseshipObject ship = allRShips.get(i);
					//if it is a suicide ship
					if (ship.getType().equals(ShipTypes.SuicideShip)){
						//if you have enough money for it
						if (redMoney>=5){	
							//if its create function returns true, break
							if (ship.create(redSelected,0)==true){
								I=false;
								redMoney-=5;
								break;
							}
							
						}
					}
				}
			}
			//shooter ship
			//same as suicide ship
			if (O==true){
				for(int len = allRShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allRShips.get(i);
					if (ship.getType().equals(ShipTypes.ShooterShip)){
						//if you have enough money for it
						if (redMoney>=10){	
							//if its create function returns true, break
							if (ship.create(redSelected,0)==true){
								O=false;
								redMoney-=10;
								break;
							}
							
						}
					}
				}
			}
			//blocker ship
			if (P==true){
				for(int len = allRShips.size(), i = 0; i < len; i++) {
					BaseshipObject ship = allRShips.get(i);
					if (ship.getType().equals(ShipTypes.BlockerShip)){
						//if you have enough money for it
						if (redMoney>=15){	
							//if its create function returns true, break
							if (ship.create(redSelected,0)==true){
								P=false;
								redMoney-=15;
								break;
							}
							
						}
					}
				}
			}
		}//end of shipCreate
		public static void giveMoney(){
			if (currentTick2 < cooldown2){
				currentTick2++;
				return;
			}
			currentTick2 = 0;
			
			blueMoney+=5;
			redMoney+=5;
			
			//money cap
			if (blueMoney>100) {
				blueMoney=100;
			}
			if (redMoney>100) {
				redMoney=100;
			}
		}
}
