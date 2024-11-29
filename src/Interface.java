/*
 * @author Andrei Ciceu
 * #251355626
 * CS2211
 * 11/11/2024
 * This class is an Interface class that takes in a file and uses a BinarySearchTree and BSTDictionary to run through user commands
 * to interact with different options
 */

import java.io.*;
import java.util.StringTokenizer;

public class Interface {
    /*
     * Main method used for running the logic of taking in an input and running through user commands
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Interface inputFile");
            return;
        }

        String inputFile = args[0];
        BinarySearchTree dictionary = new BinarySearchTree();

        // Load data from the file into the dictionary
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String label;
            while ((label = br.readLine()) != null) {
                String line = br.readLine();
                if (line == null) {
                    throw new IOException("Input file format is incorrect. Each label must be followed by data.");
                }

                Record rec = parseRecord(label, line);
                try {
                    dictionary.insert(dictionary.getRoot(), rec);
                } catch (DictionaryException e) {
                    System.err.println("A record with the given key (" + label + "," + rec.getKey().getType() + ") is already in the ordered dictionary");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }

        StringReader keyboard = new StringReader();
        while (true) {
            String line = keyboard.read("Enter next command: ").toLowerCase();
            StringTokenizer tokenizer = new StringTokenizer(line);
            if (!tokenizer.hasMoreTokens()) {
                System.out.println("Invalid command.");
                continue;
            }

            String command = tokenizer.nextToken();
            String word;
            int type;
            String content;
            Record record;

            try {
                switch (command) {
                    case "define":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 1)).getRecord();
                            if (record != null) {
                                System.out.println(record.getDataItem());
                            } else {
                                System.out.println("The word " + word + " is not in the ordered dictionary.");
                            }
                        } else {
                            System.out.println("Invalid command format for 'define'. Expected usage: define <word>");
                        }
                        break;
                    case "translate":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 2)).getRecord();
                            if (record != null) {
                                System.out.println(record.getDataItem());
                            } else {
                                System.out.println("There is no definition for the word " + word);
                            }
                        } else {
                            System.out.println("Invalid command format for 'translate'. Expected usage: translate <word>");
                        }
                        break;

                    case "sound":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 3)).getRecord();
                            
                            if (record != null) {
                                try {
                                    SoundPlayer player = new SoundPlayer();
                                    player.play(record.getDataItem());
                                } catch (MultimediaException e) {
                                    System.out.println("Error playing sound file for " + word);
                                }
                            } else {
                                System.out.println("There is no sound file for " + word);
                            }
                        } else {
                            System.out.println("Invalid command format for 'sound'. Expected usage: sound <word>");
                        }
                        break;

                    case "play":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 4)).getRecord();
                            
                            if (record != null) {
                                try {
                                    SoundPlayer player = new SoundPlayer();
                                    player.play(record.getDataItem());
                                } catch (MultimediaException e) {
                                    System.out.println("Error playing music file for " + word);
                                }
                            } else {
                                System.out.println("There is no music file for " + word);
                            }
                        } else {
                            System.out.println("Invalid command format for 'play'. Expected usage: play <word>");
                        }
                        break;
                    
                    case "say":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 5)).getRecord();
                            if (record != null) {
                                try {
                                    SoundPlayer player = new SoundPlayer();
                                    player.play(record.getDataItem());
                                } catch (MultimediaException e) {
                                    System.out.println("Error playing voice file for " + word);
                                }
                            } else {
                                System.out.println("There is no voice file for " + word);
                            }
                        } else {
                            System.out.println("Invalid command format for 'play'. Expected usage: play <word>");
                        }
                        break;

                    case "show":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 6)).getRecord();
                            if (record != null) {
                                try {
                                    // Initialize the PictureViewer and display the image file
                                    PictureViewer viewer = new PictureViewer();
                                    viewer.show(record.getDataItem());
                                    System.out.println("successfully called show with " + word);
                                } catch (MultimediaException e) {
                                    System.out.println("Error showing image file for " + word + ": " + e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Unexpected error displaying image for " + word + ": " + e.getMessage());
                                }
                            } else {
                                System.out.println("There is no image file for " + word);
                            }
                        } else {
                            System.out.println("Invalid command format for 'show'. Expected usage: show <word>");
                        }
                        break;

                    case "animate":
                        if (tokenizer.hasMoreTokens()) {
                          word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 7)).getRecord();
                            if (record != null) {
                                try {
                                    PictureViewer viewer = new PictureViewer();
                                    viewer.show(record.getDataItem());
                                } catch (MultimediaException e) {
                                    System.out.println("Error showing animated image file for " + word);
                                }
                            } else {
                                System.out.println("There is no animated image file for " + word);
                            }      
                        } else {
                            System.out.println("Invalid command format for 'play'. Expected usage: play <word>");
                        }
                        break;

                    case "browse":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            record = dictionary.get(dictionary.getRoot(), new Key(word, 8)).getRecord();
                            if (record != null) {
                                try {
                                    ShowHTML browser = new ShowHTML();
                                    browser.show(record.getDataItem());
                                } catch (Exception e) {
                                    System.out.println("Error showing webpage for " + word);
                                }
                            } else {
                                System.out.println("There is no webpage called " + word);
                            }
                        } else {
                            System.out.println("Invalid command format for 'play'. Expected usage: play <word>");
                        }
                        break;
                        

                    case "delete":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            type = Integer.parseInt(tokenizer.nextToken());
                            try {
                                dictionary.remove(dictionary.getRoot(), new Key(word, type));
                                System.out.println("Record with key (" + word + "," + type + ") removed.");
                            } catch (DictionaryException e) {
                                System.out.println("No record in the ordered dictionary has key (" + word + "," + type + ")");
                            }
                        } else {
                            System.out.println("Invalid command format for 'play'. Expected usage: play <word>");
                        }
                        break;

                    case "add":
                        if (tokenizer.hasMoreTokens()) {
                            word = tokenizer.nextToken();
                            type = Integer.parseInt(tokenizer.nextToken());
                            content = tokenizer.nextToken();
                            Record newRecord = new Record(new Key(word, type), content);
                            try {
                                dictionary.insert(dictionary.getRoot(), newRecord);
                            } catch (DictionaryException e) {
                                System.out.println("A record with the given key (" + word + "," + type + ") is already in the ordered dictionary");
                            }
                        } else {
                            System.out.println("Invalid command format for 'play'. Expected usage: play <word>");
                        }
                        break;

                    case "list":
                        if (tokenizer.hasMoreTokens()) {
                            String prefix = tokenizer.nextToken().toLowerCase();
                            StringBuilder result = new StringBuilder();
                            boolean foundMatch = false;

                            // Start from the smallest node
                            Record currentRecord = dictionary.smallest(dictionary.getRoot()).getRecord();

                            // Traverse the dictionary in sorted order using in-order traversal
                            while (currentRecord != null) {
                                String label = currentRecord.getKey().getLabel().toLowerCase();  // Convert label to lowercase

                                // If the label starts with the prefix, add it to the result
                                if (label.startsWith(prefix)) {
                                    result.append(currentRecord.getKey().getLabel()).append(", ");
                                    foundMatch = true;
                                } else if (foundMatch) {
                                    // Stop once we've passed all matching records (sorted order)
                                    break;
                                }

                                // Move to the successor of the current node
                                currentRecord = dictionary.successor(dictionary.get(dictionary.getRoot(), currentRecord.getKey()),currentRecord.getKey()).getRecord();
                            }

                            // If matches were found, output them
                            if (foundMatch) {
                                // Remove trailing comma and space, then print
                                System.out.println(result.substring(0, result.length() - 2));
                            } else {
                                // If no matches, print the no match message
                                System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix);
                            }
                        } else {
                            System.out.println("Invalid command format for 'list'.");
                        }
                        break;

                    case "first":
                        record = dictionary.smallest(dictionary.getRoot()).getRecord();
                        if (record != null) {
                            System.out.println(record.getKey().getLabel() + "," + record.getKey().getType() + "," + record.getDataItem());
                        } else {
                            System.out.println("The dictionary is empty.");
                        }
                        break;

                    case "last":
                        record = dictionary.largest(dictionary.getRoot()).getRecord();
                        if (record != null) {
                            System.out.println(record.getKey().getLabel() + "," + record.getKey().getType() + "," + record.getDataItem());
                        } else {
                            System.out.println("The dictionary is empty.");
                        }
                        break;

                    case "exit":
                        System.out.println("Exiting program.");
                        return;

                    default:
                        System.out.println("Invalid command.");
                }
            } catch (Exception e) {
                System.out.println("Invalid command format.");
            }
        }
    }

    /*
     * @param String label
     * @param String line
     * @return Record
     * Reads a data line and creates a record with the proper type, label, and data
     */
    private static Record parseRecord(String label, String line) {
        Key k;
        int type;
        String data;

        if (line.startsWith("-")) {
            type = 3;
            data = line.substring(1);
        } else if (line.startsWith("+")) {
            type = 4;
            data = line.substring(1);
        } else if (line.startsWith("*")) {
            type = 5;
            data = line.substring(1);
        } else if (line.startsWith("/")) {
            type = 2;
            data = line.substring(1);
        } else if (line.endsWith(".gif")) {
            type = 6;
            data = line;
        } else if (line.endsWith(".jpg")) {
            type = 7;
            data = line;
        } else if (line.endsWith(".html")) {
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