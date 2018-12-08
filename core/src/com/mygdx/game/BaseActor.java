package com.mygdx.game;

import javax.imageio.spi.ImageTranscoderSpi;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Intersector.MinimumTranslationVector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.compression.lzma.Base;

public class BaseActor extends Actor {
	public TextureRegion region;  
	public Polygon boundingPolygon;
	Rectangle boundary;
	public BaseActor() {
		super();
		region=new TextureRegion();
	}
	public void setTexture(Texture t){
		int w=t.getWidth();
		int h=t.getHeight();
		setWidth(w);
		setHeight(h);
		region.setRegion(t);
		setRectangleBoundary();
	}
	public Rectangle getRectangleBoundary(){
		Rectangle r;
		float w=getWidth();
		float h=getHeight();
		float[] vertices={0,0,w,0,w,h,0,h};
		boundingPolygon=new Polygon(vertices);
		boundingPolygon.setOrigin(getOriginX(), getOriginY());
		r=new Rectangle(getX(), getY(), getWidth(), getHeight());
		return r;
	}
	public void setEllipseBoundary(){
		int n=8;
		float w=getWidth();
		float h=getHeight();
		float[] vertices=new float[2*n];
		for(int i=0;i<n;i++){
			float t=i*6.28f/n;
			vertices[2*i]=w/2*MathUtils.cos(t)+w/2;
			vertices[2*i+1]=h/2*MathUtils.sin(t)+h/2;
			boundingPolygon=new Polygon(vertices);
			boundingPolygon.setOrigin(getOriginX(), getOriginY());
		}
		
	}
	public Polygon getBoundingPolygon(){
		boundingPolygon.setPosition(getX(), getY());
		boundingPolygon.setRotation(getRotation());
		return boundingPolygon;
	}
	public boolean overlaps(BaseActor other,boolean resolve){
		Polygon poly1=getBoundingPolygon();
		Polygon poly2=other.getBoundingPolygon();
		if(!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
			return false;
		MinimumTranslationVector mtv=new MinimumTranslationVector();
		boolean polyOverlap=Intersector.overlapConvexPolygons(poly1, poly2,mtv);
		if(polyOverlap && resolve){
			//this.moveBy(mtv.normal.x*mtv.depth, mtv.normal.y*mtv.depth);
		}
		float significant=0.5f;
		return (polyOverlap && (mtv.depth>significant));
	}
	public void copy(BaseActor original){
		this.region=new TextureRegion(original.region);
		if(original.getBoundingPolygon()!=null){
			boundingPolygon=new Polygon(original.getBoundingPolygon().getVertices());
			boundingPolygon.setOrigin(original.getOriginX(),original.getOriginY());
		}
		setPosition(original.getX(), original.getOriginY());
		setOriginX(original.getOriginX());
		setOriginY(original.getOriginY());
		setWidth(original.getWidth());
		setHeight(original.getHeight());
		setColor(original.getColor());
		setVisible(original.isVisible());
	}
	public BaseActor clone(){
		BaseActor newbie=new BaseActor();
		newbie.copy(this);
		return newbie;
	}
	
	public void act(float dt){
		super.act(dt);
		//moveBy(velocityX*dt,velocityY*dt);
	}
	
	public void draw(Batch b,float parentAlpha){
		Color c=getColor();
		b.setColor(c.r,c.g,c.b,c.a);
		if(region.getRegionWidth()!=0)
		if(isVisible())
			b.draw(region,getX(),getY(), getOriginX(), getOriginY(),
					getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		
	}
	public void setRectangleBoundary(){
		float w=getWidth();
		float h=getHeight();
		float[] vertices={0,0,w,0,w,h,0,h};
		boundingPolygon=new Polygon(vertices);
		boundingPolygon.setOrigin(getOriginX(), getOriginY());
	}

}
