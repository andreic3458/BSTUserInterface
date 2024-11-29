import java.io.*;
import java.util.*;

public class Interface {
    public static void main(String[] args) throws IOException{
        if (args.length != 1) {
            System.err.println("Usage: java Interface inputFile");
            return;
        }

        String inputFile = args[0];
        BinarySearchTree dictionary = new BinarySearchTree();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            String label;
            while ((label = br.readLine()) != null){
                String line = br.readLine();
                if (line == null){
                    throw new IOException("Input file format is incorrect. Each label must be followed by data");
                }
    
                Record rec = parseRecord(label, line);
                try{
                    dictionary.insert(dictionary.getRoot(), rec);
                } catch (DictionaryException e){
                    System.err.println("Duplicate entry with key: " + label);
                }
            }
            
        } catch (IOException e){
            System.err.println("Error reading input file: " + e.getMessage());
        }

        
    }

    private static Record parseRecord(String label, String line){
        Key k;
        int type;
        String data;

        if (line.startsWith("-")){
            type = 3;
            data = line.substring(1);
        } else if (line.startsWith("+")){
            type = 4;
            data = line.substring(1);
        } else if (line.startsWith("*")){
            type = 5;
            data = line.substring(1);
        } else if (line.startsWith("/")){
            type = 2;
            data = line.substring(1);
        } else if (line.endsWith(".gif")){
            type = 6;
            data = line;
        } else if (line.endsWith(".jpg")){
            type = 7;
            data = line;
        } else if (line.endsWith(".html")){
            type = 8;
            data = line;
        } else {
            type = 1;
            data = line;
        }

        k = new Key(label, type);
        return new Record(k, data);
    }
}
