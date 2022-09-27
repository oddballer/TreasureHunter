import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

    TreasureHunter loadedGame = null;

    public static void main(String[] args) {

        App app = new App();
        app.startup();
        app.run();

    }

    public void run(){

        Scanner userInput = new Scanner(System.in);
        String menuSelection;

        while(true){

            System.out.println(loadedGame.getPlayerName() + ", what would you like to do?");
            System.out.println("(T) - TREASURE HUNT - (C) - CHECK GOLD - (X) - EXIT");
            menuSelection = userInput.nextLine();

            if (menuSelection.equalsIgnoreCase("T")){

                loadedGame.setGoldPieces(loadedGame.getGoldPieces() + treasureMap());
            }

            if (menuSelection.equalsIgnoreCase("C")){

                System.out.println(loadedGame.getPlayerName() + " has amassed " + loadedGame.getGoldPieces() + " pieces of gold!");
            }

            if (menuSelection.equalsIgnoreCase("X")){

                loadedGame.save();
                System.exit(0);
            }

        }

    };

    public void startup(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Welcome to Treasure Hunter!");
        System.out.println("\n" + "What's your name, Treasure Hunter?");
        Scanner userInput = new Scanner(System.in);
        String givenPlayerName = userInput.nextLine();

        File saveFile = new File("save/saveFile");

        if (saveFile.exists()){

            try(Scanner saveScanner = new Scanner(saveFile)){

                while(saveScanner.hasNextLine()){

                    String[] saveData = saveScanner.nextLine().split("\\|");

                    if(saveData[0].equals(givenPlayerName)){

                        loadedGame = new TreasureHunter(saveData[0], Integer.parseInt(saveData[1]), Integer.parseInt(saveData[2]));
                        break;
                    }

                }

            }
            catch(FileNotFoundException e){

                System.out.println(e.getMessage());
            }

        }

        if (loadedGame == null){

            loadedGame  = new TreasureHunter(givenPlayerName);
        }

    };

    public int treasureMap(){

        double mapRoll = Math.random();

        if (mapRoll == 0){

            System.out.println(loadedGame.getPlayerName() + " has discovered The Golden City of El Dorado!!");
            System.out.println("\n" + "One million gold pieces!");
            return 1000000;
        }

        else if (mapRoll > 0.9){

            System.out.println(loadedGame.getPlayerName() + " has found the forgotten fortune of a legendary pirate of old!");
            System.out.println("\n" + "One hundred thousand gold pieces!");
            return 100000;
        }

        else if (mapRoll > 0.7){

            System.out.println(loadedGame.getPlayerName() + " has discovered a vault of great wealth!");
            System.out.println("Ten thousand gold pieces!");
            return 10000;
        }

        else if (mapRoll > 0.5){

            System.out.println(loadedGame.getPlayerName() + " has discovered buried treasure!");
            System.out.println("One thousand gold pieces!");
            return 1000;
        }

        System.out.println("Alas, nothing to be found...");
        return 0;

    }

}
