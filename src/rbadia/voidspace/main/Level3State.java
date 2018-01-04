package rbadia.voidspace.main;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.AsteroidV2;
import rbadia.voidspace.model.BigBullet;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

          

public class Level3State extends Level2State {
    
	
	
	protected Asteroid asteroid2;
	protected Asteroid asteroid3;
	
	
    private Random rand = new Random();
    
   
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
		
		AnotherNewAsteroid(this);
		
		
	}
    
	
	
	@Override
	public void removeAsteroid(Asteroid asteroid){
		// "remove" asteroid
		asteroidExplosion = new Rectangle(
				asteroid.x,
				asteroid.y,
				asteroid.getPixelsWide(),
				asteroid.getPixelsTall());
		asteroid.setLocation(-asteroid.getPixelsWide(), -asteroid.getPixelsTall());
		this.getGameStatus().setNewAsteroid(false);
		lastAsteroidTime = System.currentTimeMillis();
		// play asteroid explosion sound
		this.getSoundManager().playAsteroidExplosionSound();
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
	
	
	protected void drawAnotherAsteroid() {
		Graphics2D g2d = getGraphics2D();
		if((asteroid2.getX() + asteroid2.getPixelsWide() >  0 )) {
			
			asteroid2.translate(0 , asteroid2.getSpeed() );
			getGraphicsManager().drawAsteroid(asteroid2, g2d, this);
			
			
			
		}
		else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ASTEROID_DELAY){

				asteroid2.setLocation(rand.nextInt((int)(getWidth() - Asteroid.WIDTH - 32)),
				0 - Asteroid.HEIGHT);
				
				
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
	
@Override	
public void updateScreen(){
	Graphics2D g2d = getGraphics2D();
	GameStatus status = this.getGameStatus();
 
	// save original font - for later use
	if(this.originalFont == null){
		this.originalFont = g2d.getFont();
		this.bigFont = originalFont;
	}
   
	clearScreen();
	drawStars(50);
	drawFloor();
	drawPlatforms();
	drawMegaMan();
	
	drawAsteroid();
	drawAnotherAsteroid();
	checkBullletAsteroid2Collisions();
	checkMegaManAsteroid2Collisions();
	checkAsteroid2FloorCollisions();
	
	
	drawBullets();
	drawBigBullets();
	checkBullletAsteroidCollisions();
	checkBigBulletAsteroidCollisions();
	checkMegaManAsteroidCollisions();
	checkAsteroidFloorCollisions();
    
	// update asteroids destroyed (score) label  
	getMainFrame().getDestroyedValueLabel().setText(Long.toString(status.getAsteroidsDestroyed()));
	// update lives left label
	getMainFrame().getLivesValueLabel().setText(Integer.toString(status.getLivesLeft()));
	//update level label
	getMainFrame().getLevelValueLabel().setText(Long.toString(status.getLevel()));
}


public Asteroid AnotherNewAsteroid(Level1State screen){
	int xPos = rand.nextInt((int)(screen.getWidth() - Asteroid.WIDTH - 32)) ;
	int yPos = 0 - Asteroid.HEIGHT ; 
	asteroid2 = new Asteroid(xPos, yPos);
	return asteroid2;
}

protected void checkAsteroid2FloorCollisions() {
	for(int i=0; i<9; i++){
		if(asteroid2.intersects(floor[i])){
			removeAsteroid(asteroid2);

		}
	}
}

protected void checkMegaManAsteroid2Collisions() {
	GameStatus status = getGameStatus();
	if(asteroid2.intersects(megaMan)){
		status.setLivesLeft(status.getLivesLeft() - 1);
		removeAsteroid(asteroid2);
	}
}

protected void checkBullletAsteroid2Collisions() {
	GameStatus status = getGameStatus();
	for(int i=0; i<bullets.size(); i++){
		Bullet bullet = bullets.get(i);
		if(asteroid2.intersects(bullet)){
			// increase asteroids destroyed count
			status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 100);
			removeAsteroid(asteroid2);
			levelAsteroidsDestroyed++;
			damage=0;
			// remove bullet
			bullets.remove(i);
			break;
		}
	}
}



}	
		
	
	
	
	
	
	

