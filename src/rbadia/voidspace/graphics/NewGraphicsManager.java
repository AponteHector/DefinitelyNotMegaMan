package rbadia.voidspace.graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import rbadia.voidspace.model.Asteroid;
//import rbadia.voidspace.model.BigAsteroid;
import rbadia.voidspace.model.BigBullet;
//import rbadia.voidspace.model.Boss;
import rbadia.voidspace.model.Bullet;

import rbadia.voidspace.model.Floor;
//import rbadia.voidspace.model.BulletBoss;
//import rbadia.voidspace.model.BulletBoss2;
import rbadia.voidspace.model.MegaMan;
import rbadia.voidspace.model.Platform;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import rbadia.voidspace.graphics.GraphicsManager;
import rbadia.voidspace.model.EnemyBoss;
import rbadia.voidspace.model.EnemyBullet;


	public class NewGraphicsManager extends GraphicsManager {

		private BufferedImage enemyBossImg;
		private BufferedImage enemyBulletImg;

		/**
		 * Creates a new graphics manager and loads the game images.
		 */
		public NewGraphicsManager(){
			// load images
			try {
			
				this.enemyBossImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/realBossEnemy.png")); //added
				this.enemyBulletImg = ImageIO.read(getClass().getResource("/rbadia/voidspace/graphics/BulletBoss.png")); //added
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "The graphic files are either corrupt or missing.",
						"VoidSpace - Fatal Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(-1);
			}
			
		}
		
        // DRAW METHODS

		public void drawEnemyBoss (EnemyBoss enemyBoss, Graphics2D g2d, ImageObserver observer){ 
			g2d.drawImage(enemyBossImg, enemyBoss.x, enemyBoss.y, observer);	
		}
	
	   public void drawEnemyBullet (EnemyBullet bullet, Graphics2D g2d, ImageObserver observer) {
			g2d.drawImage(enemyBulletImg, bullet.x, bullet.y, observer);	
	    }
	
}