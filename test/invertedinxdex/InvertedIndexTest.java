/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invertedinxdex;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdgri
 */
public class InvertedIndexTest {
    
    /**
     * Test of search method, of class InvertedIndex.
     */
    @Test
    public void testSearch() {
        System.out.println("=== Test Search ===");
        
        int maxFiles = 3;
        
        InvertedIndex index = new InvertedIndex();
        
        ArrayList<String> list = new ArrayList();
        
        //simple files for testing 
        list.add("File1.txt");
        list.add("File2.txt");
        list.add("File3.txt");
        
        index.buildIndex(list, maxFiles);
        
        List results = index.search("Trees");
        
        int actualNumResults= results.size();
        
        assertEquals(maxFiles, actualNumResults);
    }
    
}
