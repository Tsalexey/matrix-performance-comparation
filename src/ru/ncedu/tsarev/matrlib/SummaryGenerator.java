/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Алексей
 */
public class SummaryGenerator {
        private String summary;
        private List columns;
        private List columnNames;
        
        /**
         * default constructor
         */
        public SummaryGenerator(){
            summary = "";
            columns = new ArrayList();
            columnNames = new ArrayList();
        }
        
        /**
         * Add a column to summary information
         * @param column 
         */
        public void  append( List column){
            columns.add(column);
            columnNames.add("-no name-");
        }

        /**
         * add a column with name to summary information
         * @param column
         * @param name 
         */
        public void  append( List column, String name){
            columns.add(column);
            columnNames.add(name);
        }
        
        /**
         * generate summary based on all available columns
         */
        private void generateSummary(){
            List sizes = (List)columns.get(0);
            for (int deep = 0; deep < sizes.size(); deep++){
                for (int cols = 0; cols < columns.size(); cols++){
                    List tmp = (List)columns.get(cols);
                    summary += tmp.get(deep) + " ";
                }
                summary += "\r\n";
            }
        }
        
        /**
         * return summary information
         * @return 
         */
        public String getSummary(){
            if (columns.size() > 0){
                generateSummary();
            }
            return summary;
        }
        
        /**
         * return current number of columns
         * @return 
         */
        public List getColumns(){
            return columns;
        }
            
        /**
         * return current column names
         * @return 
         */
        public List  getColumnNames(){
            return columnNames;
        }
}
