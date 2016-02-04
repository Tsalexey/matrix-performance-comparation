/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Алексей
 */
public class TwoDimensionalMatrix implements Matrix{
    private ArrayList <ArrayList <Double>> matrix; 
    private int row;
    private int col;
    
    /**
     * Default constructor
     */
    public TwoDimensionalMatrix(){
        matrix = new ArrayList <>();
        col = 1;
        row = 1;
        matrix = new ArrayList <>();        
        ArrayList<Double> innerList = new ArrayList();
        innerList.add(0.0);
        matrix.add(innerList);
    }
    
    /**
     * Constructor for square matrix NxN
     * @param n 
     */
    public TwoDimensionalMatrix(int n){
        n = Math.abs(n);
        if (n == 0) n = 1;
        row = n;
        col = n;

        matrix = new ArrayList <>();        
        for (int i = 0; i < row; i++){
            ArrayList<Double> innerList = new ArrayList();
            for (int j = 0; j < col; j++){                
                innerList.add(0.0);
            }
            matrix.add(innerList);
        }        
    }
    
    /**
     * Constructor for matrix with dimension IxJ 
     * @param i
     * @param j 
     */
    public TwoDimensionalMatrix(int i, int j){
        i = Math.abs(i);
        j = Math.abs(j);
        if (i == 0) i = 1;
        if (j == 0) j = 1;
        row = i;
        col = j;
        
        matrix = new ArrayList <>();        
        for (int k = 0; k < row; k++){
            ArrayList<Double> innerList = new ArrayList();
            for (int l = 0; l < col; l++){
                innerList.add(0.0);
            }
            matrix.add(innerList);
        }

    }
    
    /**
     * Constructor for array
     * @param a 
     */
    public TwoDimensionalMatrix(ArrayList <ArrayList<Double>> a){
        matrix = a;
    }

    /**
     * 
     * @param other 
     */
    public TwoDimensionalMatrix(Matrix other) {
        this(other.getListOfLists());
        row = other.getRows();
        col = other.getCols();
    }
    
    /**
     * Get matrix element in position (i,j)
     * @param i
     * @param j
     * @return 
     */
    @Override
    public double get(int i, int j) {
        try{
            if (i <0 || j <0){
                throw new IllegalArgumentException("Row's and col's number must be >= 0");
            }
            if (i > row || j > col){
                throw new IllegalArgumentException("list index out of bounds!");
            }
        } catch(IllegalArgumentException e){
            System.out.println( e.getMessage());
        }
        return matrix.get(i).get(j);
    }

    /**
     * Set 'n' value for position (i,j) in matrix
     * @param i
     * @param j
     * @param n 
     */
    @Override
    public void set(int i, int j, double n) {
        try{
            if (i <0 || j <0){
                throw new IllegalArgumentException("Row's and col's number must be >= 0!");
            }
            if (i > row || j > col){
                throw new IllegalArgumentException("List index out of bounds");
            }
            
            ArrayList<Double> innerList = matrix.get(i);
                        
            innerList.set(j, n);
            
            matrix.set(i, innerList);
            
        } catch(IllegalArgumentException e){
            System.out.println( e.getMessage());
        }
    }

    /**
     * Divide row 'i' by value 'n'
     * @param i
     * @param n 
     */
    @Override
    public void divideCol(int i, double n) {
        try {
            if (n==0) throw new ArithmeticException("Division by zero!");
            if (i < 0) throw new IllegalArgumentException("Row number must be >= 0!");
            if (i > row){
                throw new IllegalArgumentException("List index out of bounds");
            }

            for (int k = 0; k < row; k++){
                ArrayList<Double> innerList = matrix.get(k);
                Double temp = innerList.get(i);
                innerList.set(i, temp/n);
                matrix.set(k, innerList);
            }
            
        } catch(ArithmeticException e){
            System.out.println( e.getMessage());
        } catch (IllegalArgumentException iae){
            System.out.println( iae.getMessage());
        }
        
    }

    /**
     * Multiplication of column 'j' by value n'n
     * @param j
     * @param n 
     */
    @Override
    public void multiplyRow(int j, double n) {
        try {
            if (j < 0) throw new IllegalArgumentException("Col number must be >= 0!");
            if (j > col){
                throw new IllegalArgumentException("List index out of bounds");
            }

            ArrayList<Double> innerList = matrix.get(j);

            for (int k = 0; k < row; k++){
                Double temp = innerList.get(k);
                innerList.set(k, temp*n);
            }
            matrix.set(j, innerList);
             
        } catch (IllegalArgumentException iae){
            System.out.println( iae.getMessage());
        }
                    
    }

    /**
     * Multiplication of row 'i' by value 'n'
     * @param i
     * @param n 
     */
    @Override
    public void multiplyCol(int i, double n) {
        try {
            if (i < 0) throw new IllegalArgumentException("Row number must be >= 0!");
            if (i > row){
                throw new IllegalArgumentException("List index out of bounds");
            }

            for (int k = 0; k < row; k++){
                ArrayList<Double> innerList = matrix.get(k);
                Double temp = innerList.get(i);
                innerList.set(i, temp*n);
                matrix.set(k, innerList);
            }

        } catch (IllegalArgumentException iae){
            System.out.println( iae.getMessage());
        }
    }

    /**
     * Division of column 'j' by value 'n'
     * @param j
     * @param n 
     */
    @Override
    public void divideRow(int j, double n) {
        try {
            if (n==0) throw new ArithmeticException("Division by zero!");
            if (j<0) throw new IllegalArgumentException("Col's number must be >= 0!");
            if (j > col){
                throw new IllegalArgumentException("List index out of bounds");
            }

            ArrayList<Double> innerList = matrix.get(j);

            for (int k = 0; k < row; k++){
                Double temp = innerList.get(k);
                innerList.set(k, temp/n);
            }
            matrix.set(j, innerList);
                
        } catch(ArithmeticException e){
            System.out.println( e.getMessage());
        } catch(IllegalArgumentException iae){
            System.out.println( iae.getMessage());
        }
    }

    /**
     * Return number of rows in matrix
     * @return 
     */
    @Override
    public int getRows() {
        return matrix.size();
    }

    /**
     * Return number of columns in matrix
     * @return 
     */
    @Override
    public int getCols() {
        ArrayList<Double> inner = matrix.get(0);
        return inner.size();
    }
 
    /**
     * return array 
     * @return 
     */
    @Override
    public ArrayList<ArrayList<Double>> getListOfLists(){
        return matrix;
    }

    /**
     * fill randomly matrix
     */
    public void fillRandom(){
        Random r = new Random();
        for (int i = 0; i < getRows(); i++){
            ArrayList<Double> innerList = new ArrayList();
            innerList = matrix.get(i);
            for(int j = 0; j < getCols(); j++){
                innerList.set(j, r.nextDouble());
            }
            matrix.set(i, innerList);
        }
    }
    
    /**
     * equals method
     * @param other
     * @return 
     */
    @Override
    public boolean equals(Object other){
        if (other == null){
            return false;
        }
        if (other == this){
            return true;
        }
        if (!(other instanceof Matrix)){
          return false;        
        }
        Matrix m = (TwoDimensionalMatrix) other;
        for (int i= 0; i < m.getRows(); i++){
            for (int j = 0; j < m.getCols(); j++){
                if ( m.get(i, j) != this.get(i, j) ){
                    return false;
                }
            }
        }
        return true;       
    }
    
    /**
     * Hashcode method
     * @return 
     */
    @Override
    public int hashCode(){
        int result = 0;
        for (int i = 0; i < getRows(); i++){
            for (int j = 0; j < getCols(); j++){
                result+=result + matrix.get(i).get(j);;
            }
        }
        return result;
    }  
    
}
