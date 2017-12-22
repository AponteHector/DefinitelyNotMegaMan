package rbadia.voidspace.model;

import java.util.Random;

public class AsteroidV2 extends Asteroid {
	
	private static final long serialVersionUID = 1L;
	

	
	
	public static final int DEFAULT_SPEED = 6 ; 
	
	public static final int WIDTH = 32 ;
	public static final int HEIGHT =  32;
	
	public AsteroidV2(int xPos, int yPos) {
		super(xPos, yPos);
		this.setSpeed(DEFAULT_SPEED);
	}

	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}
}
