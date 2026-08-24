package Vision.Interface;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InitPanel extends JPanel{    
    private final int screenWidth = 240;
    private final int screenHeight = 120;
    
    public InitPanel() {
        JButton startGame = new JButton();
        JButton startMap = new JButton();
        
        startGame.setText("Start game");
        startMap.setText("Start map maker");
        
        this.add(startGame);
        this.add(startMap);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
