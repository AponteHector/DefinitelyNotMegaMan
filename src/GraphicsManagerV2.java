import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;

public class GraphicsManagerV2 extends GraphicsManager {

	
	@Override  
    public void drawAsteroid(Asteroid asteroid, Graphics2D g2d, ImageObserver observer) {
		
		g2d.drawImage(asteroidImg, asteroid.x, asteroid.y, observer);
		
	}
	
	
}
