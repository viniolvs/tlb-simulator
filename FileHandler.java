import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {
    private String filename;
    private Scanner input;

    public FileHandler(String filename){
        this.filename = filename;
    }

    public void openFile() {
        try {
            input = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            System.exit(1);
        }
    }

    public Pair readPage() {
        if (input.hasNextLine()) {
            String p = new String(),  d = new String();
            String aux = new String();
            aux = input.nextLine();
            p = aux.substring(0, 5); //page number = first 5 hex digits
            d = aux.substring(5, 7); //page offset = last 3 hex digits
            Page page = new Page(p, d);
            return new Pair(true, page);
        }
        else{
            closeFile();
            return new Pair(false, new Page());
        }    
    }

    public void closeFile() {
        if (input != null)
            input.close();
    }
}
