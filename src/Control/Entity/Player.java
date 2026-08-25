package Control.Entity;

import Vision.Game2D.Main.GamePanel;
import Vision.Game2D.Main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Player extends Entity {
    private final GamePanel gp;
    private final KeyHandler keyH;
    private Stats = new Stats(this);

    private final String spritePath = "src/Model/Player_Sprites/";

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        action = Actions.DOWN;
    }

    public Player(GamePanel gp, KeyHandler keyH, String name) {
        super(name);
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getImage();
    }

    public void getImage() {
        up1 = getSprite("player_up_1.png");
        up2 = getSprite("player_up_2.png");
        down1 = getSprite("player_down_1.png");
        down2 = getSprite("player_down_2.png");
        right1 = getSprite("player_right_1.png");
        right2 = getSprite("player_right_2.png");
        left1 = getSprite("player_left_1.png");
        left2 = getSprite("player_left_2.png");
    }

    public BufferedImage getSprite(String fileName) {
        try {
            File file = new File(spritePath + fileName);
            BufferedImage originalImage = ImageIO.read(file);
            
            int targetSize = gp.getTileSize() * 2;

            java.awt.GraphicsConfiguration gc = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
            BufferedImage optimizedImage = gc.createCompatibleImage(targetSize, targetSize, originalImage.getTransparency());

            Graphics2D g2 = optimizedImage.createGraphics();
            g2.drawImage(originalImage, 0, 0, targetSize, targetSize, null);
            g2.dispose();

            return optimizedImage;
        } catch (IOException e) {
            System.err.println("File not found: " + spritePath + fileName);
            throw new RuntimeException(e);
        }
}
    
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                action = Actions.UP;
                y -= speed;
            }
            if (keyH.downPressed) {
                action = Actions.DOWN;
                y += speed;
            }
            if (keyH.leftPressed) {
                action = Actions.LEFT;
                x -= speed;
            }
            if (keyH.rightPressed) {
                action = Actions.RIGHT;
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 1;
                spriteCounter = 0;
            }
        }
    }
    
    public void draw(Graphics2D g) {
        BufferedImage image = null;
        switch (action){
            case UP -> {
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
            }
            case DOWN -> {
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
            }
            case LEFT -> {
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
            }
            case RIGHT -> {
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
            }
        }

        g.drawImage(image, x, y, null);
    }
}
