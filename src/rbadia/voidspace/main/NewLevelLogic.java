package rbadia.voidspace.main;

import java.awt.Font;

public class NewLevelLogic extends LevelLogic {
    
	private long lastBulletTime;
	private long lastExchangeTime;
	
	private int stack= 0;
	private int mute = 0;

	private LevelState levelState;

	protected Font originalFont;
	protected Font bigFont;
	protected Font biggestFont;
	
	
	
	
	public void handleKeysDuringPlay(InputHandler ih, LevelState levelState) {

		GameStatus status = getLevelState().getGameStatus();

		// fire bullet if space is pressed
		if(ih.isSpacePressed()){
			// fire only up to 5 bullets per second
			stack=0;
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastBulletTime) > 1000/5){
				lastBulletTime = currentTime;
				getLevelState().fireBullet();
			}
		}

		if(ih.isEPressed()){
			if(status.getAsteroidsDestroyed()>= 1500){
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastExchangeTime > 1000)){
					lastExchangeTime = currentTime;
					status.setAsteroidsDestroyed(status.getAsteroidsDestroyed() - 1500);
					status.setLivesLeft(status.getLivesLeft() + 1);
				}
			}
		}

		if(ih.isQPressed()){
			if(stack==0 && status.getAsteroidsDestroyed()>= 0){
				stack++;
				status.setAsteroidsDestroyed(status.getAsteroidsDestroyed()-0);
			}
			else if(stack>= 1){
				long currentTime = System.currentTimeMillis();
				if((currentTime - lastBigBulletTime) > 1000){
					lastBigBulletTime = currentTime;
					getLevelState().fireBigBullet();
				}

			}
		}

		if(ih.isShiftPressed()){
			getLevelState().speedUpMegaMan();
		}

		if(ih.isUpPressed()){
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastBigBulletTime) > 570){
				lastBigBulletTime = currentTime;
				for(int i=0; i<6; i++){
					getLevelState().moveMegaManUp();
				}
			}
		}

		if(ih.isDownPressed()){
			getLevelState().moveMegaManDown();
		}

		if(ih.isLeftPressed()){
			getLevelState().moveMegaManLeft();
		}

		if(ih.isRightPressed()){
			getLevelState().moveMegaManRight();
		}
		
		if(ih.isNPressed()) {                      // ADDED
			
			getLevelState().skipLevel();
		}
	}
}
