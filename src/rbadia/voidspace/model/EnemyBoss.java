package rbadia.voidspace.model;

public class EnemyBoss extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final int DEFAULT_SPEED = 5;
	public static final int Y_OFFSET = 7;
	
	public static final int WIDTH = 52;
	public static final int HEIGHT = 55;
	
	
	public EnemyBoss(int xPos, int yPos){
		super(xPos, yPos,WIDTH,HEIGHT);
		this.setSpeed(DEFAULT_SPEED);
	}
	
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	
}
}