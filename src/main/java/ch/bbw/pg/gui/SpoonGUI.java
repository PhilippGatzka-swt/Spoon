package ch.bbw.pg.gui;

import ch.bbw.pg.api.ConnectionManager;
import ch.bbw.pg.api.Recipe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

/**
 * @author P. Gatzka
 * @version 04.01.2022
 * Project: Spoon
 */
public class SpoonGUI extends JPanel {
    JImagePanel pnlImage;
    JButton btnGet, btnLoad;
    JLabel lblTitle;
    Recipe recipe;

    JPanel content;
    JScrollPane scrollPane;

    public SpoonGUI() {
        setPreferredSize(new Dimension(556, 600));
        setLayout(new BorderLayout());
        prepareUI();
    }

    private void getRandomRecipe() {
        recipe = ConnectionManager.getRandomRecipe();
        update();
    }

    private void prepareUI() {
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(content, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        btnGet = new JButton("Get Random Recipe");
        btnGet.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getRandomRecipe();
            }
        });
        content.add(btnGet);
        lblTitle = new JLabel();
        content.add(lblTitle);
        pnlImage = new JImagePanel();
        content.add(pnlImage);
        btnLoad = new JButton("Show more Information");
        btnLoad.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionManager.getRecipeInstructions(recipe);
            }
        });
        content.add(btnLoad);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void update() {
        try {
            pnlImage.setImage(ImageIO.read(new URL(recipe.getImage())));
            lblTitle.setText(recipe.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateUI();
    }

}
