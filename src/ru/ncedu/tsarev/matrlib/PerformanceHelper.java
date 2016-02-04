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
public class PerformanceHelper {
    private int initialSize;
    private int finalSize;
    private int step;
    private static int count;
    private int repeats;
    
    /**
     * constructor
     * @param initialSize
     * @param finalSize
     * @param step 
     */
    public PerformanceHelper(int initialSize, int finalSize, int step){
        int init = Math.abs(initialSize);
        int fin = Math.abs(finalSize);
        
        if (fin < init) {
            int t = init;
            init = fin;
            fin = init;
        }
        
        this.initialSize = init;
        this.finalSize = fin;
        this.step = Math.abs(step);
        
        int tempCount = 0;
        for (int size = this.initialSize; size <= this.finalSize; size+=this.step) tempCount++;
        count = tempCount;
        repeats = 5;
    }
    
    /**
     * return list with info about matrix dimensions
     * @return 
     */
    public List getMatrixSizes(){
        List sizes = new ArrayList();
        
        for (int size = initialSize, i = 0; size <= finalSize; size+=step, i++) {
            sizes.add(size);
        }            
        return sizes;
    }
    
    /**
     *  return time needed for benchmark of add-function
     * @return 
     */
    public List timeForAdd(){
        return calculateTime("+", 1);
    }
    
    /**
     * return time needed for benchmark of add-function in multithready mode
     * @param threadsCount
     * @return 
     */
    public List timeForAddMultithreadly(int threadsCount){
        return calculateTime("+", threadsCount);        
    }
    
    /**
     * return time needed for benchmark of subtract-function
     * @return 
     */
    public List timeForSubtract(){
        return calculateTime("+", 1);                
    }
    
    /**
     * return time needed for benchmark of subtract-function in multithready mode
     * @param threadsCount
     * @return 
     */
    public List timeForSubtractMultithreadly(int threadsCount){
        return calculateTime("+", threadsCount);                
    }
    
    /**
     * return time needed for benchmark of multiply-function 
     * @return 
     */
    public List timeForMultiply(){
        return calculateTime("+", 1);                
    }
    
    /**
     * return time needed for benchmark of multiply-function in multithready mode
     * @param threadsCount
     * @return 
     */
    public List timeForMultiplyMultithreadly(int threadsCount){
        return calculateTime("+", threadsCount);                
    }
    
    /**
     * benchmark function
     * @param mode
     * @param threadsCount
     * @return 
     */
    private List calculateTime( String mode, int threadsCount){
        MatrixHelper mh = new MatrixHelper();
        MatrixHelperMultiThready mhmt = new MatrixHelperMultiThready();
        
        Matrix C;
                
        long start = 0;
        long stop = 0;
        
        List result = new ArrayList();          
        
        double tempTime = 0;
        for (int size = initialSize, j = 0; size <= finalSize; size+=step, j++){
            for (int repeatsCount = 0; repeatsCount < repeats; repeatsCount++){
                TwoDimensionalMatrix A = new TwoDimensionalMatrix(size);
                TwoDimensionalMatrix B = new TwoDimensionalMatrix(size);        
                A.fillRandom();
                B.fillRandom();           

                if (threadsCount == 1){
                    switch(mode){
                        case "+":
                            start = System.nanoTime();
                            C = mh.add(A, B);
                            stop = System.nanoTime();
                            break;
                        case "-":
                            start = System.nanoTime();
                            C = mh.subtract(A, B);
                            stop = System.nanoTime();
                            break;
                        case "*":
                            start = System.nanoTime();
                            C = mh.multiply(A, B);
                            stop = System.nanoTime();
                            break;
                    }
                } else {
                    switch(mode){
                        case "+":
                            start = System.nanoTime();
                            C = mhmt.add(A, B, threadsCount);
                            stop = System.nanoTime();
                            break;
                        case "-":
                            start = System.nanoTime();
                            C = mhmt.subtract(A, B, threadsCount);
                            stop = System.nanoTime();
                            break;
                        case "*":
                            start = System.nanoTime();
                            C = mhmt.multiply(A, B, threadsCount);
                            stop = System.nanoTime();
                            break;
                    }

                }            

                tempTime = tempTime+(stop-start) / 1000000000.0;
            }
            
           result.add( tempTime/repeats );
        }
            
        return result;
    }
        
}
