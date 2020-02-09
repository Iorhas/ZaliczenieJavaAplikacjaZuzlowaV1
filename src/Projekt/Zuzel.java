package Projekt;

import java.util.ArrayList;
import java.util.List;

public class Zuzel {

    private List<Player> listOfAllPlayers = new ArrayList<>();
    private List<Player> selectedPlayers = new ArrayList<>();

    public int getSortResult() {
        return sortResult;
    }

    public int getMinusPoints() {
        return minusPoints;
    }


    private int minusPoints = 0;
    private int sortResult = 0;

    List<Player> getselectedPlayers() {
        return selectedPlayers;
    }

    void setAllPlayers(List<Player> players) {
        this.listOfAllPlayers = players;
    }

    public List<Player> getAllPlayers() {
        return listOfAllPlayers;
    }

    public int getCurrentPoints() {
        return listOfAllPlayers.stream().mapToInt(p -> p.getPoints()).sum();
    }


    public void sortPlayers(String x, int j) {
        for (Player e : getselectedPlayers())
            if (e.getStatus().equals(x)) {
                j = j + 1;
                sortResult = j;
            }
    }

    public void returnMinusPoints() {
        minusPoints -= 4;
    }


}



