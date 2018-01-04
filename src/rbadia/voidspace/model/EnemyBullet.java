package rbadia.voidspace.model;

public class EnemyBullet extends GameObject {

private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_SPEED = 6;
	public static final  int WIDTH = 70;
	public static final int HEIGHT = 70;
	
	public EnemyBullet(int xPos, int yPos) {
		super(xPos, yPos, WIDTH, HEIGHT);
		this.setSpeed(15);
	}
	public int getDefaultSpeed(){
		return DEFAULT_SPEED;
	}
	
}
