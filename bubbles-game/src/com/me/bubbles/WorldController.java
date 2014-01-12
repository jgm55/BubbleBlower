package com.me.bubbles;

import screens.GameScreen;
import screens.StartScreen;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class WorldController implements InputProcessor {
	
	private World world;
	private BubblesGame game;
	
	public WorldController(World world,BubblesGame game) {
		this.world = world;
		this.game = game;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.BACK){
			//game.setScreen(new StartScreen(game));
			game.setScreen(new GameScreen(game));
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Buttons.LEFT){
			return world.checkPopping(screenX, screenY);
		} 
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}