//This problem was asked by Google.
//
// Suppose we represent our file system by a string in the following manner:
//
// The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
//
// dir
//    subdir1
//    subdir2
//        file.ext
//
// The length of the longest absolute path to the file in absolute file system.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem17 {

    //TODO: Write a proper print function to print directory structure? --> Means convertinf DataStructure back into String and printing it.
    public static void main(String args[]){

        Scanner scr = new Scanner(System.in);
        System.out.println("Please enter the abstracted absolute file system: ");
        String fileSystem = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        scr.close();

         FileSystem f = new FileSystem(fileSystem);

        System.out.println("Longest file Path is: "+f.longestFilePath()+"\n");
        System.out.println("All the file paths are as follows: ");

        for(String i : f.filePaths){
            System.out.println(i);
        }

//        System.out.println(f.base.name);
//        System.out.println(f.base.subItems.get(0).name);
//        System.out.println(f.base.subItems.get(0).subItems.get(0).name);
//        System.out.println(f.base.subItems.get(0).subItems.get(1).name);
//        System.out.println(f.base.subItems.get(1).name);
//        System.out.println(f.base.subItems.get(1).subItems.get(0).name);
//        System.out.println(f.base.subItems.get(1).subItems.get(0).subItems.get(0).name);
//        System.out.println();
    }
}

class FileSystem{

    String abstractPath;
    Item base;
    List<String> filePaths = new ArrayList<>();

    public FileSystem(String str){
        this.abstractPath = str;
        constructBase();
    }

    public void constructBase(){
        String[] items = abstractPath.split("\n");
        int size = items.length;

        int previousIndentation =0;
        Item previousItem = new Item(items[0]);
        previousItem.previous = null;

        this.base = previousItem;
        for(int currentIndex =1; currentIndex < size; currentIndex++){

            int currentIndentation = items[currentIndex].lastIndexOf('\t')+1;
            Item currentItem = new Item(items[currentIndex].substring(currentIndentation));

            if(currentIndentation == previousIndentation+1){
                previousItem.subItems.add(currentItem);
                currentItem.previous = previousItem;

                previousItem = currentItem;
                previousIndentation = currentIndentation;

            }
            else if(currentIndentation == previousIndentation){
                //these items will be in the same level as previous item, belong to same directory
                previousItem = previousItem.previous;
                previousItem.subItems.add(currentItem);
                currentItem.previous = previousItem;

                previousItem = currentItem;
                previousIndentation = currentIndentation;
            }
            else if(currentIndentation <= previousIndentation-1){
                //Thes items will be parents of the previous item, or previous item will be located inside this item ?
                for(int i=previousIndentation; i!=currentIndentation; i-- ){
                    previousItem = previousItem.previous;
                    previousIndentation--;
                }
                previousItem = previousItem.previous;
                previousItem.subItems.add(currentItem);
                currentItem.previous = previousItem;

                previousItem = currentItem;
                previousIndentation = currentIndentation;
            }

        }
    }


    private void findAllFilePaths(String pathPrev, Item i){
        if(i.isFile()){
            filePaths.add(pathPrev+"/"+i.name);
        }
        for(Item j : i.subItems){
            findAllFilePaths(pathPrev+"/"+i.name,j);
        }
    }

    public String longestFilePath(){
        findAllFilePaths("",base);
        String longest = "";
        for(String str: filePaths){
            if(str.length()>longest.length())
                longest=str;
        }
        return longest;
    }
    public void printFileSystem(){
        base.print();
    }
}

class Item {
    String name;
    Item previous;
    List<Item> subItems;

    public Item(String str){
        this.name = str;
        this.subItems = new ArrayList<Item>();
    }

    public boolean isFile(){
        if(name.contains("."))return true;
        return false;
    }

    public void print(){
        if(subItems.isEmpty()){
            System.out.println(name);
        }else{
            System.out.print('\t');
            for(Item i: subItems){
                i.print();
            }
        }
    }

}