package task1;

import java.util.*;
import java.io.*;

public class Task1 {

    public static void main(String[] args) throws IOException {
        //GETTING FILE
        String fileLocation = System.getProperty("user.dir");
        String dataPath = fileLocation + File.separator + "superheroes.txt";
        String resultsPath = fileLocation + File.separator + "results.txt";
        BufferedReader br = new BufferedReader(new FileReader(dataPath));

        //ARRAY LIST OF CHARACTER INFORMATION NEEDED
        ArrayList<Characters> characters = new ArrayList<Characters>();
        //ARRAY LIST OF WORDS IN DESCRIPTION - USED TO CHECK FOR UNIQUE WORDS
        ArrayList<String> evenWordsArray = new ArrayList<String>();
        ArrayList<String> oddWordsArray = new ArrayList<String>();

        //words
        int evenWords = 0;
        int oddWords = 0;

        //READS LINES
        try {
            String temp = null;
            while ((temp = br.readLine()) != null) {
                //LINE SPLIT AT : DELIMITER AT ADDED INTO ARRAY
                String[] linesTest = temp.split(":");

                //RELEVANT INFORMATION FROM LINE PICKED OUT FROM ARRAY AND ASSIGNED TO STRING
                String chrPname = linesTest[0];
                String chrHeight = linesTest[1];
                String chrName = linesTest[7];
                String chrStrength = linesTest[3];

                //HEIGHT AND STRENGTH VALUES CONVERTED FROM STRING TO INTS
                int intHeight = Integer.valueOf(chrHeight);
                int intStrength = Integer.valueOf(chrStrength);

                //NEW CHARACTER OBJECT MADE EACH TIME AND ADDED IN ARRAY LIST
                characters.add(new Characters(chrPname, chrName, intHeight, intStrength));
                
                //GETTING DESCRIPTIONS OF SUPERHEROES
                String description = linesTest[8];
                //REMOVING ANY UNWANTED CHARACTERS AND MAKING TEXT ALL LOWER CASE
                String cleanDescription = description.replace(".", "").replace(",", "").replace(";", "").replace("-", " ").replace("'", "").replace("!", "");
                cleanDescription = cleanDescription.toLowerCase();
                //SPLITTING THE DESCRIPTION SENTENCE INTO SEPARATE WORDS AND ADDING IT INTO AN ARRAY
                String[] description2 = cleanDescription.split(" ");
                //LOOPING THROUGH ALL THE WORDS IN THE DESCRIPTION
                for (int i = 0; i < description2.length; i++) {
                    //GETS A WORD AND THE LENGTH OF THE WORD
                    int wordLength = description2[i].length();
                    String currentWord = description2[i];
                    //IF THE WORD IS EVEN AND IF IT IS ALREADY NOT IN EVENWORDS ARRAY IT IS ADDED AND EVENWORDS COUNTER IT INCREASED
                    //SAME FOR ODD WORDS
                    if (wordLength % 2 == 0) {
                        if (!evenWordsArray.contains(currentWord)) {
                            evenWords++;
                            evenWordsArray.add(currentWord);
                        }
                    } else {
                        if (!oddWordsArray.contains(currentWord)) {
                            oddWords++;
                            oddWordsArray.add(currentWord);
                        }
                    }
                }
            }
        } catch (FileNotFoundException x) {
            //FILE NOT FOUND ERROR
            System.out.println("File not found");
        }
        
        //CREATING FILE WRITER AND PRINT WRITER TO WRITE RESULTS INTO FILE
        FileWriter fw = new FileWriter(resultsPath);
        PrintWriter pw = new PrintWriter(fw);
        
        //RESULTS SORTED AND PRINTED - TALLEST
        String fiveStrongest = "--- Five tallest heroes ---";
        System.out.println(fiveStrongest);
        pw.println(fiveStrongest);
        Collections.sort(characters, (Characters s1, Characters s2) -> Integer.valueOf(s2.height).compareTo(s1.height));
        for (int x = 0; x < 5; x++) {
            System.out.println(characters.get(x).pseudoName + " (" + characters.get(x).realName + "), Height = " + characters.get(x).height + " centimetres");
            pw.println(characters.get(x).pseudoName + " (" + characters.get(x).realName + "), Height = " + characters.get(x).height + " centimetres");
        }

        System.out.println("");
        pw.println("");

        //RESULTS SORTED AND PRINTED - WEAKEST
        String fiveWeakest = "--- Five weakest heroes ---";
        System.out.println(fiveWeakest);
        pw.println(fiveWeakest);
        Collections.sort(characters, (Characters s1, Characters s2) -> Integer.valueOf(s1.strength).compareTo(s2.strength));
        for (int x = 0; x < 5; x++) {
            System.out.println(characters.get(x).pseudoName + " (" + characters.get(x).realName + "), Strength rating = " + characters.get(x).strength);
            pw.println(characters.get(x).pseudoName + " (" + characters.get(x).realName + "), Strength rating = " + characters.get(x).strength);
        }

        System.out.println("");
        System.out.println("----------------------");
        System.out.println("");
        
        pw.println("");
        pw.println("----------------------");
        pw.println("");

        System.out.println("Total number of unique even words in hero descriptions = " + evenWords);
        System.out.println("Total number of unique odd words in hero descriptions = " + oddWords);
        
        pw.println("Total number of unique even words in hero descriptions = " + evenWords);
        pw.println("Total number of unique odd words in hero descriptions = " + oddWords);
        
        pw.close();
        fw.close();
        br.close();
    }
}

//CHARACTER OBJECT WHICH TAKES NECESSARY INFORMATION
class Characters {

    public String pseudoName;
    public String realName;
    public int height;
    public int strength;

    public Characters(String pseudoName1, String realName1, int Height1, int Strength1) {
        this.pseudoName = pseudoName1;
        this.realName = realName1;
        this.height = Height1;
        this.strength = Strength1;
    }
}
