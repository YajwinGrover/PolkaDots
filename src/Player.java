import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.redlit.engine.GameContainer;
import com.redlit.engine.GameObject;
import com.redlit.engine.Renderer;
import com.redlit.engine.gfx.Image;

public class Player extends GameObject {
	
	String path;
	Image playerImage;
	boolean firstInit = true;
	int movementSpeed = 5;
	public ArrayList<CannonBall> balls = new ArrayList<CannonBall>();
	int reloadSpeed = 10;
	int reloadCounter = 0;
	int lastShot = 0;
	int health = 100;
	boolean deductable = true;
	int deductableCounter = 0;
	public Player(String tag, String path, GameContainer gc) {
		this.tag = tag;
		this.playerImage = new Image(path);
		
		
		
		
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		if(firstInit) {
			this.posX = gc.getWidth()/2;
			this.posY = gc.getHeight() - playerImage.getHeight();
			firstInit = false;
		}
		if(MainGameLoop.paused) {
			return;
		}
		reloadCounter ++;
		deductableCounter ++;
		for(int i = 0; i < balls.size(); i++) {
			balls.get(i).update(gc, deltaTime);
			if(balls.get(i).isDead()) {
				balls.remove(i);
				i--;
			}
		}
		movement(gc,deltaTime);
		if(reloadCounter >= reloadSpeed && gc.getInput().isKey(KeyEvent.VK_SPACE)) {
			attack(gc);
			reloadCounter = 0;
		}
		collision(gc);
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for(CannonBall ball: balls) {
			ball.render(gc, r);
		}
		r.drawImage(playerImage, posX, posY);
		

	}
	
	
	public void movement(GameContainer gc, float deltaTime) {
		
		if(gc.getInput().isKey(KeyEvent.VK_A)) {
			this.posX -= movementSpeed;
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			this.posX +=  movementSpeed;
		}
		
		if(posX < 0 ) {
			posX = 0;
		}
		if(posX >= gc.getWidth()) {
			posX = gc.getWidth() - width;
		}
	
	}
	
	public void attack(GameContainer gc) {
		balls.add(new CannonBall(posX + playerImage.getWidth()/2 - 10, posY + 10,"Cannon Ball"));
		
	}
	
	private void collision(GameContainer gc) {
		for(int i = 0; i < MainGameLoop.circles.size();i++) {
			
			Circle c = MainGameLoop.circles.get(i);
			int circleRad = c.getWidth()/2;
			int hugeVariableNameCircleCenterX = c.getPosX() + circleRad;
			int hugeVariableNamecircleCenterY = c.getPosY() + circleRad;
			
			int thisX = this.posX + playerImage.getWidth()/2;
			int thisY = this.posY + playerImage.getHeight()/2;
			int collisionX = playerImage.getWidth()/2 + circleRad;
			int collisionY = playerImage.getHeight()/2 + circleRad;
			
			int dx = thisX - hugeVariableNameCircleCenterX;
			int dy = thisY - hugeVariableNamecircleCenterY;
			
			double distance = Math.sqrt(dx * dx + dy * dy);
			if((distance < collisionX || distance < collisionY) && deductable) {
				this.health -= 10;
				MainGameLoop.circles.get(i).hit(gc);
			}
		}
	}

}
