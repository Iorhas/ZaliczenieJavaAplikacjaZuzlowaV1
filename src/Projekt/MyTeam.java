package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTeam {

    private JPanel panelMyTeam;
    private JList<Player> myTeamList;
    private JButton transferyButton;
    private JLabel pointsLabel;
    private JButton odświeżButton;


    public MyTeam() {

        TransferWindow transferWindow = new TransferWindow();
        FileManager fileManager = new FileManager();

        var zuzel = new Zuzel();
        var playerList = fileManager.importData("selectedPlayers.txt");
        zuzel.setAllPlayers(playerList);
        DefaultListModel<Player> myTeamDefaultList = new DefaultListModel<Player>();
        myTeamDefaultList.addAll(zuzel.getAllPlayers());
        myTeamList.setModel(myTeamDefaultList);

        odświeżButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pointsLabel.setText(zuzel.getCurrentPoints() + " " + "punktów");

            }
        });
        transferyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                transferWindow.createAndShowGUI();

            }
        });
    }

    public  void createAndShowGUI() {
        JFrame frame = new JFrame("Gra");
        frame.setContentPane(new MyTeam().panelMyTeam);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }


}