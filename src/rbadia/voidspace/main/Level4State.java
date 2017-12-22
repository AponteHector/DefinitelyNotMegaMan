package rbadia.voidspace.main;
import java.awt.Graphics2D;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.EnemyBoss;
import rbadia.voidspace.model.Platform;
import rbadia.voidspace.sounds.SoundManager;

public class Level4State extends Level3State {

	protected EnemyBoss enemyBoss ; 
	
	
	
	
	
	public Level4State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic, InputHandler inputHandler,
			GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		
	}
	
	
	//Boss getter
	public EnemyBoss getEnemyBoss() 					{ return enemyBoss; }
		
	
	
	@Override
	protected void drawAsteroid() {
		}	
	
	@Override
	protected void drawPlatforms() {
	}
    
	
	
	protected void drawEnemyBoss() {
		//draw one of three possible MegaMan poses according to situation
		Graphics2D g2d = getGraphics2D();
		
		
		getGraphicsManager().drawEnemyBoss(enemyBoss, g2d, this);
		
	}

	}


