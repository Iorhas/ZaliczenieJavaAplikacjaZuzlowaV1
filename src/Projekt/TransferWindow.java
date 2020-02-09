package Projekt;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TransferWindow {
    private JList<Player> playersToBuy;
    private JList<Player> playersInMyClub;
    private JPanel panel1;
    private JButton kupButton;
    private JButton sprzedajButton;
    private JButton dalejButton;
    private JLabel errorText;
    private JLabel showTransferPoints;

    private int sum = 0;
    private int sum1 = 0;
    private int sum2 = 0;


    public TransferWindow() {


        FileManager fileManager = new FileManager();
        var zuzel = new Zuzel();
        var playerList = fileManager.importData("selectedPlayers.txt");
        zuzel.setAllPlayers(playerList);

        DefaultListModel<Player> myTeamDefaultList = new DefaultListModel<Player>();

        myTeamDefaultList.addAll(zuzel.getAllPlayers());
        playersInMyClub.setModel(myTeamDefaultList);

        var allPlayersList = fileManager.importData("plik.txt");

        zuzel.setAllPlayers(allPlayersList);
        DefaultListModel<Player> defaultModelToChoose = new DefaultListModel<Player>();

        defaultModelToChoose.addAll(zuzel.getAllPlayers());
        playersToBuy.setModel(defaultModelToChoose);

        kupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Player selectedPlayer = playersToBuy.getSelectedValue();
                defaultModelToChoose.removeElement(selectedPlayer);
                myTeamDefaultList.addElement(selectedPlayer);
                zuzel.returnMinusPoints();
                showTransferPoints.setText(String.valueOf(zuzel.getMinusPoints()));
                // showTransferPoints.setText(String.valueOf(minusPoints -= 4));
                System.out.println(zuzel.getMinusPoints());


            }
        });
        sprzedajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Player selectedPlayer = playersInMyClub.getSelectedValue();
                myTeamDefaultList.removeElement(selectedPlayer);

            }
        });
        dalejButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                FileManager fileManager = new FileManager();
                List<Player> players;
                players = Arrays.stream(myTeamDefaultList.toArray()).map(c -> (Player) c).collect(Collectors.toList());
                zuzel.getselectedPlayers().addAll(players);
                 var myTeam = new MyTeam();
                fileManager.saveData(players);


                zuzel.sortPlayers("D", sum);
                zuzel.sortPlayers("J", sum1);
                zuzel.sortPlayers("P", sum2);


                if (myTeamDefaultList.size() != 6 || zuzel.getSortResult() > 2) {
                    errorText.setText("wybrałes złą ilość zawodników");
                    showTransferPoints.setText(String.valueOf(zuzel.getMinusPoints()));
                    zuzel.getselectedPlayers().clear();
                } else
                   myTeam.createAndShowGUI();


            }


        });
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Gra");
        frame.setContentPane(new TransferWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
