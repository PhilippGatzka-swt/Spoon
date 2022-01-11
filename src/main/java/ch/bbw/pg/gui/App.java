package ch.bbw.pg.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 */
public class App extends JFrame {

    public App() throws HeadlessException {
        super("Spoon");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new SpoonGUI());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}
