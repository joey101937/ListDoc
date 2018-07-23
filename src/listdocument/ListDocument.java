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
       } catch (FileNotFoundException ex) {
           System.out.println("Error trying to update ListDoc: " + title + " " + underlyingFile.getPath() + " not found");
           ex.printStackTrace();
       } catch (Exception e){
           e.printStackTrace();
           System.out.println("Error trying to update ListDoc: " + title + " " + underlyingFile.getPath());
       }
   }
   
   public ListDocument(){
       
   }
   
   public ListDocument(File f){
       underlyingFile = f;
       updateFromSource();
   }
   
   
   
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
    */
   public void addKey(String k){
       keys.add(k);
       contents.put(k, new ArrayList<String>());
   }
   
}
