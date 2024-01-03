import com.redlit.engine.GameContainer;
import com.redlit.engine.GameObject;
import com.redlit.engine.Renderer;

public class CannonBall extends GameObject {

	int flySpeed = 5;
	int realX;
	int realY;
	
	public CannonBall(int posX, int posY, String tag) {
		this.posX = posX;
		this.posY = posY;
		this.tag = tag;
		this.width = 20;
		this.realX = posX + width/2;
		this.realY = posY + width/2;
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		if(MainGameLoop.paused) {
			return;
		}
		move();
		collision(gc);
	}

	private void collision(GameContainer gc) {
		for(int i = 0; i < MainGameLoop.circles.size();i++) {
			Circle c = MainGameLoop.circles.get(i);
			int circleRad = c.getWidth()/2;
			int circleX = c.getPosX() + circleRad;
			int circleY = c.getPosY() + circleRad;
			
			int dx = realX - circleX;
			int dy = realY - circleY;
			
			double distance = Math.sqrt(dx * dx + dy * dy);
			
			if(distance < circleRad + width/2) {System.out.println("Collision detected");
				MainGameLoop.circles.get(i).hit(gc);
			}
		}		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawCircle(width/2, 0xfffc8a17, posX, posY);
	

	}
	
	private void move() {
		this.posY -= flySpeed;
		this.realY -= flySpeed;
		
		if(posY <= 50) {
			this.dead = true;
		}
	}
	
	
	
	
}
