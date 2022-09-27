import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TreasureHunter {

    String playerName;
    int goldPieces;
    int mapsCompleted;

    public TreasureHunter(String playerName, int goldPieces, int mapsCompleted) {
        this.playerName = playerName;
        this.goldPieces = goldPieces;
        this.mapsCompleted = mapsCompleted;
    }

    public TreasureHunter(String playerName) {
        this.playerName = playerName;
    }

    public void save(){

        File saveDirectory = new File("save");

        if (saveDirectory.exists() && saveDirectory.isDirectory()){

            File saveFile = new File("save/saveFile");
            File tempSave = new File("save/tempSave");
            boolean playerFound = false;

            try (PrintWriter saveWriter = new PrintWriter(tempSave);
            Scanner saveScanner = new Scanner(saveFile)){

                while(saveScanner.hasNextLine()){

                    String[] saveLine = saveScanner.nextLine().split("\\|");

                    if (saveLine[0].equals(getPlayerName())){

                        saveWriter.println(getPlayerName() + "|" + getGoldPieces() + "|" + getMapsCompleted() + "|");
                        playerFound = true;
                        continue;
                    }

                    saveWriter.println(saveLine[0] + "|" + saveLine[1] + "|" + saveLine[2] + "|");

                    if(!playerFound){


                        saveWriter.println(getPlayerName() + "|" + getGoldPieces() + "|" + getMapsCompleted() + "|");
                    }

                }



            }
            catch(FileNotFoundException e){

                System.out.println(e.getMessage());
            }

            try(PrintWriter saveWriter = new PrintWriter(saveFile);
            Scanner saveScanner = new Scanner(tempSave)){

                while(saveScanner.hasNextLine()){
                    String saveLine = saveScanner.nextLine();
                    saveWriter.println(saveLine);
                }

            }
            catch(FileNotFoundException e){

                System.out.println(e.getMessage());
            }

            tempSave.delete();

        }

        if (!saveDirectory.exists()){

            saveDirectory.mkdir();

            File saveFile = new File("save/saveFile");

            try(PrintWriter saveWriter = new PrintWriter(saveFile)){

                saveWriter.print(getPlayerName() + "|" + getGoldPieces() + "|" + getMapsCompleted() + "|");
            }
            catch(FileNotFoundException e){
                System.out.println(e.getMessage());
            }

        }

        System.out.println("Game saved!");
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getGoldPieces() {
        return goldPieces;
    }

    public void setGoldPieces(int goldPieces) {
        this.goldPieces = goldPieces;
    }

    public int getMapsCompleted() {
        return mapsCompleted;
    }

    public void setMapsCompleted(int mapsCompleted) {
        this.mapsCompleted = mapsCompleted;
    }

    @Override
    public String toString() {
        return "TreasureHunter{" +
                "playerName='" + playerName + '\'' +
                ", goldPieces=" + goldPieces +
                ", mapsCompleted=" + mapsCompleted +
                '}';
    }
}
