/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdocument;

import java.util.ArrayList;

/**
 *
 * @author Joseph
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ////////////TESTING EXAMPLE 
             String characterName = "bob";
             ArrayList<String> items = new ArrayList<>();
             items.add("sword");
             items.add("pot");
             ArrayList<String> friendList = new ArrayList<>();
             friendList.add("friend1");
             friendList.add("friend2");
             ArrayList<String> statline = new ArrayList<String>();
             statline.add("80");
             statline.add("80");
             statline.add("5");
            ListDocument doc = new ListDocument(characterName);
            doc.addKey("items");
            doc.replace("items", items);
            doc.addKey("friends");
            doc.replace("friends",friendList);
            doc.addKey("stats");
            doc.replace("stats", statline);
            doc.saveToSource();
            System.out.println(doc.getKeys());
            for(String s : doc.getKeys()){
                System.out.println(doc.getSection(s));
            }
    }
    
}
