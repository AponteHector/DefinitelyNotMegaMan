package rbadia.voidspace.main;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.Bullet;
import rbadia.voidspace.sounds.SoundManager;

public class NewLevel1State extends Level1State {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int NEW_ASTEROID_DELAY = 0;
	
	
	
		public NewLevel1State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic,
			InputHandler inputHandler, GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		// TODO Auto-generated constructor stub
	}
        @Override
		public int skipLevel() {
			
			 levelAsteroidsDestroyed = 3;
			return levelAsteroidsDestroyed;
			
		}
		
    	public boolean moveBullet2(Bullet bullet){
    		if(bullet.getY() - bullet.getSpeed() >= 0){
    			bullet.translate(-bullet.getSpeed(), 0);
    			return false;
    		}
    		else{
    			return true;
    		}
    	}
	

}

