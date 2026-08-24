package Vision.Interface;

import javax.swing.JFrame;

public class InitFrame {
        public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        InitPanel initPanel = new InitPanel();
        window.add(initPanel);

        window.setSize(initPanel.getScreenWidth(), initPanel.getScreenHeight());

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
