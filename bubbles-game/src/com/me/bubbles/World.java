package com.me.bubbles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	private Sound pop;

	private static Random rand = new Random();
	private Array<Bubble> bubbles;
	//private float midpoint = ;
	private static Vector2 ppu;
	private Integer score;
	
	public World(){
		bubbles = new Array<Bubble>();
		score = 0;
		pop = Gdx.audio.newSound(Gdx.files.internal("pop3.wav"));
	}
	public String getScore(){
		return "Score: "+score.toString();
	}
	public void addBubble(){
		Vector2 pos = new Vector2(rand.nextFloat() * 70f, 119f - 5);
		Vector2 vel = new Vector2((float)0, (float) -1);
		float radius = rand.nextFloat() * 4.5f + 4.5f;
		Bubble bubble = new Bubble(pos, vel, radius);
		bubbles.add(bubble);
	}
	public void setPPU(Vector2 ppu){
		this.ppu = ppu;
	}
	
	private boolean pop(Bubble bubble){
		if( bubbles.removeValue(bubble, false)){
			//uses .equals
			score +=10;
			pop.play();
			System.out.println(score);
			return true;
		}
		return false;
	}
	public  void gameOver(){
		pop.dispose();
	}
	
	public boolean checkPopping(int screenX, int screenY){
		
		for(Bubble b: getBubbles()){
			Vector2 pos = new Vector2(b.getPosition());
			pos = new Vector2(pos.x * ppu.x,pos.y * ppu.y);
//			System.out.println("posx: "+pos.x);
//			System.out.println("posy: "+pos.y);
			float radius = b.getRadius() * ppu.y;
//			System.out.println("rad "+radius);
//			System.out.println("screenX: "+screenX);
//			System.out.println("screenY: "+screenY);
			if(screenX <= pos.x+radius && screenX >= pos.x-radius &&
					screenY <= pos.y + radius && screenY >= pos.y - radius){
				return pop(b);
			}
		}
		return false;
	}
	
	public Array<Bubble> getBubbles() {
		
		return bubbles;
	}
}
