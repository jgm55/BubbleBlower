package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.me.bubbles.Bubble;
import com.me.bubbles.World;

public class WorldRenderer {

	private static final float CAMERA_WIDTH = 70f;
	private static final float CAMERA_HEIGHT = 119f;
	private int WIDTH = 540;
	private int HEIGHT = 960;
	private World world;
	private OrthographicCamera cam;
	private BitmapFont font;
	
	private Texture wand;

	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();

	public static boolean gameOver;
	public static boolean gameStart;
	/** Textures **/
	//private TextureRegion bubbleTexture;
	
	private SpriteBatch spriteBatch;
	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX;	// pixels per unit on the X axis
	private float ppuY;	// pixels per unit on the Y axis	
	
	public void setSize (float w, float h) {
		this.width = (int) w;
		this.height = (int) h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}
	
	public WorldRenderer(World world, boolean debug) {
		this.world = world;
		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.cam.update();
		this.cam.setToOrtho(true, CAMERA_WIDTH, CAMERA_HEIGHT);
		//Configuration config = getResources().getConfiguration();
		setSize(WIDTH,HEIGHT);
		this.debug = debug;
		spriteBatch = new SpriteBatch();
		//texture = new Texture(Gdx.files.internal("data/score.png"));
		//texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		gameOver = false;
		gameStart = false;
		
		font = new BitmapFont(Gdx.files.internal("data/comicSans.fnt"),Gdx.files.internal("data/comicSans.png"),false);
		wand = new Texture(Gdx.files.internal("data/bubbleWand.png"));

	}
	
	public Vector2 getPPU(){
		Vector2 toRet = new Vector2(ppuX,ppuY);
		return toRet;
	}
	public void render() {
		if(gameOver){
			renderGameOver();
		} else if(!gameStart){
			renderGameStart();
		} else {
			spriteBatch.begin();
			//drawBubbles();
			spriteBatch.draw(wand,WIDTH/2-50,0);
			font.draw(spriteBatch, world.getScore(), 4, HEIGHT-80);
			spriteBatch.end();
			if (debug){
				drawDebug();
			}
		}
	}
	private void renderGameStart() {
		spriteBatch.begin();
		int midpoint = WIDTH / 2 - 75;
		font.draw(spriteBatch, "BLOW!!", midpoint, HEIGHT-180);
		font.draw(spriteBatch, "You Stop,", midpoint, HEIGHT-260);
		font.draw(spriteBatch, "You Lose.", midpoint, HEIGHT-340);
		font.draw(spriteBatch, "Pop the bubbles to get points!", 50, HEIGHT-420);
		spriteBatch.draw(wand,WIDTH/2-50,0);
		spriteBatch.end();
	}

	public void dispose(){
		gameOver = false;
		gameStart = false;
		font.dispose();
		wand.dispose();
	}
	public void renderGameOver(){
		spriteBatch.begin();
		font.draw(spriteBatch, "Game Over", WIDTH / 2 - 40, HEIGHT/2);
		font.draw(spriteBatch, "Final "+ world.getScore(), WIDTH / 2 - 40, HEIGHT/2 - 80);
		spriteBatch.end();
	}


	/*private void drawBubbles() {
		for (Bubble bubble : world.getBubbles()) {
			spriteBatch.draw(bubbleTexture, bubble.getPosition().x * ppuX,
					bubble.getPosition().y * ppuY);
		}
	}*/

	private void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Line);
		
		// render Bob
		for (Bubble bubble : world.getBubbles()) {
			float x1 = bubble.getPosition().x ;
			float y1 = bubble.getPosition().y;
			debugRenderer.setColor(new Color(1, 1, 1, 1));
			debugRenderer.circle(x1, y1, bubble.getRadius());
		}
		
		debugRenderer.end();
	}
}