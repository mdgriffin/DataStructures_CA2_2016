package invertedinxdex;

/**
   This class implements a Map based binary search tree whose
   nodes hold keys (of type K) that implement the Comparable
   interface and associated values (of type V).
*/

public class BinarySearchTreeMap<K extends Comparable<K>, V> {
   private Node root;

   private class Node
   {
      public K key;
      public V val;
      public Node left;
      public Node right;
      public Node parent; 
   }
   
   public BinarySearchTreeMap(){
     root = null;
   }
   
   /* Insert key-value pair into BinarySearchTreeMap
      If key already exists, ignore it
   */
   // insert renamed to put, to match Map interface
   public void put(K k, V v) {
      Node newNode = new Node();
      newNode.key = k;
      newNode.val = v;
      newNode.left = null;
      newNode.right = null;
      if (root == null) {
        root = newNode;
        // CA
        newNode.parent = null;
      } else {
        insertSub(root, newNode);  
      }
        
   }
       
   private void insertSub(Node node, Node newNode){
      if (newNode.key.compareTo(node.key) < 0)
      {  if (node.left == null) {
          node.left = newNode;
            // CA
            newNode.parent = node.left;
        } else {
          insertSub(node.left,newNode);
        }
      } else if (newNode.key.compareTo(node.key) > 0)
      {  if (node.right == null) {
          node.right = newNode;
             // CA
            newNode.parent = node.right;
        } else {
          insertSub(node.right, newNode);
        }
    }
    //else //key already in tree => do nothing
   }
 
   public void print(){
       if (root != null)
         printSub(root);
   }
   
   private void printSub(Node node){
       //implements inorder traversal
      if (node != null){
         printSub(node.left);
         System.out.println(node.key + " " + node.val);         
         printSub(node.right);
      }
   } 
   
    
   /*
    *  Search BinarySearchTreeMap for given key, and return associated value if found,
    *  return null if not found
   */
   public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if  (cmp < 0)
               node = node.left;
            else if (cmp > 0)
                node = node.right;
            else return node.val;
        }
        return null;
    }

    // does there exist a key-value pair with given key?
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    
       
    // sample client code
    public static void main(String[] args) {
        BinarySearchTreeMap<String, String> bstMap = new BinarySearchTreeMap();

        // insert some key-value pairs e.g. name, mobile number 
        //Not in order of name - why?
        bstMap.put("Jack",  "0871231231");   
        bstMap.put("Jill",  "0877654321"); 
        bstMap.put("Bob",   "0871234567"); 
        bstMap.put("Rob",   "0851212121");  
        
        bstMap.put("Jill",  "0867654321");  //what happens here?

        bstMap.print();
        
        //System.out.println("Size of tree: "+ bstMap.size());
 
        // search for mobile number given name
        String number = bstMap.get("Jill");
        System.out.println(number); //what output do you expect here?

        number = bstMap.get("Bill");
        System.out.println(number); //what output do you expect here?
    
        if (bstMap.containsKey("Sam"))
            System.out.println("Sam is on the tree");
        else 
            System.out.println("Sam is not on the tree");
 
    }

}
