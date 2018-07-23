/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Joseph
 */
public class ListDocument {
   private File underlyingFile; //the file this document points to
   private String separator = "~"; //this separates sections and cannot be used anywhere else, escape character
   private String title = "untitled listdoc"; //name of file
   private ArrayList<String> rawContent; //raw strings, index corresponds to line
   private Map<String,ArrayList<String>> contents = new HashMap<String,ArrayList<String>>();
   private ArrayList<String> keys = new ArrayList<String>();
   /***
    * TITLE
    * SEP:<separator>
    * <separator><key>
    * item
    * item
    * item
    * <separator><key>
    * item
    * item
    * item
    */
   
   /**
    * updates contents to match the underlying file
    * CURRENT CONTENTS WILL BE OVERWRITTEN
    */
   public void updateFromSource(){
       try {      
           ArrayList<String> lines = new ArrayList<>(); //output is where we store all the lines from the given file
           InputStream ips = new FileInputStream(underlyingFile);
           InputStreamReader ipsr = new InputStreamReader(ips);
           BufferedReader br = new BufferedReader(ipsr);
           String line;
           while ((line = br.readLine()) != null) {
               lines.add(line);
           }
           rawContent = lines;
           title = lines.get(0); //title on first line
           separator = lines.get(1).substring(4); //separator is on second lines after the 'SEP:'
           String currentKey = "";
           ArrayList<String> currentList = new ArrayList<>();
           for(int i = 2; i < lines.size(); i++){   
            if(lines.get(i).startsWith(separator)){
               if(currentKey!=""){ //if we just ended a section, save it so we can start another
                   contents.put(currentKey, currentList);
               }
               currentKey = lines.get(i).substring(separator.length());
               keys.add(currentKey);
               currentList = new ArrayList<String>();
               continue;
            }
            currentList.add(lines.get(i));
           }
           contents.put(currentKey,currentList);
           br.close();
           ipsr.close();
           ips.close();
       } catch (FileNotFoundException ex) {
           System.out.println("Error trying to update ListDoc: " + title + " " + underlyingFile.getPath() + " not found");
           ex.printStackTrace();
       } catch (Exception e){
           e.printStackTrace();
           System.out.println("Error trying to update ListDoc: " + title + " " + underlyingFile.getPath());
       }
   }
   
   /**
    * updates the underlying file to match current in-memory contents
    */
   public void updateToSource(){
       
   }
   
   public ListDocument(){
       
   }
   
   public ListDocument(File f){
       underlyingFile = f;
       updateFromSource();
   }
   
   
   /**
    * returns the list stored in the given section
    * @param key key name to retrieve 
    * @return contents of the list
    */
   public ArrayList<String> getSection(String key){
       return contents.get(key);
   }
   
   
   /**
    * @return list of keys stored in this doc
    */
   public ArrayList<String> getKeys(){
       ArrayList<String> output = new ArrayList<>();
       for(String s : keys){
           output.add(s);
       }
       return output;
   }
   
   /**
    * adds a section to the doc with the given key title.
    * section starts as an empty list
    * @param k name of new section
    * @return if the key was successfully added, return true. If the key already existed, return false;
    */
   public boolean addKey(String k){
       if(!keys.contains(k)){
       keys.add(k);
       contents.put(k, new ArrayList<String>());
       return true;
       }else{
           System.out.println("WARNING TRYING TO ADD KEY " + k + " ALREADY EXISTS");
           return false;
       }
   }
   /**
    * replaces the list at the given key with the passed list
    * @param k key to replace
    * @param c list to replace with
    * @return weather or not the replace was a success (key doesnt exist?)
    */
   public boolean replace(String k, ArrayList<String> c){
       if(keys.contains(k)){
       contents.put(k, c);
       return true;
       }
       else return false;
   }
   
   /**
    * removes a given section from a file
    * @param key 
    */
   public void removeSection(String key){
       contents.remove(key);
       keys.remove(key);
   }
}
