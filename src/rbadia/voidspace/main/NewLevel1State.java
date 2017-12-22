package rbadia.voidspace.main;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.sounds.SoundManager;

public class NewLevel1State extends Level1State {

		public NewLevel1State(int level, MainFrame frame, GameStatus status, LevelLogic gameLogic,
			InputHandler inputHandler, GraphicsManager graphicsMan, SoundManager soundMan) {
		super(level, frame, status, gameLogic, inputHandler, graphicsMan, soundMan);
		// TODO Auto-generated constructor stub
	}

		public int skipLevel() {
			
			 levelAsteroidsDestroyed = 3;
			return levelAsteroidsDestroyed;
			
		}
		
	

}
