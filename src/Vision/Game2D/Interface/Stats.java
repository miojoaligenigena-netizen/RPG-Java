package Vision.Game2D.Interface;

import Control.Entity.Player;
import Control.Entity.Stats.StatType;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stats extends JPanel {
    
    private final int screenWidth = 340;
    private final int screenHeight = 240;
    
    private Player player;
    
    private JLabel strength = new JLabel();
    private JLabel sorcery = new JLabel();
    private JLabel resistance = new JLabel();
    private JLabel agility = new JLabel();
    
    JFrame window = new JFrame();
    
    public Stats(Player player) {
        this.player = player;

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Status");

        window.add(this);

        window.setSize(screenWidth, screenHeight);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    private void init() {
        this.add(strength);
        this.add(sorcery);
        this.add(resistance);
        this.add(agility);
        
        refresh();
    }
    
    private void refresh() {
        this.strength.setText("Strenght: " + player.getStat(StatType.STRENGTH));
        this.sorcery.setText("Sorcery: " + player.getStat(StatType.SORCERY));
        this.resistance.setText("Resistance: " + player.getStat(StatType.RESISTANCE));
        this.agility.setText("Agility: " + player.getStat(StatType.AGILITY));
    }
    
    public void open() {
        refresh();
        window.setVisible(true);
    }
}
