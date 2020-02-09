



package Projekt;

import javax.lang.model.element.Element;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ZuzelApp {


    private JPanel panel;
    private JButton prowadzacy;
    private JButton juniorzy;
    private JButton dodaj;
    private JButton doparowi;
    private JList<Player> list1;
    private JList<Player> listChosen;
    private JButton usuńButton;
    private JButton dalejButton;
    private JButton wszyscyButton;
    private JLabel randomArea;

    private int sum = 0;
    private int sum1 = 0;
    private int sum2 = 0;

    ZuzelApp() {
        FileManager fileManager = new FileManager();
        var zuzel = new Zuzel();

        var playerList = fileManager.importData("plik.txt");
        zuzel.setAllPlayers(playerList);
        DefaultListModel<Player> defaultModelToChoose = new DefaultListModel<Player>();
        var defaultModelChosen = new DefaultListModel<Player>();

        defaultModelToChoose.addAll(zuzel.getAllPlayers());

        list1.setModel(defaultModelToChoose);
        listChosen.setModel(defaultModelChosen);

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Player selectedPlayer = list1.getSelectedValue();
                defaultModelToChoose.removeElement(selectedPlayer);
                defaultModelChosen.addElement(selectedPlayer);


            }
        });
        usuńButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Player selectedPlayer = listChosen.getSelectedValue(); // wyciagamy zaznaczonego gracza z listy 1
                defaultModelChosen.removeElement(selectedPlayer);
                defaultModelToChoose.addElement(selectedPlayer);

            }
        });
        prowadzacy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                defaultModelToChoose.clear();
                for (Player e : zuzel.getAllPlayers()) {
                    if (e.getStatus().equals("P")) {
                        defaultModelToChoose.addElement(e);
                    }
                }
            }

        });

        dalejButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                FileManager fileManager = new FileManager();
                MyTeam myTeam = new MyTeam();
                List<Player> players;
                players = Arrays.stream(defaultModelChosen.toArray()).map(c -> (Player) c).collect(Collectors.toList());
                zuzel.getselectedPlayers().addAll(players);
                fileManager.saveData(players);

                zuzel.sortPlayers("D", sum);
                zuzel.sortPlayers("J", sum1);
                zuzel.sortPlayers("P", sum2);

                if (defaultModelChosen.size() > 6 || zuzel.getSortResult() > 2) {
                    randomArea.setText("wybrałes złą ilość zawodników");
                    zuzel.getselectedPlayers().clear();
                } else
                    myTeam.createAndShowGUI();
            }

        });
        doparowi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                defaultModelToChoose.clear();
                for (Player e : zuzel.getAllPlayers()) {
                    if (e.getStatus().equals("D")) {
                        defaultModelToChoose.addElement(e);
                    }

                }
            }
        });
        juniorzy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                defaultModelToChoose.clear();
                for (Player e : zuzel.getAllPlayers()) {
                    if (e.getStatus().equals("J")) {
                        defaultModelToChoose.addElement(e);
                    }

                }
            }
        });
        wszyscyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                defaultModelToChoose.clear();
                for (Player e : zuzel.getAllPlayers()) {
                    defaultModelToChoose.addElement(e);
                }
            }
        });

    }

     public void createAndShowGUI() {
        JFrame frame = new JFrame("ZuzelApp");
        frame.setContentPane(new ZuzelApp().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        GameMenu gameMenu = new GameMenu();
        gameMenu.createAndShowGUI();

    }
}
