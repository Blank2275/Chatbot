import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
    public static String[] readLines(String filepath){
        try{
            File file = new File(filepath);
            Scanner reader = new Scanner(file);

            ArrayList<String> lines = new ArrayList<String>();

            while (reader.hasNextLine()) {
                lines.add(reader.nextLine());
            }

            String[] arrLines = lines.toArray(new String[0]);
            return arrLines;
        } catch (IOException e){
            System.out.println("An error occured loading the file");
            e.printStackTrace();
            return null;
        }

    }
}
