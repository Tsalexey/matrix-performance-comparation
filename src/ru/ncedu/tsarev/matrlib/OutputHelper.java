/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Алексей
 */
public class OutputHelper {
    
    /**
     * write summary info in filename.type
     * @param sg
     * @param filename
     * @param type 
     */
    public void output(SummaryGenerator sg, String filename, String type){
        try{
            if (type == null || filename == null || "".equals(filename)) throw new IOException("Bad name for output file!");
            if ( !("txt".equals(type) || "html".equals(type)) ) throw new IOException("Bad type of output file");
            if ( sg == null) throw new IOException("Null pointer for summary gengerator!");
            
            FileWriter writer = new FileWriter(filename + "." +type, false);
            String source = null;
            if ("txt".equals(type)){
                source= sg.getSummary();
            }            
            if ("html".equals(type)){
                HtmlGenerator generator = new HtmlGenerator();
                source = generator.buildHtml(sg);   
            }
            writer.write(source);             
            writer.flush();
        }
        catch(IOException ex){             
            System.out.println(ex.getMessage());
        }         
    }
}
