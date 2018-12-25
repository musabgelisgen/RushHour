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
	int timer = 0;

	BaseActor card, move, move1, move2, move3, shiftMap, skip;
	Card currentCard;
	boolean expectingMovement = false;
	public HumanVsHuman(BaseGame game) {
		super(game);
	}

	@Override
	public void create() {
		drawCard=new TextButton("Draw a Card", game.skin, "buttonStyle");
		boards=new Board[3];
		turn=2; //will set to 1 when first card is drawn
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

		leftboard=new Board(0,6,5,6,leftX,leftY,1);
		middleboard=new Board(5,6,4,6,middleX,middleY,2);
		rightboard=new Board(9,6,5,6,rightX,rightY,3);
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
					if (expectingMovement && !(currentCard.shift)) {
						carx.firstTouchX=(int) (x/(mapWidth/n));
						carx.firstTouchY=(int) (y/(mapHeight/m));
						carx.lastX=carx.x+carx.firstTouchX;
						carx.lastY=carx.y+carx.height-carx.firstTouchY;
						if(carx.id!=3-turn)
							carx.pressed=true;
					}
					return true;

				}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					carx.pressed=false;

					if (expectingMovement && !(currentCard.shift)) {
						expectingMovement = false;
						moveCount = 0;
					}

				}
			});
		}
		//board action listeners
		for(final Board b:boards){

			b.addListener(new InputListener()
			{
				public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
					if (expectingMovement && currentCard.shift) {
						//					for(int i=0;i<gameTable.length;i++)
						//						System.out.println(Arrays.toString(gameTable[i]));
						//					System.out.println("\n\n");
						boolean shift=true;
						//					for(int i=0;i<gameTable.length;i++)
						//						if((gameTable[i][4]>0 && gameTable[i][5]==gameTable[i][4] && (b.id==1 || b.id==2))||(gameTable[i][8]>0 && gameTable[i][8]==gameTable[i][9] && (b.id==3 || b.id==2)))
						//						{
						//							System.out.println("id:"+b.id+" s�k�nt�:"+i);
						//							shift=false;
						//							break;
						//						}
						b.firstTouchX=(int) (x/(mapWidth/n));
						b.firstTouchY=b.height-(int) (y/(mapHeight/m))-1;
						//b.lastX=b.firstTouchX+b.x;
						//b.lastY=b.firstTouchY+b.y;
						b.pressed=shift;

					}
					return true;
				}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					b.pressed=false;
					expectingMovement = false;
					moveCount = 0;
				}
			});
		}

		card = new BaseActor();
		card.setTexture(new Texture("card2.png"));
		card.setVisible(false);
		
		shiftMap = new BaseActor();
		shiftMap.setTexture(new Texture("shift.png"));
		shiftMap.setVisible(false);
		
		move = new BaseActor();
		move.setTexture(new Texture("move.png"));
		move.setVisible(false);
		
		move1 = new BaseActor();
		move1.setTexture(new Texture("1.png"));
		move1.setVisible(false);
		
		move2 = new BaseActor();
		move2.setTexture(new Texture("2.png"));
		move2.setVisible(false);
		
		move3 = new BaseActor();
		move3.setTexture(new Texture("3.png"));
		move3.setVisible(false);
		
		skip = new BaseActor();
		skip.setTexture(new Texture("skip.png"));
		skip.setVisible(false);


		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(returnMenu);
		leftboard.setVisible(true);
		rightboard.setVisible(true);
		middleboard.setVisible(true);
		uiStage.addActor(leftboard);
		uiStage.addActor(rightboard);
		uiStage.addActor(middleboard);
		uiStage.addActor(drawCard);
		uiStage.addActor(card);
		uiStage.addActor(shiftMap);
		uiStage.addActor(move);
		uiStage.addActor(move1);
		uiStage.addActor(move2);
		uiStage.addActor(move3);
		uiStage.addActor(skip);


		drawCard.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){

				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				if (!expectingMovement) {
					int moveCount = (int)(Math.random() * 3) + 1;
					boolean shift = false;
					boolean slice = false;

					int rand = (int)(Math.random() * 16);
					if (rand < 16 && rand > 12) { // %20 chance
						shift = true;
						slice = false;
					}
					else if (rand <= 12 && rand > 11){ // %10 chance
						shift = false;
						slice = true;
						targetMove = 200;
					}
					else { // %75 chance
						shift = false;
						slice = false;
						targetMove = moveCount;
					}

					currentCard = new Card(moveCount, shift, slice);
					expectingMovement = true;
					System.out.println("Card: " + moveCount + " " + shift + " " + slice);
					
					timer = 100;
					
					card.setVisible(true);
					card.setPosition(VIEW_WIDTH/2-95, VIEW_HEIGHT/2-133);
					card.setWidth(190);
					card.setHeight(266);
					
					if (shift) {
						shiftMap.setVisible(true);
						shiftMap.setPosition(VIEW_WIDTH/2-95, VIEW_HEIGHT/2-133);
						shiftMap.setWidth(190);
						shiftMap.setHeight(266);
					}
					else if (slice) {
						skip.setVisible(true);
						skip.setPosition(VIEW_WIDTH/2-50, VIEW_HEIGHT/2-50);
						skip.setWidth(100);
						skip.setHeight(100);
					}
					else {
						move.setVisible(true);
						move.setPosition(VIEW_WIDTH/2-50, VIEW_HEIGHT/2-100);
						move.setWidth(100);
						move.setHeight(100);
						
						if (moveCount == 1) {
							move1.setVisible(true);
							move1.setPosition(VIEW_WIDTH/2-50, VIEW_HEIGHT/2 + 10);
							move1.setWidth(100);
							move1.setHeight(100);
						}
						else if (moveCount == 2) {
							move2.setVisible(true);
							move2.setPosition(VIEW_WIDTH/2-50, VIEW_HEIGHT/2 + 10);
							move2.setWidth(100);
							move2.setHeight(100);
						}
						else {
							move3.setVisible(true);
							move3.setPosition(VIEW_WIDTH/2-50, VIEW_HEIGHT/2 + 10);
							move3.setWidth(100);
							move3.setHeight(100);
						}
					}
				}
				turn = 3 - turn; //change turns when card is drawn
			}
		});


		for(Board b:boards)
			b.setPosition(b.leftX,b.leftY);
		//uiTable.add(leftboard);
		//uiTable.add(rightboard);
		//uiTable.add(middleboard);


	}
	public void createCars(ArrayList<Car> list){
		Car car1=new Car(0,0,7,3,1,0);
		Car car2=new Car(1,3,6,1,3,0);
		Car car3=new Car(0,0,10,2,1,1);	//Player1
		Car car4=new Car(1,4,10,1,2,0);
		Car car5=new Car(1,5,9,1,2,0);
		Car car6=new Car(1,6,8,1,2,0);
		Car car7=new Car(1,7,8,1,2,0);
		Car car8=new Car(1,8,7,1,2,0);	
		Car car9=new Car(1,9,6,1,2,0);
		Car car10=new Car(1,10,9,1,3,0);
		Car car11=new Car(0,11,10,3,1,0);
		Car car12=new Car(0,12,7,2,1,2); //Player2


		car1.setTexture(new Texture("truck_horiz.png"));
		car1.setPosition(boardX+car1.x*mapWidth/n, boardY+boardHeight*car1.y/gameTable.length);

		car2.setTexture(new Texture("truck_vert.png"));
		car2.setPosition(boardX+car2.x*mapWidth/n, boardY+boardHeight*car2.y/gameTable.length);

		car3.setTexture(new Texture("police_horiz.png"));
		car3.setPosition(boardX+car3.x*mapWidth/n, boardY+boardHeight*car3.y/gameTable.length);

		car4.setTexture(new Texture("obs_car_vert.png"));
		car4.setPosition(boardX+car4.x*mapWidth/n, boardY+boardHeight*car4.y/gameTable.length);

		car5.setTexture(new Texture("obs_car_vert.png"));
		car5.setPosition(boardX+car5.x*mapWidth/n, boardY+boardHeight*car5.y/gameTable.length);

		car6.setTexture(new Texture("obs_car_vert.png"));
		car6.setPosition(boardX+car6.x*mapWidth/n, boardY+boardHeight*car6.y/gameTable.length);

		car7.setTexture(new Texture("obs_car_vert.png"));
		car7.setPosition(boardX+car7.x*mapWidth/n, boardY+boardHeight*car7.y/gameTable.length);

		car8.setTexture(new Texture("obs_car_vert.png"));
		car8.setPosition(boardX+car8.x*mapWidth/n, boardY+boardHeight*car8.y/gameTable.length);
		
		car9.setTexture(new Texture("obs_car_vert.png"));
		car9.setPosition(boardX+car9.x*mapWidth/n, boardY+boardHeight*car9.y/gameTable.length);

		car10.setTexture(new Texture("truck_vert.png"));
		car10.setPosition(boardX+car10.x*mapWidth/n, boardY+boardHeight*car10.y/gameTable.length);

		car11.setTexture(new Texture("truck_horiz.png"));
		car11.setPosition(boardX+car11.x*mapWidth/n, boardY+boardHeight*car11.y/gameTable.length);

		car12.setTexture(new Texture("police_horiz2.png"));
		car12.setPosition(boardX+car12.x*mapWidth/n, boardY+boardHeight*car12.y/gameTable.length);

		list.add(car1);
		list.add(car2);
		list.add(car3);
		list.add(car4);
		list.add(car5);
		list.add(car6);
		list.add(car7);
		list.add(car8);
		list.add(car9);
		list.add(car10);
		list.add(car11);
		list.add(car12);

		uiStage.addActor(car1);
		uiStage.addActor(car2);
		uiStage.addActor(car3);
		uiStage.addActor(car4);
		uiStage.addActor(car5);
		uiStage.addActor(car6);
		uiStage.addActor(car7);
		uiStage.addActor(car8);
		uiStage.addActor(car9);
		uiStage.addActor(car10);
		uiStage.addActor(car11);
		uiStage.addActor(car12);

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

		//		ShapeRenderer sr=new ShapeRenderer();
		//		sr.begin(ShapeType.Line);
		//		sr.setColor(Color.YELLOW);
		//		for(int i=0;i<m;i++)
		//			sr.line(leftX, leftY+mapHeight*i/m, rightX+rightwidth, leftY+mapHeight*i/m);
		//		for(int i=0;i<n;i++)
		//			sr.line(leftX+mapWidth*i/n, leftY, leftX+mapWidth*i/n, leftY+mapHeight);
		//		sr.end();
		//		for(Car car:cars)
		//			car.setPosition(boardX+car.x*mapWidth/n, boardY+boardHeight*car.y/gameTable.length);
		//	
		
		if (timer > 0) {
			timer = timer - 1;
			if (timer == 0) {
				card.setVisible(false);
				shiftMap.setVisible(false);
				move.setVisible(false);
				skip.setVisible(false);
				move1.setVisible(false);
				move2.setVisible(false);
				move3.setVisible(false);
			}
		}

	}

	@Override
	public void update(float dt) {
		boolean win;
		for(Car x:cars){
			x.setWidth(mapWidth/n*x.width);
			x.setHeight(mapHeight/m*x.height);
			if((x.xx==m && x.id==1)||(x.xx==-2 && x.id==2)){
				win=true;
			}
		}
		//Positions
		returnMenu.setPosition(0, 0);


	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// upperleft is (0,0)	
		//		for(Car c:cars){
		//			int s=0;ArrayList<Board> cross=new ArrayList<Board>();
		//			for(Board b:boards)
		//				if(b.x<=c.x)
		//					if(b.x+b.width>=c.x+c.width)
		//						cross.add(b);
		//			if(cross.size()>1)
		//				for(Board b:cross)
		//					b.pressed=false;
		//		}
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
		//		for(int i=0;i<gameTable.length;i++)
		//			System.out.println(Arrays.toString(gameTable[i]));
		//		System.out.println("\n\n");
		//		System.out.println(gameTable[6][8]+" , "+gameTable[6][9]);
		for(int i=0;i<boards.length;i++){

			int bb1=screenY-(VIEW_HEIGHT-boardY-boardHeight);
			bb1/=h1;
			if(boards[i].pressed)
				System.out.println(bb1-boards[i].firstTouchY);
			if(i<2)
				if(boards[i].pressed)
					if((bb1-boards[i].firstTouchY+boards[i].height<boards[i+1].y)||(bb1-boards[i].firstTouchY>boards[i+1].y+boards[i+1].height))
						boards[i].pressed=false;
			if(i>0)
				if(boards[i].pressed)
					if((bb1-boards[i].firstTouchY+boards[i].height<boards[i-1].y)||(bb1-boards[i].firstTouchY>boards[i-1].y+boards[i-1].height))
						boards[i].pressed=false;


			if(boards[i].pressed){

				boards[i].dragBoard(boards[i].x,bb1-boards[i].firstTouchY, gameTable, boardX, boardY, boardWidth,boardHeight,cars);

			}
		}
		for(int i=0;i<cars.size();i++){


			if(cars.get(i).pressed && cars.get(i).move){
				if(cars.get(i).setPosition(a1-cars.get(i).firstTouchX,b1-cars.get(i).firstTouchY,gameTable,i,w1,h1,a,b,cars))
					moveCount++;
				if(moveCount==targetMove){
					cars.get(i).pressed=false;
					
					//						for(int i=0;i<2;i++)
					//							players_played[i]=!players_played[i];

					moveCount=0;
				}

			}


		}

		return true;
	}



}
