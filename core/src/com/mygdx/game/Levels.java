package com.mygdx.game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

import data.SerializationUtil;
import data.StarScore;

public class Levels extends BaseScreen {
	//
	BaseActor startSign,finishSign,backgroundForInfo;
	Sound carVoice;
	int width_tiles,height_tiles;
	ShapeRenderer shape_renderer;
	int half_of_view_width,half_of_view_height;
	TextButton returnMenu,hint,message,nextLevel;
	static ArrayList<Car> cars;
	int[][] gameTable;
	static int a,b,tile_width,tile_height;
	int a1,b1;
	boolean win=false;
	float startX,startY;
	Sound buton;
	int pass;
	BaseActor winner;
	int levelno;
	ArrayList<Node> path;	//the path of the hint
	Car target;			//the car hint shows
	int carnumber;		//the index of the hint car
	int hintnumber;	// the array index of the hint wanted to be applied
	boolean show=false;	//shows the car which the hint wants user to move
	int distx,disty;
	protected boolean move;//for hint
	int gox,goy;	//for hint
	int speed;
	int index;
	Label move_count,targetMoveCount;
	int numOfMoves;
	int[] targets;
	boolean serialized = false;
	boolean finished = false;
	StarScore sScores;
	public Levels(BaseGame game) {
		super(game);

	}

	@Override
	public void create() {
//		grid = new BaseActor();
//		grid.setTexture(SelectTheme.targetTexture);
//		mainStage.addActor(grid);
		
		
		startSign = new BaseActor();
		startSign.setTexture(new Texture("start.png"));
		mainStage.addActor(startSign);
		
		finishSign = new BaseActor();
		finishSign.setTexture(new Texture("finish.jpg"));
		mainStage.addActor(finishSign);
		
		backgroundForInfo = new BaseActor();
		backgroundForInfo.setTexture(new Texture("upButton.png"));
		mainStage.addActor(backgroundForInfo);

		// TODO Auto-generated method stub
		targets=new int[8];//it represents the target move counts of levels which we will set at the beginning by playing the levels before 
		//by hint button
		targets[0]=4;
		targets[1]=16;
		targets[2]=14;
		targets[3]=10;
		targets[4]=6;
		targets[5]=14;
		targets[6]=19;
		targets[7]=20;
		numOfMoves=0;
		targetMoveCount=new Label("Minimum number of moves :"+targets[levelno], game.skin,"uiLabelStyle");
		move_count=new Label("Number Of Moves :"+numOfMoves, game.skin,"uiLabelStyle");
		index=0;
		speed=10;
		gox=0;
		goy=0;
		move=false;
		show=false;
		hintnumber=0;
		target=null;
		path=new ArrayList<Node>();
		winner=new BaseActor();
		winner.setTexture(new Texture("you_win.png"));
		pass=Singleplayer.passed;
		carVoice=Gdx.audio.newSound(Gdx.files.internal("car.ogg"));
		returnMenu=new TextButton("Back", game.skin, "buttonStyle");
		hint=new TextButton("Hint", game.skin, "buttonStyle");
		nextLevel = new TextButton("Next", game.skin, "buttonStyle");
		message=new TextButton("", game.skin, "buttonStyle");
		game.skin.add("returnMenu", returnMenu);
		buton=game.skin.get("buton", Sound.class);
		win=false;
		cars=new ArrayList<Car>();
		width_tiles=6;
		height_tiles=6;
		gameTable=new int[height_tiles][width_tiles];
		half_of_view_width=VIEW_WIDTH/2;
		half_of_view_height=VIEW_HEIGHT/2;
		tile_width=half_of_view_width/width_tiles;
		tile_height=half_of_view_height/height_tiles;

		a=VIEW_WIDTH/2-half_of_view_width/2;
		b=VIEW_HEIGHT/2-half_of_view_height/2;
		shape_renderer=new ShapeRenderer();


		createCars(cars);
		for(int i=0;i<cars.size();i++)
			if(cars.get(i).id==1){
				if(SelectTheme.targetNumber == 2)
					cars.get(i).setTexture(SelectCar.targetTexture2);
				else
					cars.get(i).setTexture(SelectCar.targetTexture);
				break;
			}


		for(int i=0;i<cars.size();i++){
			for(int j=height_tiles-cars.get(i).y-cars.get(i).height;j<height_tiles-cars.get(i).y;j++)
				for(int k=cars.get(i).x;k<cars.get(i).x+cars.get(i).width;k++){
					gameTable[j][k]=i+1;
				}
		}

		for(final Car carx:cars){
			
		carx.addListener(new InputListener(){
		
			
			 public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
						carx.firstTouchX=(int) (x/(half_of_view_width/width_tiles));
						carx.firstTouchY=(int) (y/(half_of_view_height/height_tiles));
						carx.lastX=carx.firstTouchX+carx.x;
						carx.lastY=carx.firstTouchY+carx.y;
						carVoice.play();
						
					
				 carx.pressed=true;
					return true;
				}
					public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
						carx.pressed=false;
						if(carx.x!=carx.lastX || carx.y!=carx.lastY)
							numOfMoves++;
						carVoice.stop();
					}
		 });
		}

			returnMenu.addListener(new InputListener()
			{
				public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
					buton.play();
					return true;
				}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					if(win){
						for(Car carx:cars)
							carx.buton.stop();
						if(pass<levelno)
							Singleplayer.passed=pass+1;

					}
					game.setScreen(new Singleplayer(game));
					
				}
			});
		
		hint.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				if(win)
					return true;

				Node begin=new Node();
				begin.cars=cars;
				begin.table=new int[gameTable.length][gameTable[0].length];
				for(int i=0;i<gameTable.length;i++)
					for(int j=0;j<gameTable[0].length;j++)
						begin.table[i][j]=gameTable[i][j];
				boolean wehave=false;
				hintnumber=1;

				if(path.size()>0){
					for(int i=0;i<path.size();i++){
						if(isSame(path.get(i).table)){
							wehave=true;
							hintnumber=i+1;
						}
					}
				}
				if(!wehave){
					path=PathFinder.Astar(begin, cars);
				}
				move=true;
				if(hintnumber<path.size()){
					target=path.get(hintnumber).moved;
					carnumber=path.get(hintnumber).carnumber;
					distx=path.get(hintnumber).targetX-cars.get(carnumber).x;
					disty=path.get(hintnumber).targetY-cars.get(carnumber).y;
				}
				else{
					carnumber=0;
					for(;carnumber<cars.size();carnumber++)
						if(cars.get(carnumber).id==1){
							break;
						}
					target=cars.get(carnumber);
					distx=gameTable[0].length-target.x;
					disty=0;

				}
				if(hintnumber<path.size()){
					for(int i=0;i<path.get(hintnumber).table.length;i++)
						System.out.println(Arrays.toString(path.get(hintnumber).table[i]));
					System.out.println(cars.get(5).x+" "+cars.get(5).y);
					System.out.println("\n\n");
				}
				return true;
			}


			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){

				//message.setVisible(true);	
				//message.setTouchable(Touchable.enabled);


			}
		});
		
		nextLevel.addListener(new InputListener() {
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
				buton.play();
				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){

				for(Car carx:cars)
					carx.buton.stop();
				if(pass<levelno)
					Singleplayer.passed=pass+1;
				if(levelno == 1) {
					game.setScreen(new Level2(game));
				}
				else if(levelno == 2) {
					game.setScreen(new Level3(game));
				}
				else if(levelno == 3) {
					game.setScreen(new Level4(game));
				}
				else if(levelno == 4) {
					game.setScreen(new Level5(game));
				}
				else if(levelno == 5) {
					game.setScreen(new Level6(game));
				}
				else if(levelno == 6) {
					game.setScreen(new Level7(game));
				}
				else if(levelno == 7) {
					game.setScreen(new Level8(game));
				}
			}
		});

		message.addListener(new InputListener()
		{
			public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){

				return true;
			}
			public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
				//message.setTouchable(Touchable.disabled);
				//message.setVisible(false);	


			}
		});

		
		if(SelectTheme.targetNumber == 2)
			uiTable.setBackground(game.skin.getDrawable("themeTexture2"));
		else
			uiTable.setBackground(game.skin.getDrawable("background"));
		
		uiTable.add(returnMenu);
		uiTable.add(hint);
		uiTable.add(nextLevel);
		uiTable.row();


		mainStage.addActor(move_count);
		mainStage.addActor(targetMoveCount);
		mainStage.addActor(message);
		message.setVisible(false);
		mainStage.addActor(winner);
		winner.setVisible(false);

	}
	public boolean isSame(int[][] arr){
		for(int i=0;i<arr.length;i++)
			for(int j=0;j<arr[0].length;j++)
				if(arr[i][j]!=gameTable[i][j])
					return false;
		return true;
	}

	public void createCars(ArrayList<Car> list){

	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		targetMoveCount.setText("Minimum number of moves :"+targets[levelno-1]);

		for(Car x:cars){
			x.setWidth(half_of_view_width/width_tiles*x.width);
			x.setHeight(half_of_view_height/height_tiles*x.height);
			if(x.xx==width_tiles && x.id==1){
				if(!serialized) {
					int currStar;
					if(numOfMoves <= targets[levelno - 1])
						currStar = 3;
					else if(numOfMoves <= targets[levelno - 1] + 2)
						currStar = 2;
					else
						currStar = 1;

					String fileFolder = ""; 
					String scoreDir = ""; //location for StarScore object in file system
					String os = System.getProperty("os.name").toUpperCase();

					if (os.contains("WIN")) {
						fileFolder = System.getenv("APPDATA") + "\\" + "RushHour";
						scoreDir =  fileFolder + "\\scores.ser";
					}

					else if (os.contains("MAC")) {
						fileFolder = System.getProperty("user.home") + "/Library/Application Support" + "/RushHour";
						scoreDir = fileFolder + "/scores.ser";
					}
					

					try { //deserialization here
						sScores = (StarScore) SerializationUtil.deserialize(scoreDir);
						System.out.println("old score: " + sScores.getScores().get(levelno - 1));
						if(sScores.getScores().get(levelno - 1) < currStar) {
							sScores.updateScore(levelno - 1, currStar);
							SerializationUtil.serialize(sScores, scoreDir);
						}
						System.out.println("new score: " + sScores.getScores().get(levelno - 1));
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}

					serialized = true;
				}
				win=true;
			}

			if(win && !finished){
				if(SelectTheme.targetNumber == 2)
					uiTable.setBackground(game.skin.getDrawable("space_backgroundSP"));
				else
					uiTable.setBackground(game.skin.getDrawable("background_blurred"));
				move_count.setVisible(false);
				targetMoveCount.setVisible(false);
				Image starImage;
				if(sScores.getScores().get(levelno - 1) == 0) 
					starImage = new Image(new Texture("no_stars.png"));
				else if(sScores.getScores().get(levelno - 1) == 1) 
					starImage = new Image(new Texture("one_stars.png"));
				else if(sScores.getScores().get(levelno - 1) == 2)
					starImage = new Image(new Texture("two_stars.png"));
				else
					starImage = new Image(new Texture("three_stars.png"));
				mainStage.addActor(starImage);
				starImage.setPosition(VIEW_WIDTH/2 - 83, 5 * VIEW_HEIGHT/6 - 20);
				starImage.setWidth(175);
				starImage.setHeight(65);
				winner.setVisible(true);
				winner.setPosition(VIEW_WIDTH/2-150, VIEW_HEIGHT/2-175);
//				winner.setWidth(400);
//				winner.setHeight(200);
				winner.setWidth(300);
				winner.setHeight(300);
				finished = true;
				pause();
			}

		}

	}

	@Override
	public void render(float dt) {
		super.render(dt);
		
		

		index++;
		index%=speed;
		if(move && index==0){
			target.setPosition(target.getX()+distx/Math.max(Math.abs(distx),1)*tile_width, target.getY()+disty/Math.max(Math.abs(disty),1)*tile_height);
			gox+=distx/Math.max(Math.abs(distx),1);
			goy+=disty/Math.max(Math.abs(disty),1);
			//numOfMoves++;
		}
		if(distx!=0 || disty!=0)
			if(gox==distx)
				if(goy==disty){
					gox=0;
					goy=0;
					int aa,bb;
					aa=hintnumber<path.size()?path.get(hintnumber).targetX:gameTable[0].length;
					bb=hintnumber<path.size()?path.get(hintnumber).targetY:target.y;
					target.setPosition(aa, bb, gameTable, carnumber, tile_width, tile_height, a, b, true);
					move=false;
					numOfMoves++;
				}
		super.render(dt);
				shape_renderer.begin(ShapeType.Filled);
				shape_renderer.setColor(Color.GRAY);
				shape_renderer.rect(VIEW_WIDTH/2-half_of_view_width/2, VIEW_HEIGHT/2-half_of_view_height/2, half_of_view_width, half_of_view_height);
				shape_renderer.end();

//		grid.setPosition(VIEW_WIDTH/2-half_of_view_width/2, VIEW_HEIGHT/2-half_of_view_height/2);
//		grid.setWidth(half_of_view_width);
//		grid.setHeight(half_of_view_height);
		
		//finish flag
		
		for(Car x:cars)
		if(x.id==1){
			finishSign.setPosition(VIEW_WIDTH/2+half_of_view_width/2, x.getY());
			finishSign.setWidth(2*half_of_view_width/width_tiles);
			finishSign.setHeight(half_of_view_height/height_tiles);
			
		}
		
		
		backgroundForInfo.setPosition(0,360);
		backgroundForInfo.setWidth(240);
		backgroundForInfo.setHeight(100);
		//finish black

//		shape_renderer.begin(ShapeType.Filled);
//		shape_renderer.setColor(Color.BLACK);
//
//		for(Car x:cars)
//			if(x.id==1){
//				shape_renderer.rect(VIEW_WIDTH/2+half_of_view_width/2, x.getY(), 2*half_of_view_width/width_tiles, half_of_view_height/height_tiles);
//			}
//		shape_renderer.end();

		
		
		/////////////////
		shape_renderer.begin(ShapeType.Line);
		shape_renderer.setColor(Color.BLACK);
		for(int i=0;i<width_tiles+1;i++)
			shape_renderer.line(VIEW_WIDTH/2-half_of_view_width/2, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*i/width_tiles, VIEW_WIDTH/2+half_of_view_width/2, VIEW_HEIGHT/2-half_of_view_height/2+half_of_view_height*i/width_tiles);
		for(int i=0;i<height_tiles+1;i++)
			shape_renderer.line(VIEW_WIDTH/2-half_of_view_width/2+half_of_view_width*i/width_tiles, VIEW_HEIGHT/2-half_of_view_height/2, VIEW_WIDTH/2-half_of_view_width/2+half_of_view_width*i/width_tiles, VIEW_HEIGHT/2+half_of_view_height/2);
		shape_renderer.end();
		
		//Start Flag
		startSign.setPosition(startX,startY);
		startSign.setWidth(2*half_of_view_width/width_tiles);
		startSign.setHeight(half_of_view_height/height_tiles);
		
		
		
		//mavi baslangic
//				shape_renderer.begin(ShapeType.Filled);
//				shape_renderer.setColor(Color.CYAN);
//				shape_renderer.rect(startX,startY,half_of_view_width/width_tiles,half_of_view_height/height_tiles);
//				shape_renderer.end();
		
		mainStage.draw();
		move_count.setText("Number Of Moves :"+numOfMoves);
		move_count.setPosition(VIEW_WIDTH/2-300,VIEW_HEIGHT-100);
		targetMoveCount.setPosition(VIEW_WIDTH/2-300,VIEW_HEIGHT-60 );
		returnMenu.setPosition(10,20);
		returnMenu.setWidth(150);
		returnMenu.setHeight(50);
		hint.setPosition(250,20);
		hint.setWidth(150);
		hint.setHeight(50);
		nextLevel.setVisible(false);
		nextLevel.setPosition(480,20);
		nextLevel.setWidth(150);
		nextLevel.setHeight(50);
		
		if(win && levelno < 8) {
			nextLevel.setVisible(true);
		}
		
		int meswidth=400;
		int mesheight=60;
		message.setPosition(VIEW_WIDTH/2-meswidth/2, 0);
		message.setWidth(meswidth);
		message.setHeight(mesheight);
		
		//arabanin etrafindaki sari cizgiler
		
		//		shape_renderer.begin(ShapeType.Line);
		//		shape_renderer.setColor(Color.YELLOW);
		//		for(Car x:cars)
		//			if(x.id==1)
		//				shape_renderer.rect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
		//		shape_renderer.end();

		//show the hint

		shape_renderer.begin(ShapeType.Line);
		shape_renderer.setColor(Color.PURPLE);
		if(show)
			shape_renderer.rect(target.getX(), target.getY(), target.getWidth(), target.getHeight());
		shape_renderer.end();

	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// upperleft is (0,0)			
		for(int i=0;i<cars.size();i++){
			int a=VIEW_WIDTH/2-half_of_view_width/2;
			int b=VIEW_HEIGHT/2-half_of_view_height/2;
			//(a,b) is the lowerleft corner
			int sc=VIEW_HEIGHT-screenY;
			int w1=half_of_view_width/width_tiles;
			int h1=half_of_view_height/height_tiles;
			a1=screenX-a;
			a1/=w1;
			b1=sc-b;
			b1/=h1;
				
			if(cars.get(i).pressed && cars.get(i).move){
					
					cars.get(i).setPosition(a1-cars.get(i).firstTouchX,b1-cars.get(i).firstTouchY,gameTable,i,w1,h1,a,b,cars);
			}
		
		
	}
		
		return true;
	}
}
