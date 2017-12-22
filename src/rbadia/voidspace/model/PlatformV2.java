package rbadia.voidspace.model;

import java.awt.Rectangle;

public class PlatformV2 extends Rectangle {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 20;
	private static final int HEIGHT = 4;

	public PlatformV2(int xPos, int yPos) {
		super(xPos, yPos, WIDTH, HEIGHT);
	}
}
