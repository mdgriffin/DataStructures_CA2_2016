package invertedinxdex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/*
 * Class to build an index.
*/
public class InvertedIndex {

    // Properties
    /** TreeMap for storing the index. */
    private BinarySearchTreeMap<String, List<String>> treeMapIndex;
        
    private Set<String> allWords;  //set of all unique words in the index 
                                   //used in profiling the search method
    
    // Methods
    public InvertedIndex() {
        treeMapIndex = new BinarySearchTreeMap<>();
        
        allWords = new HashSet();
    }

    public void buildIndex(ArrayList<String> listOfFileNames) {
       
        for (String fileName : listOfFileNames) {
            try {
                File inputFile = new File(fileName);
                Scanner in = new Scanner(inputFile);

                String line;
                String words[];

                while (in.hasNextLine()) {
                    //read a line
                    line = in.nextLine();
                    line = line.replaceAll("https?://\\S+\\s?", "");  // removes URLs

                    // parse line into words
                    // the character pattern that separates words is any sequence of
                    // characters other than letters, numbers and apostrophe
                    // This strips off punctuation marks
                    words = line.split("[^A-Za-z0-9']+");

                    //add words and filename to index
                    for (String word : words) {
                        if (!word.equals("")) {
                            if (!treeMapIndex.containsKey(word)) {
                                List<String> list = new LinkedList();
                                list.add(fileName);
                                treeMapIndex.put(word, list);
                                allWords.add(word);
                            }
                            else {
                                // word already in index - check if fileName there
                                List<String> list = treeMapIndex.get(word);
                                if (!list.contains(fileName)) {
                                    list.add(fileName);
                                }
                            }
                        }
                    }
                }
                //System.out.println(treeMapIndex.size());
            } catch (IOException exc) {
                System.out.println("File does not exist");
                exc.printStackTrace();
                System.exit(1);
            }
        }
     }

    /** For BinarySearchTreeMap implementation : Displays the index, one word per line. */
    public void print (){
        treeMapIndex.print();
    }
    
//    public int height(){
//        return treeMapIndex.height();
//    }
            
    public List search(String s){
        
        List<String> value = treeMapIndex.get(s);
        return value;
    }
    

    //method to profile search: call search method with all words in inverted index 
    public void searchAllWords(){
        
        for (String s : allWords){
            search(s);  
        }
    }
}