import java.util.*;


public class PrefixTree {

    private static final int ALPHABET_SIZE = 26;
        // why is this a static class? and inner class?
    static class TrieNode{

        //Each node is a hash table of what eleemnts are present at the level, index i not null means node has i-th element in alphabet as one of the child nodes
        //Hash Map makes more sense in the case of storing children Values. More or less chilren are key value stores
        List<TrieNode> children;
        boolean isEndOfWord;

        public TrieNode(){
            //In ASCII 0 means NULL
            isEndOfWord = false;
            children = new ArrayList<TrieNode>(ALPHABET_SIZE);
//            children = (ArrayList<TrieNode>) Arrays.asList(new TrieNode[ALPHABET_SIZE]);
            for(int i =0; i< ALPHABET_SIZE; i++ )
                children.add(null);
        }

        public boolean isLeafNode(){
            for(TrieNode c: children){
                if(c!=null)return false;
            }
            return true;
        }
    }

    TrieNode root = new TrieNode();

    //Method to insert a string into a Trie.
    //This method can be called on any Prefix tree and the string will be inserted into the tree
    public void insert(String str){
        TrieNode n = root;
        int length = str.length();

        for( int index = 0; index<length; index++){

            if(n.children.get(str.charAt(index)-'a')==null){
                //TODO: Always use set to update an ArrayList dont use add
                n.children.set(str.charAt(index)-'a', new TrieNode());
            }
            n = n.children.get(str.charAt(index)-'a');
        }
        n.isEndOfWord = true;
    }

    //Search if present or not.
    public boolean search(String str){
        TrieNode n = root;
        int length = str.length();

        for(int index=0; index< length; index++){
            if(n.children.get(str.charAt(index)-'a') != null){
                 n = n.children.get(str.charAt(index)-'a');
            }else{
                return false;
            }
        }

        return n!=null && n.isEndOfWord;
    }

    //This is a autocomplete function gives a list of words in dictionary that start with the letters of str
    public List<String> getRecommendations(String str){

        TrieNode n = root;
        int length = str.length();
        List<String> output = new ArrayList<>();
        for(int index=0; index<length; index++){
            if(n.children.get(str.charAt(index)-'a')!=null){
                n= n.children.get(str.charAt(index)-'a');
            }else{
                List<String> emptyList = new ArrayList<>();
                emptyList.add("");
            }
        }
        if(n.isEndOfWord && n.isLeafNode()){
           output.add(str);
        }

        //Actual recursive call over here
        if(!n.isLeafNode()){
              helper(str, n, output);
        }
        return output;
    }

    public void helper(String prefix, TrieNode n, List<String> output){

        if(n.isLeafNode()){
           return;
        }
        for(int i =0; i< ALPHABET_SIZE; i++){
            if(n.children.get(i)!=null){
                char c = (char) ('a'+i);
                if(n.children.get(i).isEndOfWord){
                    output.add(prefix+c);
                }
                helper(prefix+c, n.children.get(i),output);
            }
        }
    }

    public static void main(String args[]){
        PrefixTree p = new PrefixTree();
        p.insert("dog");
        p.insert("deer");
        p.insert("dentist");
        p.insert("deal");
        System.out.println("The word deer exists in trie? : "+ p.search("deotist"));
        System.out.println("The recommendations of words starting with de: "+p.getRecommendations("de"));
    }

}
