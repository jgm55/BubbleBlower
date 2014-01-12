package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.me.bubbles.BubblesGame;
import com.me.bubbles.World;
import com.me.bubbles.WorldController;

public class GameScreen implements Screen{
	private final int GAME_OVER_AMOUNT = 120;
	private static final int BUBBLE_FRAMES = 18;
	
	private World world;
	private WorldRenderer renderer;
	private BubblesGame game;
	private int gameOverCounter;
		
	private WorldController inputProcessor;
	private int frameCounter = 0;
	
	private static MicThread mic;
	
	private static boolean makeBubble;

	private static Thread micThread ;
	
	public GameScreen(BubblesGame game){
		this.game = game;
		world = new World();
		inputProcessor = new WorldController(world, game);

		Gdx.input.setInputProcessor(inputProcessor);
		
		renderer = new WorldRenderer(world, true);
		world.setPPU(renderer.getPPU());
		
		mic = new MicThread();
		micThread = new Thread(mic);
		micThread.start();
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if(makeBubble){
			world.addBubble();
			makeBubble = false;
			//frameCounter = 0;
		}
		else if(renderer.gameStart){
			if(frameCounter >= BUBBLE_FRAMES){
				mic.stop();
//				System.out.println("volume: "+mic.getVolume());
				if(mic.getVolume() >= 31000){
					makeBubble = true;
					gameOverCounter = 0;
				} else if(gameOverCounter >= GAME_OVER_AMOUNT){
					renderer.gameOver = true;
				} 
				frameCounter = 0;
			} else {
				frameCounter ++;
			}
			gameOverCounter++;
		} else {
			mic.stop();
			if(mic.getVolume() >= 32767){
				makeBubble = true;
				gameOverCounter = 0;
				renderer.gameStart = true;
			}
		}
		renderer.render();
	}

	@Override
	public void dispose() {
		try {
			micThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		world.gameOver();
		renderer.dispose();
	}


	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
