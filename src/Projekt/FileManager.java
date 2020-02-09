package Projekt;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class FileManager {

    public List<Player> importData(String filename) {
        var playerList = new ArrayList<Player>();

        try (Scanner fileReader = new Scanner(new File(filename))) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                Player player;
                player = stringToObject(line);
                playerList.add(player);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    void saveData(List<Player> listOfPlayers) {
        var strBuilder = new StringBuilder();
        var fmt = new Formatter(strBuilder);

        try (var fileReader = new BufferedWriter(new FileWriter("selectedPlayers.txt"))) {
            for (Player e : listOfPlayers) {
                fmt.format("%s;%s;%d;%s\n", e.getFirstName(), e.getLastName(), e.getPoints(), e.getStatus());
            }
            fileReader.write(strBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player stringToObject(String line) {
        String[] split = line.split(";");
        return createPlayer(split);
    }

    private Player createPlayer(String[] tab) {
        String firstName = tab[0];
        String lastName = tab[1];
        int points = Integer.parseInt(tab[2]);
        String status = tab[3];
        Player player = new Player(firstName, lastName, points, status);
        return player;
    }
}


/* Funkcje createPlayer, stringToObject, importData tworzyłem korzystając z innego kodu.
link do kodu: https://github.com/javastartpl/java-akpk1/blob/master/18_2/src/pl/javastart/library/io/file/CsvFileManager.java
Autor: Sławek Ludwiczak
 */
