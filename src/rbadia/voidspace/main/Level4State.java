package rbadia.voidspace.main;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.List;
import java.util.Random;

import rbadia.voidspace.graphics.NewGraphicsManager;
import rbadia.voidspace.model.Asteroid;
import rbadia.voidspace.model.BigBullet;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.model.EnemyBoss;
import rbadia.voidspace.model.EnemyBullet;
import rbadia.voidspace.model.Floor;
import rbadia.voidspace.model.MegaMan;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;


public class Level4State extends Level1State {

	private static final long serialVersionUID = 1L;
	protected EnemyBoss enemyBoss; 
	protected Platform[] enemyPlatforms;
	private NewGraphicsManager graphicsManager2;	
    protected ImageObserver observer; 
    
    protected EnemyBullet enemyBullets;
    
    protected static final int NEW_ENEMYBULLETS_DELAY = 500;
    
    long currentTime = System.currentTimeMillis();
    long EnemySpawnDelay = 2000 ; 
   
    Random rand = new Random();
    int enemyLife = 5; 
    
    
	
	public Level4State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			NewGraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);	
		    
			this.setNewGraphicsManager(graphicsMan);
			
		}
    
	
	
	//Boss getter
	public EnemyBoss getEnemyBoss() 					{ return enemyBoss; }
		
	@Override
	public void doStart() {	
		super.doStart();
		setStartState(GETTING_READY);
		setCurrentState(getStartState());
		
		newEnemyBoss();
		newEnemyPlatforms(getNumPlatforms());
		newEnemyBullet();
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
		drawEnemyPlatforms(); // added platforms for the enemy 
		
		drawMegaMan();
		drawAsteroid();
	   
		drawEnemyBullets();
		drawEnemyBoss();// added enemy 
	   
		checkMegaManEnemyBulletCollisions();
		checkEnemyBulletCollisions();
		
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

	@Override
	protected void drawAsteroid() {

	}	

	@Override 
	public Platform[] newPlatforms(int n){
		platforms = new Platform[n];
		for(int i=0; i<n; i++){
			this.platforms[i] = new Platform( 0 , getHeight()/2 + 140 - i*40);
		}
		return platforms;

	}
	
	public Platform[] newEnemyPlatforms(int n){
		enemyPlatforms = new Platform[n];
		for(int i=0; i<n; i++){
			this.enemyPlatforms[i] = new Platform(  getWidth() - 50 , getHeight()/2 + 140 - i*40);
		}
	
		return enemyPlatforms;

	}
	
	@Override
	protected void drawPlatforms() {
	
		//draw platforms
				Graphics2D g2d = getGraphics2D();
				for(int i=0; i<getNumPlatforms(); i++){
					getGraphicsManager().drawPlatform(platforms[i], g2d, this, i);
				}
			}
	
	protected void drawEnemyPlatforms() {
		
		//draw platforms
		Graphics2D g2d = getGraphics2D();
		for(int i=0; i<getNumPlatforms(); i++){
			getGraphicsManager().drawPlatform(enemyPlatforms[i], g2d, this, i);
		}
	}
	
	protected void drawEnemyBoss() {
		
		Graphics2D g2d = getGraphics2D();
		
		graphicsManager2.drawEnemyBoss(enemyBoss, g2d, observer);
	
	}

	public EnemyBoss newEnemyBoss(){
		this.enemyBoss = new EnemyBoss( getWidth() - 60 , (getHeight() - EnemyBoss.HEIGHT - EnemyBoss.Y_OFFSET) / 2 );
		return enemyBoss;
	}
	
	
	//ENEMY FIRING METHODS
	protected void drawEnemyBullets() {
		Graphics2D g2d = getGraphics2D();
		GameStatus status = getGameStatus();
		
		EnemyBoss enemy = this.getEnemyBoss();
		
		
		 int randomNumber = rand.nextInt((int) (this.getHeight() - enemyBullets.getPixelsTall() - 32));
		
		if((enemyBullets.getX() + enemyBullets.getWidth() >  0)){
			enemyBullets.translate(-enemyBullets.getDefaultSpeed(), 0);
			graphicsManager2.drawEnemyBullet(enemyBullets, g2d, observer);
			
		}
		else {
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastAsteroidTime) > NEW_ENEMYBULLETS_DELAY){
				// draw a new bullet
				lastAsteroidTime = currentTime;
			    
				
				   
			    enemy.setLocation( getWidth() - 60, randomNumber );		
					
				
				enemyBullets.setLocation((int) (this.getWidth() - enemyBullets.getPixelsWide()), 
						
						randomNumber	);
			}

		}
	}
	
	public EnemyBullet newEnemyBullet(){
		int xPos = enemyBoss.x ;
		int yPos = enemyBoss.y ;
		enemyBullets = new EnemyBullet(xPos, yPos);
		return enemyBullets;
	}
	
	protected void checkMegaManEnemyBulletCollisions() {
		GameStatus status = getGameStatus();
		if(this.enemyBullets.intersects(megaMan)){
			status.setLivesLeft(status.getLivesLeft() - 1);
			removeEnemyBullet(enemyBullets);
		}
		
	}
	
	public void removeEnemyBullet(EnemyBullet enemyBullet){
		// "remove" asteroid
		asteroidExplosion = new Rectangle(
				enemyBullets.x,
				enemyBullets.y,
				enemyBullets.getPixelsWide(),
				enemyBullets.getPixelsTall());
		enemyBullets.setLocation(-enemyBullets.getPixelsWide(), -enemyBullets.getPixelsTall());
		this.getGameStatus().setNewAsteroid(true);
		lastAsteroidTime = System.currentTimeMillis();
		// play asteroid explosion sound
		this.getSoundManager().playAsteroidExplosionSound();
	}
	
	protected void checkEnemyBulletCollisions() {
		GameStatus status = getGameStatus();
		
		for(int i=0; i<bullets.size(); i++){
			Bullet bullet = bullets.get(i);
			  
			
			if(enemyBoss.intersects(bullet)){
				// increase asteroids destroyed count
				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() + 100);
				enemyLife = enemyLife - 1 ;
				bullets.remove(i);
				
			}
			if(enemyLife == 0) {
				
				levelAsteroidsDestroyed = 3;
			
				bullets.remove(i);
				
			}
		}
	}
	
	
    protected void teleportEnemy() {
    	
    	
    	
        EnemyBoss enemy = this.getEnemyBoss();
   
        enemy.setLocation( getWidth() - 60, 0);		
		}
    
    	
    
	
	
	
	//new setter for newGraphicsManager
	protected void setNewGraphicsManager(NewGraphicsManager graphicsManager) { graphicsManager2 = graphicsManager; }
	//new getter for newGraphicsManager
	public NewGraphicsManager getNewGraphicsManager() { return graphicsManager2; }
	
	
	
	
}


