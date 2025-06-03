package pokerResult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {
    private String fileName;
    private double sumaWonHands;
    private double sumaAnte;
    private double sumaHeroRaise;
    private double sumaHeroCalls;
    private double sumaHeroBets;

    public FileManager() throws IOException {
        fileName = "C:\\Users\\ana\\IdeaProjects\\NewZoo\\src\\pokerResult\\GG20240119-1005 - SDhite40 - 0.02 - 5max.txt";
        sumaWonHands = wonHands();
        sumaAnte = heroAnte();
        sumaHeroRaise = heroRaise();
        sumaHeroCalls = heroCalls();
        sumaHeroBets = heroBets();
    }


    public double handsResult() {
        double handsResult = sumaWonHands - sumaAnte - sumaHeroRaise - sumaHeroCalls - sumaHeroBets;
        System.out.println(handsResult);
        return handsResult;
    }

    public void handStarted() throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        double moneyOnTheStreet = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("poker.Poker Hand") || line.contains("*** FLOP ***") ||
                    line.contains("*** TURN ***") || line.contains("*** RIVER ***")) {
                moneyOnTheStreet = 0;
            }
            if (line.contains("Hero: raises")) {
                int dollarIndex = line.lastIndexOf("$");
                if (dollarIndex != -1) {
                    String amountRaises = line.substring(dollarIndex + 1);
                    int spaceIndex = amountRaises.indexOf(" ");
                    if (spaceIndex != -1) {
                        amountRaises = amountRaises.substring(0, spaceIndex);
                    }
                    double raises = Double.parseDouble(amountRaises);
                    //           sumaHeroRaise = sumaHeroRaise + raises;
                }
            }
        }
    }

    public double heroAnte() throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        double sumaAnte = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("Hero")) {
                if (line.contains("posts the ante")) {
                    double ante = Double.parseDouble(line.substring(line.lastIndexOf(" ") + 2));
                    sumaAnte = sumaAnte + ante;
                }
            }
        }
        return sumaAnte;
    }

    public double heroBets() throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        double sumaHeroBets = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("Hero: bets")) {
                int dollarIndex = line.indexOf("$");
                if (dollarIndex != -1) {
                    int endIndex = line.indexOf(" and is all-in", dollarIndex);
                    if (endIndex == -1) {
                        endIndex = line.length();
                    }
                    String amountBets = line.substring(dollarIndex + 1, endIndex);
                    double bets = Double.parseDouble(amountBets);
                    sumaHeroBets = sumaHeroBets + bets;
                }
            }
        }
        // System.out.println(sumaHeroBets);
        return sumaHeroBets;
    }

    public double heroRaise() throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        double sumaHeroRaise = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("Hero: raises")) {
                int dollarIndex = line.indexOf("$");
                if (dollarIndex != -1) {
                    String amountRaises = line.substring(dollarIndex + 1);
                    int spaceIndex = amountRaises.indexOf(" ");
                    if (spaceIndex != -1) {
                        amountRaises = amountRaises.substring(0, spaceIndex);
                    }
                    double raises = Double.parseDouble(amountRaises);
                    sumaHeroRaise = sumaHeroRaise + raises;
                }
            }
        }
        // System.out.println(sumaHeroRaise);
        return sumaHeroRaise;
    }

    public double heroCalls() throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        double sumaHeroCalls = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("Hero: calls")) {
                int dollarInxdex = line.indexOf("$");
                if (dollarInxdex != -1) {
                    String amountCalls = line.substring(dollarInxdex + 1);
                    int spaceIndex = amountCalls.indexOf(" ");
                    if (spaceIndex != -1) {
                        amountCalls = amountCalls.substring(0, spaceIndex);
                    }
                    double calls = Double.parseDouble(amountCalls);
                    sumaHeroCalls = sumaHeroCalls + calls;
                }
            }
        }
        //System.out.println(sumaHeroCalls);
        return sumaHeroCalls;
    }

    public double wonHands() throws IOException {
        File file = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        double sumaWonHands = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains("Hero collected")) {
                int startIndex = line.indexOf("$") + 1;
                int endtIndex = line.indexOf(" from pot");
                if (endtIndex > -1) {
                    String wonString = line.substring(startIndex, endtIndex);
                    double won = Double.parseDouble(wonString);
                    sumaWonHands = sumaWonHands + won;
                }
            }
        }
        //System.out.println(sumaWonHands);
        return sumaWonHands;
    }
}
