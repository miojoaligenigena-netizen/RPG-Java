package Vision.Game2D.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    public boolean upPressed, downPressed, leftPressed, rightPressed, statOpen;

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int code = ke.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int code = ke.getKeyCode();
        
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        
        if (code == KeyEvent.VK_E) {
            statOpen = true;
        }
    }
    
}
