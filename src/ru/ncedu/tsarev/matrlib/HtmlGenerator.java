/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.util.List;

/**
 *
 * @author Алексей
 */
public class HtmlGenerator {
    private String head;
    private String tail;
    private String generatedTable;
    
    /**
     * default constructor
     */
    public HtmlGenerator(){
        head = "<html><head><title></title></head><body>" 
            +"<style type='text/css'>"
            +".tg  {border-collapse:collapse;border-spacing:0;border-color:#aabcfe;border-width:1px;border-style:solid;}"
            +".tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#669;background-color:#e8edff;}"
            +".tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;border-color:#aabcfe;color:#039;background-color:#b9c9fe;}"
            +".tg .tg-amwm{font-weight:bold;text-align:center;vertical-align:top}"
            +".tg .tg-rbyy{background-color:#D2E4FC;font-weight:bold;color:#000000;text-align:center;vertical-align:top}"
            +".tg .tg-yw4l{vertical-align:top}"
            +".tg .tg-6k2t{background-color:#D2E4FC;vertical-align:top}"
            +"</style>";             
        tail = "</table></body></html>";        
        generatedTable = "";
    }
    
    /**
     * return complete html page with statistics
     * @param sg
     * @return 
     */
    public String buildHtml(SummaryGenerator sg){
        try{
            if (sg == null) throw new IllegalArgumentException("SummaryGenerator pointer is null");
            
            List columns = sg.getColumns();
            List columnNames = sg.getColumnNames();
            generateHead(columnNames);
            
            
            String class2 = "tg-yw4l";
            String class1 = "tg-6k2t";
            List sizes = (List)columns.get(0);
            for (int deep = 0; deep < sizes.size(); deep++){
                generatedTable += "<tr>";
                for (int cols = 0; cols < columns.size(); cols++){
                    List tmp = (List)columns.get(cols);
                    String tmpClass = "";
                    if (deep %2 ==0) tmpClass = class1; else tmpClass = class2;
                    generatedTable += "<td class=\""+ tmpClass+ "\">"+tmp.get(deep)+"</td>";
                }
                generatedTable += "</tr>";
            }

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return head + generatedTable + tail;
    }
    
    /**
     * generate head of table
     * @param names 
     */
    private void generateHead(List names){
        generatedTable += "<table class='tg'><tr>";
        for(int i = 0; i < names.size(); i++){
            generatedTable+= "<th class=\"tg-amwm\">" + names.get(i) + "</th>";
        }
        generatedTable += "</tr>";  
    }
    
}
