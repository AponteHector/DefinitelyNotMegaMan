package rbadia.voidspace.main;
import java.awt.Graphics2D;
import java.util.Random;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.AsteroidV2;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

          

public class Level3State extends Level2State {

	protected Asteroid asteroid2;
	protected Asteroid asteroid3;
	protected Asteroid asteroid4;
	

	
	protected int numPlatforms;
	
	private static final long serialVersionUID = -2094575762243216079L;

	// Constructors
	public Level3State(int level, MainFrame frame, GameStatus status, 
			LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
	}

	@Override
	public void doStart() {	
		super.doStart();
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
	}
    
	
	@Override
	protected void drawAsteroid() {
		Graphics2D g2d = getGraphics2D();
		if((asteroid.getX() + asteroid.getPixelsWide() >  0)) {
			asteroid.translate(-asteroid.getSpeed(), asteroid.getSpeed()/2);
			getGraphicsManager().drawAsteroid(asteroid, g2d, this);
		}
		else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){

				asteroid.setLocation(this.getWidth() - asteroid.getPixelsWide(),
						rand.nextInt(this.getHeight() - asteroid.getPixelsTall() - 32));
			}
			else {
				// draw explosion
				getGraphicsManager().drawAsteroidExplosion(asteroidExplosion, g2d, this);
			}
		}	
	}
	
	@Override
	public Platform[] newPlatforms(int n){
		platforms = new Platform[n];
		for(int i=0; i<n; i++){
			this.platforms[i] = new Platform(getWidth()/2 + 140 - i * 40 , getHeight()/2 + 140 - i*40);
		}
		return platforms;
	
				
	
	}	
	}
	

		
		
	
	
	
	
	
	

