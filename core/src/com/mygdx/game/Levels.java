package com.mygdx.game;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

public class Levels extends BaseScreen {
	String minute;
	Sound carVoice;
	int m,n;
	ShapeRenderer sr;
	int w,h;
	TextButton returnMenu,hint,message;
	static ArrayList<Car> cars;
	int[][] gameTable;
	static int a,b,w1,h1;
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
	Label time,move_count,targetMoveCount;
	int total_time;
	int tl;
	int numOfMoves;
	long startTime;
	int[] targets;
	ArrayList<String> verticalSkins;
	ArrayList<String> horizontalSkins;
	ArrayList<ArrayList<int[]>> levels; //levels -> cars -> car params
	int currentLevel;
	public Levels(BaseGame game, int currentLevel) {
		super(game);
		this.currentLevel = currentLevel;

		horizontalSkins = new ArrayList<>();
		horizontalSkins.add("ferrari.png");
		horizontalSkins.add("ambulance.png");
		horizontalSkins.add("bugatti.png");
		horizontalSkins.add("chevrolet.png");
		horizontalSkins.add("jeep.png");
		horizontalSkins.add("mercedes.png");

		verticalSkins = new ArrayList<>();
		verticalSkins.add("ferrari2.png");
		verticalSkins.add("ambulance2.png");
		verticalSkins.add("bugatti2.png");
		verticalSkins.add("chevrolet2.png");
		verticalSkins.add("jeep2.png");
		verticalSkins.add("mercedes2.png");

		levels = new ArrayList<>();

		ArrayList<int[]> layout;
		layout = new ArrayList<>();
		layout.add(new int[]{0,3,5,2,1,0});
		layout.add(new int[]{0,0,4,2,1,0});
		layout.add(new int[]{0,0,2,2,1,1});
		layout.add(new int[]{1,2,1,1,2,0});
		layout.add(new int[]{1,3,2,1,2,0});
		layout.add(new int[]{1,5,1,1,2,0});
		layout.add(new int[]{0,2,0,2,1,0});
		levels.add(layout);

		layout = new ArrayList<>();
		layout.add(new int[]{1,0,4,1,2,0});
		layout.add(new int[]{1,1,4,1,2,0});
		layout.add(new int[]{0,2,5,2,1,0});
		layout.add(new int[]{0,2,4,2,1,0});
		layout.add(new int[]{1,4,4,1,2,0});
		layout.add(new int[]{0,0,3,2,1,1});
		layout.add(new int[]{1,2,2,1,2,0});
		layout.add(new int[]{1,2,0,1,2,0});
		layout.add(new int[]{1,3,1,1,3,0});
		layout.add(new int[]{1,4,1,1,2,0});
		levels.add(layout);



	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		targets=new int[8];//it represents the target move counts of levels which we will set at the beginning by playing the levels before 
		//by hint button
		targets[0]=10;
		targets[1]=36;
		numOfMoves=0;
		total_time=120;
		time=new Label("02:00", game.skin,"uiLabelStyle");
		targetMoveCount=new Label("Target Move Count is :"+targets[levelno], game.skin,"uiLabelStyle");
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
		winner.setTexture(new Texture("youwin1.png"));
		pass=Singleplayer.passed;
		carVoice=Gdx.audio.newSound(Gdx.files.internal("car.ogg"));
		returnMenu=new TextButton("Back", game.skin, "buttonStyle3");
		hint=new TextButton("Hint", game.skin, "buttonStyle");
		message=new TextButton("", game.skin, "buttonStyle");
		game.skin.add("returnMenu", returnMenu);
		buton=game.skin.get("buton", Sound.class);
		win=false;
		cars=new ArrayList<Car>();
		m=6;
		n=6;
		gameTable=new int[n][m];
		w=VIEW_WIDTH/2;
		h=VIEW_HEIGHT/2;
		w1=w/m;
		h1=h/n;

		a=VIEW_WIDTH/2-w/2;
		b=VIEW_HEIGHT/2-h/2;
		sr=new ShapeRenderer();
		createCars(cars, currentLevel);
		for(int i=0;i<cars.size();i++)
			if(cars.get(i).id==1){
				cars.get(i).setTexture(SelectCar.targetTexture);
				break;
			}


		for(int i=0;i<cars.size();i++){
			for(int j=n-cars.get(i).y-cars.get(i).height;j<n-cars.get(i).y;j++)
				for(int k=cars.get(i).x;k<cars.get(i).x+cars.get(i).width;k++){
					gameTable[j][k]=i+1;
				}
		}

		for(final Car carx:cars){

			carx.addListener(new InputListener(){

				public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
					carx.firstTouchX=(int) (x/(w/m));
					carx.firstTouchY=(int) (y/(h/n));
					carVoice.play();


					carx.pressed=true;
					return true;
				}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					carx.pressed=false;
					carVoice.stop();
				}
			});

			returnMenu.addListener(new InputListener()
			{
				public boolean touchDown(InputEvent ev,float x,float y,int pointer,int button){
					buton.play();
					return true;
				}
				public void touchUp(InputEvent ev,float x,float y,int pointer,int button){
					game.setScreen(new Singleplayer(game));
					if(win){
						for(Car carx:cars)
							carx.buton.stop();
						if(pass<levelno)
							Singleplayer.passed=pass+1;

					}
				}
			});

		}
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

		uiTable.setBackground(game.skin.getDrawable("background"));
		uiTable.add(returnMenu);
		uiTable.add(hint);
		uiTable.row();
		time.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),
				Actions.delay(1.0f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));
		mainStage.addActor(time);
		startTime= System.currentTimeMillis();
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

		ArrayList<int[]> cars = levels.get(level);
		for (int i = 0; i < cars.size(); i++) {
			int[] c = cars.get(i);
			Car car = new Car(c[0],c[1],c[2],c[3],c[4],c[5]);
			car.setTexture(new Texture(horizontalSkins.get(i % horizontalSkins.size())));
			car.setPosition(VIEW_WIDTH/2 - w/2 + car.x * w/m, VIEW_HEIGHT/2 - h/2 + h*car.y/n);
			list.add(car);
			mainStage.addActor(car);
		}

		Car escapeCar = null;
		for (int i = 0; i < cars.size(); i++) { //assuming escape car always exists, otherwise error
			if (cars.get(i)[5] == 1) {
				int[] c = cars.get(i);
				escapeCar = new Car(c[0],c[1],c[2],c[3],c[4],c[5]);
			}
		}

		startX = escapeCar.getX();
		startY = escapeCar.getY();
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		targetMoveCount.setText("Target Move Count is :"+targets[levelno-1]);

		for(Car x:cars){
			x.setWidth(w/m*x.width);
			x.setHeight(h/n*x.height);
			if(x.xx==m && x.id==1){
				win=true;
			}

			if(win){
				winner.setVisible(true);
				winner.setPosition(VIEW_WIDTH/2-100, VIEW_HEIGHT/2-100);
				winner.setWidth(200);
				winner.setHeight(200);
				pause();
			}


		}


	}






	@Override
	public void render(float dt) {
		super.render(dt);
		minute="";
		long elapsedTime = System.currentTimeMillis()-startTime;
		tl=(int) (total_time-elapsedTime/1000);
		if(tl-60*(tl/60)>9)
			minute=""+(tl-60*(tl/60));
		else
			minute="0"+(tl-60*(tl/60));



		index++;
		index%=speed;
		if(move && index==0){
			target.setPosition(target.getX()+distx/Math.max(Math.abs(distx),1)*w1, target.getY()+disty/Math.max(Math.abs(disty),1)*h1);
			gox+=distx/Math.max(Math.abs(distx),1);
			goy+=disty/Math.max(Math.abs(disty),1);
			numOfMoves++;
		}
		if(distx!=0 || disty!=0)
			if(gox==distx)
				if(goy==disty){
					gox=0;
					goy=0;
					int aa,bb;
					aa=hintnumber<path.size()?path.get(hintnumber).targetX:gameTable[0].length;
					bb=hintnumber<path.size()?path.get(hintnumber).targetY:target.y;
					target.setPosition(aa, bb, gameTable, carnumber, w1, h1, a, b, true);
					move=false;	
				}
		super.render(dt);
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.GRAY);
		sr.rect(VIEW_WIDTH/2-w/2, VIEW_HEIGHT/2-h/2, w, h);
		sr.end();
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.YELLOW);
		for(Car x:cars)
			if(x.id==1){
				sr.rect(VIEW_WIDTH/2+w/2, x.getY(), 2*w/m, h/n);
			}
		sr.end();

		/////////////////
		sr.begin(ShapeType.Line);
		sr.setColor(Color.BLACK);
		for(int i=0;i<m+1;i++)
			sr.line(VIEW_WIDTH/2-w/2, VIEW_HEIGHT/2-h/2+h*i/m, VIEW_WIDTH/2+w/2, VIEW_HEIGHT/2-h/2+h*i/m);
		for(int i=0;i<n+1;i++)
			sr.line(VIEW_WIDTH/2-w/2+w*i/m, VIEW_HEIGHT/2-h/2, VIEW_WIDTH/2-w/2+w*i/m, VIEW_HEIGHT/2+h/2);
		sr.end();
		sr.begin(ShapeType.Filled);
		sr.setColor(Color.CYAN);
		sr.rect(startX-w/m,startY,w/m,h/n);
		sr.end();
		mainStage.draw();
		time.setText("Time Left: 0"+tl/60+":"+minute);
		move_count.setText("Number Of Moves :"+numOfMoves);
		time.setPosition(0, 350);
		time.setColor(Color.RED);
		move_count.setPosition(VIEW_WIDTH/2,VIEW_HEIGHT-100);
		targetMoveCount.setPosition(VIEW_WIDTH/2,VIEW_HEIGHT-50 );
		returnMenu.setPosition(0,0);
		hint.setPosition(0,200);
		hint.setWidth(100);
		hint.setHeight(100);
		int meswidth=400;
		int mesheight=60;
		message.setPosition(VIEW_WIDTH/2-meswidth/2, 0);
		message.setWidth(meswidth);
		message.setHeight(mesheight);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.YELLOW);
		for(Car x:cars)
			if(x.id==1)
				sr.rect(x.getX(), x.getY(), x.getWidth(), x.getHeight());
		sr.end();

		//show the hint

		sr.begin(ShapeType.Line);
		sr.setColor(Color.PURPLE);
		if(show)
			sr.rect(target.getX(), target.getY(), target.getWidth(), target.getHeight());
		sr.end();

	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// upperleft is (0,0)			
		for(int i=0;i<cars.size();i++){
			int a=VIEW_WIDTH/2-w/2;
			int b=VIEW_HEIGHT/2-h/2;
			//(a,b) is the lowerleft corner
			int sc=VIEW_HEIGHT-screenY;
			int w1=w/m;
			int h1=h/n;
			int a1=screenX-a;
			a1/=w1;
			int b1=sc-b;
			b1/=h1;

			if(cars.get(i).pressed && cars.get(i).move){

				if(cars.get(i).setPosition(a1,b1,gameTable,i,w1,h1,a,b,true))
					numOfMoves++;
			}


		}

		return true;
	}
}




