package com.mygdx.game;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class AnimatedActor extends BaseActor {
	private float elepsedTime;
	private Animation activeAnim;
	private String activeName;
	private HashMap<String,Animation> animationStorage;
	protected int velocityX,velocityY;
	public AnimatedActor() {
		// TODO Auto-generated constructor stub
		super();
		elepsedTime=0;
		activeAnim=null;
		activeName=null;
		animationStorage=new HashMap<String, Animation>();
		}
	public void storeAnimation(String name,Animation anim){
		animationStorage.put(name, anim);
		if(activeName==null)
			setActiveAnimation(name);
	}
	public void storeAnimation(String name,Texture tex){
		TextureRegion reg=new TextureRegion(tex);
		TextureRegion[] frames={reg};
		Animation anim=new Animation(0.1f, frames);
		storeAnimation(name, anim);
	}
	public void setActiveAnimation(String name) {
		// TODO Auto-generated method stub
		if(!animationStorage.containsKey(name)){
			System.out.println("no animation "+name);
			return;
		}
		if(activeName!=null)
		if(activeName.equals(name))
			return;
		activeName=name;
		activeAnim=animationStorage.get(name);
		Texture tex=((TextureRegion) activeAnim.getKeyFrame(0)).getTexture();
		setWidth(tex.getWidth());
		setHeight(tex.getHeight());
	}
	public String getAnimationName(){
		return activeName;
	}
	
	public void act(float dt){
		super.act(dt);
		elepsedTime+=dt;
		moveBy(velocityX*dt, velocityY*dt);
		
	}
	public void draw(Batch b,float parentAlpha){
		if(activeAnim!=null)
		region.setRegion((TextureRegion) activeAnim.getKeyFrame(elepsedTime));
		super.draw(b, parentAlpha);
	}

}
