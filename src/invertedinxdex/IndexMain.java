package invertedinxdex;

import java.util.ArrayList;

public class IndexMain {

    public static void main(String[] args) {

        InvertedIndex index = new InvertedIndex();
        ArrayList<String> list = new ArrayList();
        
        //simple files for testing 
        list.add("File1.txt");
        list.add("File2.txt");
        list.add("File3.txt");
        
        index.buildIndex(list, 10);
        
        index.print();  //output index
       
                
    }
}
