/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listdocument;

import java.io.File;

/**
 * @author Joseph
 */
public class ListDocument {
   private File underlyingFile;
   private String separator = "~";
   private String title = "untitled listdoc";

    public File getUnderlyingFile() {
        return underlyingFile;
    }

    public void setUnderlyingFile(File underlyingFile) {
        this.underlyingFile = underlyingFile;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
   
   
}
