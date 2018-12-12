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
	public Levels(BaseGame game) {
		super(game);
		
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
		createCars(cars);
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
					path=ASTAR.Astar(begin, cars);
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
		Car car1=new Car(0,3,5,2,1,0);
		Car car2=new Car(0,0,4,2,1,0);
		Car car3=new Car(0,0,2,2,1,1);
		Car car4=new Car(1,2,1,1,2,0);
		Car car5=new Car(1,3,2,1,2,0);
		Car car6=new Car(1,5,1,1,2,0);
		Car car7=new Car(0,2,0,2,1,0);
		
		car1.setTexture(new Texture("ferrari.png"));
		car1.setPosition(VIEW_WIDTH/2-w/2+car1.x*w/m, VIEW_HEIGHT/2-h/2+h*car1.y/n);
		
		car2.setTexture(new Texture("bugatti.png"));
		car2.setPosition(VIEW_WIDTH/2-w/2+car2.x*w/m, VIEW_HEIGHT/2-h/2+h*car2.y/n);
		
		car3.setTexture(new Texture("ambulance.png"));
		car3.setPosition(VIEW_WIDTH/2-w/2+car3.x*w/m, VIEW_HEIGHT/2-h/2+h*car3.y/n);
		
		car4.setTexture(new Texture("chevrolet.png"));
		car4.setPosition(VIEW_WIDTH/2-w/2+car4.x*w/m, VIEW_HEIGHT/2-h/2+h*car4.y/n);
				
		car5.setTexture(new Texture("jeep.png"));
		car5.setPosition(VIEW_WIDTH/2-w/2+car5.x*w/m, VIEW_HEIGHT/2-h/2+h*car5.y/n);
				
		car6.setTexture(new Texture("dashcam.png"));
		car6.setPosition(VIEW_WIDTH/2-w/2+car6.x*w/m, VIEW_HEIGHT/2-h/2+h*car6.y/n);
				
		car7.setTexture(new Texture("mercedes.png"));
		car7.setPosition(VIEW_WIDTH/2-w/2+car7.x*w/m, VIEW_HEIGHT/2-h/2+h*car7.y/n);
		
		list.add(car1);
		list.add(car2);
		list.add(car3);
		list.add(car4);
		list.add(car5);
		list.add(car6);
		list.add(car7);
		
		mainStage.addActor(car1);
		mainStage.addActor(car2);
		mainStage.addActor(car3);
		mainStage.addActor(car4);
		mainStage.addActor(car5);
		mainStage.addActor(car6);
		mainStage.addActor(car7);
		
		startX=car3.getX();
		startY=car3.getY();
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
	
	
	

