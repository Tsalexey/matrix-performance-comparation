/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Алексей
 */
public class TwoDimensionalMatrixTest {
    private int n;
    private Matrix testingMatrix;
    ArrayList<ArrayList<Double>> expected;
    
    /**
     * Default constructor
     */
    public TwoDimensionalMatrixTest() {
        n = 5;               
        
        expected = new ArrayList<>();
        expected = fillExpected(expected);
        ArrayList<ArrayList<Double>> temp = new ArrayList<>();
        temp = makeCopy(expected);
        testingMatrix = new TwoDimensionalMatrix(temp);
    }
    
    /**
     * make clone of array 
     * @param a
     * @return 
     */
    private ArrayList<ArrayList<Double>> makeCopy(ArrayList<ArrayList<Double>> a){
        ArrayList<ArrayList<Double>> t = new ArrayList<ArrayList<Double>>();
        t = (ArrayList<ArrayList<Double>>)a.clone();
        for(int  i=0; i< a.size(); i++){
            ArrayList<Double> temp = new ArrayList();
            temp = (ArrayList<Double>)a.get(i).clone();
            t.set(i, temp);
        }
        return t;
    }
    
    /**
     * fill array of expected values
     * @param a
     * @return 
     */
    private ArrayList<ArrayList<Double>> fillExpected(ArrayList<ArrayList<Double>> a){
        Random r = new Random();
        for (int i = 0; i < n; i++){
            ArrayList<Double> innerList = new ArrayList();
            for (int j = 0; j < n; j++){
                innerList.add(r.nextDouble());
            }
            a.add(innerList);
        }
        return a;
    }
        
    /**
     * Test of get method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testGet() {
            System.out.println("Get test");
            int i = 0;
            int j = 0;
            Matrix instance = testingMatrix;            
            ArrayList<Double> temp = expected.get(i);          
            double expResult = temp.get(j);
            double result = instance.get(i, j);
            assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of set method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testSet() {
        System.out.println("Set test");
        int i = 0;
        int j = 0;
        double exp = -10;
        Matrix instance = testingMatrix;
        instance.set(i, j, exp);
        double result = instance.get(i, j);
        assertEquals(exp, result, 0.0);
    }

    /**
     * Test of divideRow method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testDivideRow() {
        System.out.println("DivideRow test");
        Matrix instance = testingMatrix;
        double divisor = 2;
        int row = 0;
        
        Matrix exp = new TwoDimensionalMatrix(instance.getRows(), instance.getCols());
        for (int i = 0; i < instance.getRows(); i++){
            for (int j = 0; j < instance.getCols(); j++){
                exp.set(i, j, instance.get(i, j));
            }
        }
        
        instance.divideRow(row, divisor);
        
        for (int j = 0; j < n ; j++){            
            assertEquals(exp.get(row, j), instance.get(row, j), 0.0);
        }
        // division by zero
        System.out.println("- division by zero test");        
        divisor = 0;
        instance.divideCol(row, divisor);
        //negative row
        System.out.println("-negative row test");        
        row = -4;
        divisor = 1;
        instance.divideCol(row, divisor);   
    }
    
    /**
     * Test of multiplyCol method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testMulCol() {
        System.out.println("mulCol");
        int j = 0;
        double val = 100;
        Matrix instance = testingMatrix;
        instance.multiplyCol(j, val);        
        for (int i = 0; i < n ; i++){
            ArrayList<Double> list = new ArrayList();
            list = expected.get(i);            
            assertEquals(list.get(j), instance.get(i, j), 0.0);        
        }

    }

    /**
     * Test of multiplyRow method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testMulRow() {
        System.out.println("mulRow");
        int i = 0;
        double val = 100;
        Matrix instance = testingMatrix;
        instance.multiplyRow(i, val);
        ArrayList<Double> list = new ArrayList();
        list = expected.get(i);        
        for (int j = 0; j < n ; j++){
            assertEquals(list.get(j), instance.get(i, j), 0.0);
        }        
    }

    /**
     * Test of divideCol method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testDivideCol() {
        System.out.println("divideCol");
        double divisor = 2;
        int j = 0;
        Matrix instance = testingMatrix;
        instance.divideCol(j, divisor);
        for (int i = 0; i < n ; i++){
            ArrayList<Double> list = new ArrayList();
            list = expected.get(i);            
            assertEquals(list.get(j), instance.get(i, j), 0.0);
        }
        // division by zero
        System.out.println("-division by zero test");        
        divisor = 0;
        instance.divideCol(j, divisor);
        // negative column
        System.out.println("-negative col test");        
        divisor = 1;
        j = -3;
        instance.divideCol(j, divisor);

    }
    
    /**
     * Test of getRows method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testGetRows() {
        System.out.println("getRows test");
        Matrix istance = testingMatrix;
        int result = istance.getRows();
        assertEquals(n, result);
    }

    /**
     * Test of getCols method, of class TwoDimensionalMatrix.
     */
    @Test
    public void testGetCols() {
        System.out.println("getCols test");
        Matrix istance = testingMatrix;
        int result = istance.getCols();
        assertEquals(n, result);
    }
    
}
