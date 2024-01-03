import com.redlit.engine.GameContainer;
import com.redlit.engine.GameObject;
import com.redlit.engine.Renderer;
import com.redlit.engine.gfx.Font;

public class Button extends GameObject {
	
	String message;
	int color;
	int textColor;
	int padding;

	public Button(int posX, int posY, int width, int height,int color,int textColor, int padding, String tag, String message) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.tag = tag;
		this.message = message;
		this.color = color;
		this.textColor = textColor;
		this.padding = padding;
	}

	@Override
	public void update(GameContainer gc, float deltaTime) {
		

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect(posY, posY, width, height, color);
		r.drawText(message, posX + padding, posY + padding, textColor);

	}
	
	

}
