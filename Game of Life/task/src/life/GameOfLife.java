package life;

import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JFrame {
    public GameOfLife() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawRect(5,55,50,50);
    }

    private void initComponents() {
        JLabel generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        generationLabel.setBounds(5,5,290,5);
        add(generationLabel);

        JLabel aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        generationLabel.setBounds(5,15,290,5);
        add(aliveLabel);
    }
}
