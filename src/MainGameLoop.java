import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import com.redlit.engine.AbstractGame;
import com.redlit.engine.GameContainer;
import com.redlit.engine.Renderer;
import com.redlit.engine.gfx.Image;

public class MainGameLoop extends AbstractGame{
	
	public static ArrayList<Circle> circles = new ArrayList<Circle>();
	Random generator = new Random();
	public static int blue = 0xff0096ff;
	public static int red = 0xffae0700;
	public static int green = 0xff66ff00;
	public static int score = 0;
	public int timer;
	public static boolean paused = false;
	
	Player cannon;
	Image background = new Image("/resources/background.jpeg");

	public MainGameLoop() {
		for(int i = 0; i < 5; i ++) {
			circles.add(new Circle("Circle", new GameContainer(null)));
		}
		cannon = new Player("Player","/resources/cannon.png",new GameContainer(null));
	}
	
	
	@Override
	public void update(GameContainer gc, float deltaTime) {
		for(int i = 0; i < circles.size(); i ++) {
			circles.get(i).update(gc, deltaTime);
			
			if(circles.get(i).isDead()) {
				circles.remove(i);
				i--;
			}
		}
		timer ++;
		if(timer <= 1000000000 && circles.size() < 15) {
			circles.add(new Circle("Circle", new GameContainer(null)));
		}
		cannon.update(gc, deltaTime);
		if(cannon.health <= 0) {
			gc.changeActiveGame(new EndScreen(score));
			score = 0;
		}
		pause(gc);
		
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(background, 0, 0);
		for(Circle c : circles) {
			c.render(gc, r);
		}
		cannon.render(gc, r);
		r.drawText(""+ score, 0, 0, 0xffffffff);
		
		r.fillRect(gc.getWidth() - 500, 0, 500, 20, 0xffff0000);
		r.fillRect(gc.getWidth() - 500, 0, cannon.health * 5, 20, 0xff00ff00);
		
	
	}
	
	public void pause(GameContainer gc) {
		if(gc.getInput().isKeyDown(KeyEvent.VK_P)) {
			paused = !paused;
		}
	}

	@Override
	public void reset() {
		
		
	}


	@Override
	public void setup(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}


	
}
