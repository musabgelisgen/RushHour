package com.mygdx.game;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;

public class HumanVsHuman extends BaseScreen {

	TextButton returnMenu;
	TextField field;
	Board leftboard,middleboard,rightboard;
	int leftX,leftY,middleX,middleY,rightX,rightY;
	int leftwidth,middlewidth,rightwidth;
	int widthUnit;
	int mapHeight;	
	int mapWidth;
	int boardX,boardY,boardWidth,boardHeight;
	ArrayList<Car> cars;
	int[][] gameTable;
	int m,n;
	int turn;
	boolean[] players_played;
	private int moveCount;
	int targetMove;	//it will be set by the drawen card but it will be set 3 at the moment
	Board[] boards;
	TextButton drawCard;
	int paddingW,paddingH;
	public HumanVsHuman(BaseGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		drawCard=new TextButton("Draw a Card", game.skin, "buttonStyle");
		boards=new Board[3];
		turn=1;
		targetMove=3;
		players_played=new boolean[2];
		players_played[0]=false;
		players_played[1]=true;
		cars=new ArrayList<Car>();
		gameTable=new int[18][14];
		m=gameTable.length/3;
		n=gameTable[0].length;
		paddingW=50;
		paddingH=30;
		widthUnit=(VIEW_WIDTH-2*paddingW)/14;
		mapWidth=14*widthUnit;
		leftwidth=5*widthUnit;
		middlewidth=4*widthUnit;
		rightwidth=5*widthUnit;
		mapHeight=(VIEW_HEIGHT-2*paddingH)/3;
		leftX=paddingW;
		middleX=leftX+leftwidth;
		rightX=middleX+middlewidth;
		leftY=paddingH+mapHeight;
		middleY=leftY;
		rightY=middleY;
		
		boardX=leftX;
		boardY=leftY-mapHeight;
		boardWidth=mapWidth;
		boardHeight=3*mapHeight;
		
		leftboard=new Board(0,6,5,6,leftX,leftY);
		middleboard=new Board(5,6,4,6,middleX,middleY);
		rightboard=new Board(9,6,5,6,rightX,rightY);
		leftboard.setTexture(new Texture("board.jpeg"));
		rightboard.setTexture(new Texture("board.jpeg"));
		middleboard.setTexture(new Texture("board.jpeg"));
		returnMenu=new TextButton("Back", game.skin, "buttonStyle3");
		returnMenu.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				MainMenu.start=false;
				game.skin.get("buton",Sound.class).play();
				return true;
		}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				game.setScreen(new MultiPlayer(game));
			}
			});
		boards[0]=leftboard;
		boards[1]=middleboard;
		boards[2]=rightboard;
		uiStage.addActor(leftboard);
		uiStage.addActor(rightboard);
		uiStage.addActor(middleboard);
		createCars(cars);
		for(int i=0;i<cars.size();i++){
			for(int j=3*m-cars.get(i).y-cars.get(i).height;j<3*m-cars.get(i).y;j++)
				for(int k=cars.get(i).x;k<cars.get(i).x+cars.get(i).width;k++){
					gameTable[j][k]=i+1;
				}
		}
		for(int i=0;i<gameTable.length;i++)
			for(int j=0;j<gameTable[0].length;j++){
				if(i<m)
					gameTable[i][j]=-1;
				else if(i>2*m-1)
					gameTable[i][j]=-1;
			}
		
		
		
		for(final Car carx:cars){
			
			carx.addListener(new InputListener(){
			
				
				 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
							carx.firstTouchX=(int) (x/(mapWidth/n));
							carx.firstTouchY=(int) (y/(mapHeight/m));
							carx.lastX=carx.x+carx.firstTouchX;
							carx.lastY=carx.y+carx.height-carx.firstTouchY;
							if(carx.id!=3-turn)
								carx.pressed=true;
						
						return true;
					}
						public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
							carx.pressed=false;
							
						}
			 });
			}
		//board action listeners
		for(final Board b:boards){
			b.addListener(new InputListener()
			{
				public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
					b.firstTouchX=(int) (x/(mapWidth/n));
					b.firstTouchY=b.height-(int) (y/(mapHeight/m))-1;
					System.out.println(b.firstTouchY);
					//b.lastX=b.firstTouchX+b.x;
					//b.lastY=b.firstTouchY+b.y;
					b.pressed=true;
					return true;
			}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					b.pressed=false;
				}
				});
		}
		
		
			
		
		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(returnMenu);
		leftboard.setVisible(true);
		rightboard.setVisible(true);
		middleboard.setVisible(true);
		uiStage.addActor(leftboard);
		uiStage.addActor(rightboard);
		uiStage.addActor(middleboard);
		uiStage.addActor(drawCard);
		for(Board b:boards)
			b.setPosition(b.leftX,b.leftY);
		//uiTable.add(leftboard);
		//uiTable.add(rightboard);
		//uiTable.add(middleboard);
		

	}
	public void createCars(ArrayList<Car> list){
		Car car1=new Car(0,3,11,2,1,0);
		Car car2=new Car(0,0,10,2,1,0);
		Car car3=new Car(0,0,8,2,1,1);	//Player1
		Car car4=new Car(1,2,7,1,2,0);
		Car car5=new Car(1,3,8,1,2,0);
		Car car6=new Car(1,5,7,1,2,0);
		Car car7=new Car(0,2,6,2,1,0);
		Car car8=new Car(0,6,7,2,1,2);	//Player2
		
		
		
		car1.setTexture(new Texture("ferrari.png"));
		car1.setPosition(leftX+car1.x*mapWidth/n, leftY+mapHeight*car1.y/m-mapHeight);
		
		car2.setTexture(new Texture("bugatti.png"));
		car2.setPosition(leftX+car2.x*mapWidth/n, leftY+mapHeight*car2.y/m-mapHeight);
		
		car3.setTexture(new Texture("ambulance.png"));
		car3.setPosition(leftX+car3.x*mapWidth/n, leftY+mapHeight*car3.y/m-mapHeight);
				
		car4.setTexture(new Texture("chevrolet.png"));
		car4.setPosition(leftX+car4.x*mapWidth/n, leftY+mapHeight*car4.y/m-mapHeight);
						
		car5.setTexture(new Texture("jeep.png"));
		car5.setPosition(leftX+car5.x*mapWidth/n, leftY+mapHeight*car5.y/m-mapHeight);
						
		car6.setTexture(new Texture("dashcam.png"));
		car6.setPosition(leftX+car6.x*mapWidth/n, leftY+mapHeight*car6.y/m-mapHeight);
						
		car7.setTexture(new Texture("mercedes.png"));
		car7.setPosition(leftX+car7.x*mapWidth/n, leftY+mapHeight*car7.y/m-mapHeight);
		
		car8.setTexture(new Texture("mercedes.png"));
		car8.setPosition(leftX+car8.x*mapWidth/n, leftY+mapHeight*car8.y/m-mapHeight);
				
		list.add(car1);
		list.add(car2);
		list.add(car3);
		list.add(car4);
		list.add(car5);
		list.add(car6);
		list.add(car7);
		list.add(car8);
		
		uiStage.addActor(car1);
		uiStage.addActor(car2);
		uiStage.addActor(car3);
		uiStage.addActor(car4);
		uiStage.addActor(car5);
		uiStage.addActor(car6);
		uiStage.addActor(car7);
		uiStage.addActor(car8);

	}
	@Override
	public void render(float dt) {
		
		super.render(dt);
		//Positions
		for(Board b:boards)
			b.setPosition(b.leftX,b.leftY);
		drawCard.setPosition(VIEW_WIDTH/2, paddingH);
		drawCard.setWidth(2*paddingW);
		drawCard.setHeight(paddingH);
		//Widths
		rightboard.setWidth(rightwidth);
		leftboard.setWidth(leftwidth);
		middleboard.setWidth(middlewidth);
		
		//Heights
		rightboard.setHeight(mapHeight);
		leftboard.setHeight(mapHeight);
		middleboard.setHeight(mapHeight);
		ShapeRenderer sr=new ShapeRenderer();
		sr.begin(ShapeType.Line);
		sr.setColor(Color.YELLOW);
		for(int i=0;i<m;i++)
			sr.line(leftX, leftY+mapHeight*i/m, rightX+rightwidth, leftY+mapHeight*i/m);
		for(int i=0;i<n;i++)
			sr.line(leftX+mapWidth*i/n, leftY, leftX+mapWidth*i/n, leftY+mapHeight);
		sr.end();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		boolean win;
		for(Car x:cars){
			x.setWidth(mapWidth/n*x.width);
			x.setHeight(mapHeight/m*x.height);
			if(x.xx==m && x.id==1){
				win=true;
			}
		}
		//Positions
		returnMenu.setPosition(0, 0);
		
		
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// upperleft is (0,0)	
		int a=boardX;
		int b=boardY;
		//(a,b) is the lowerleft corner
		int sc=VIEW_HEIGHT-screenY;
		int w1=mapWidth/n;
		int h1=mapHeight/m;
		int a1=screenX-a;
		a1/=w1;
		int b1=sc-b;
		b1/=h1;
		for(int i=0;i<boards.length;i++){
			int bb1=screenY-(VIEW_HEIGHT-boardY-boardHeight);
			bb1/=h1;
			if(boards[i].pressed)
				boards[i].dragBoard(boards[i].x,bb1-boards[i].firstTouchY, gameTable, boardX, boardY, boardWidth,boardHeight);
		}
		for(int i=0;i<cars.size();i++){
			
				
			if(cars.get(i).pressed && cars.get(i).move){
					if(cars.get(i).setPosition(a1-cars.get(i).firstTouchX,b1-cars.get(i).firstTouchY,gameTable,i,w1,h1,a,b,cars))
						moveCount++;
					if(moveCount==targetMove){
						cars.get(i).pressed=false;
						turn=3-turn;
//						for(int i=0;i<2;i++)
//							players_played[i]=!players_played[i];
						
						moveCount=0;
					}
					
			}
		
		
	}
		
		return true;
	}
	
	

}
