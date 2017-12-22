package rbadia.voidspace.model;

import java.awt.Graphics2D;

import rbadia.voidspace.main.GameStatus;

public class EnemyBoss extends MegaMan {

	public static final int DEFAULT_SPEED = 5;
	
	
	public static final int WIDTH = 52;
	public static final int HEIGHT = 55;
	
	
	public EnemyBoss(int xPos, int yPos){
		super(xPos, yPos);
		this.setSpeed(DEFAULT_SPEED);
	}
	
	
	
}
