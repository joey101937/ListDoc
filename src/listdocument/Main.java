/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdocument;

import java.io.File;

/**
 *
 * @author Joseph
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListDocument doc = new ListDocument(new File("test.txt"));
        System.out.println(doc.getSection("key1"));
        System.out.println(doc.getKeys());
    }
    
}
