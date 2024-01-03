import java.awt.event.MouseEvent;
import java.util.Random;

import com.redlit.engine.GameContainer;
import com.redlit.engine.GameObject;
import com.redlit.engine.Renderer;

public class Circle extends GameObject {
	
	Random generator = new Random();
	float moveY;
	float moveX;
	int fallTime;
	int color;
	int realPosX;
	int realPosY;
	int mouseX;
	int mouseY;
	boolean firstInit = true;
	boolean bouncing = false;
	
	
	
	

	
	
	public Circle(String tag,GameContainer gc) {
		this.tag = tag;
		this.width = 74;
		this.posX = generator.nextInt(gc.getWidth() - this.width/2);
		this.realPosX = this.posX + width/2;
		this.realPosY = this.posY + width/2;
		posY = 0;
		
		moveY = generator.nextInt(5) + 1;
		moveX = 0;
		
		
		int randomInt = generator.nextInt(3) + 1;
		
		
		
		
		if(randomInt == 1) {
			color = MainGameLoop.red;
		}
		else if(randomInt == 2) {
			color = MainGameLoop.green;
		}
		else if(randomInt == 3) {
			color = MainGameLoop.blue;
		}
		else {
			System.err.println("Error has occured. Color generator errororororor");
		}
		
		
		
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		if(MainGameLoop.paused) {
			return;
		}
		if(firstInit) {
			this.posX = generator.nextInt(gc.getWidth() - this.width/2);
			this.realPosX = this.posX + width/2;
			this.realPosY = this.posY + width/2;
			posY = 0;
			firstInit = false;
		}
		isClicked(gc);
		move(gc);
		
		if(realPosX < 0 || realPosX > gc.getWidth()) {
			this.dead = true;
		}
		
	}

	private void isClicked(GameContainer gc) {
		if(gc.getInput().isButtonDown(MouseEvent.BUTTON1)) {
			int radius = width/2;
			mouseX = gc.getInput().getMouseX();
			mouseY = gc.getInput().getMouseY();
			
			double distance = Math.sqrt((mouseX - realPosX) * (mouseX - realPosX) + (mouseY - realPosY) * (mouseY - realPosY));
			
			
			
			if(distance < radius) {
				
				clicked(gc);
			}
			else {
			
			}
		}
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		
		r.drawCircle(width/2, color, posX, posY);
		

	}
	
	private void randomize(GameContainer gc) {
		this.posX = generator.nextInt(gc.getWidth() - this.width);
		moveY = generator.nextInt(5) + 1;
	}
	
	private void clicked(GameContainer gc) {
		MainGameLoop.score ++;
		this.posY = 0;
		randomize(gc);
	}
	
	public void hit(GameContainer gc) {
		MainGameLoop.score ++;
		this.posY = 0;
		randomize(gc);
	}
	
	private void move(GameContainer gc) {
		
		posY += moveY;
		posX += moveX;
		if(posY + this.width/2 > gc.getHeight()) {
			bouncing = true;
			bounce();
			
		}
		if(posY <= 0) {
			bouncing = false;
			bounce();
		}
		if(posX < 0 || posX + width > gc.getWidth()) {
			bouncing = false;
			bounce();
			
		}
		realPosY = posY + width/2;
		realPosX = posX + width/2;
	}
	
	public void bounce() {
		
		
		moveX = generator.nextInt(10) - 5;
		moveY = generator.nextInt(5) + 1;
		
		if(bouncing) {
			moveX -= moveX * 2;
			moveY -= moveY * 2;
		}
		
	}

}
