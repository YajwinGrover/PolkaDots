import java.awt.event.MouseEvent;

import com.redlit.engine.AbstractGame;
import com.redlit.engine.GameContainer;
import com.redlit.engine.Renderer;

public class EndScreen extends AbstractGame {
	int score;

	public EndScreen(int score) {
		this.score = score;
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		clicked(gc);

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawText("Score: " + score, 100, 10, 0xffff0000);
		r.fillRect(550, 300, 100, 20, 0xff0000ff);
		r.drawText("RESTART", 575, 305, 0xffffffff);
		r.setPixel(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 0xffff0000);
		

	}
	private void clicked(GameContainer gc) {
		if(gc.getInput().isButtonDown(MouseEvent.BUTTON1)) {
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			if(mouseX < 550 + 100 && mouseX > 550) {
				if(mouseY < 300 + 20 && mouseY > 300) {
					gc.changeActiveGame(new MainGameLoop());
					
				}
			}
		}
	}
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setup(GameContainer gc) {
		// TODO Auto-generated method stub
		
	}

}
