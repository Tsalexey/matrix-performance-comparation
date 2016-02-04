/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.util.ArrayList;

/**
 *
 * @author Алексей
 */
public interface Matrix {

    /**
     * get (i,j) element of matrix
     * @param i
     * @param j
     * @return 
     */
    public double get(int i , int j);

    /**
     *  set value of (i,j) element
     * @param i
     * @param j 
     * @param n
     */
    public void set(int i, int j, double n);

    /**
     * divide i-row by numer n
     * @param i
     * @param n 
     */
    public void divideRow(int i, double n);
    
    /**
     * divide j-col by number n
     * @param j
     * @param n 
     */
    public void multiplyCol(int j, double n);
    
    /**
     * mul i-row by numer n
     * @param i
     * @param n 
     */
    public void multiplyRow(int i, double n);
    
    /**
     * mul j-col by number n
     * @param j
     * @param n 
     */
    public void divideCol(int j, double n);    
        
    /**
     * return number of rows
     * @return 
     */
    public int getRows();
        
    /**
     * return number of cols
     * @return 
     */
    public int getCols();
    
    /**
     * return NxM array
     */
    public ArrayList<ArrayList<Double>> getListOfLists();
}
