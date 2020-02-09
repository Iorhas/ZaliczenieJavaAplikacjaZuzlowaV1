package Projekt;

import javafx.stage.Modality;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class GameMenu extends JFrame {
    private JPanel panel1;
    private JButton nowaGraButton;
    private JButton kontynuujButton;
    private JButton zasadyButton;
    private JPanel pp;
    private JPanel panel;
    private JPanel panel2;
    private JButton zasadyGryButton;


    public GameMenu() {
        nowaGraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ZuzelApp zuzelApp = new ZuzelApp();
                zuzelApp.createAndShowGUI();
            }
        });
        kontynuujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MyTeam myTeam = new MyTeam();
                myTeam.createAndShowGUI();


            }
        });


        zasadyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Rules rules = new Rules();
                rules.createAndShowGUI();
            }
        });
    }

    public  void createAndShowGUI() throws IOException {

        var frame2 = new JFrame("Aplikacja Żużlowa v1.0");
        frame2.setContentPane(new GameMenu().panel1);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setSize(800, 600);
        frame2.setVisible(true);
    }
}