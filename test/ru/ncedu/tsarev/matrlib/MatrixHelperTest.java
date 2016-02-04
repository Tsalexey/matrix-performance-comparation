/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.util.ArrayList;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Алексей
 */
public class MatrixHelperTest {
    private int n;
    private int threadCount;
    private TwoDimensionalMatrix A;
    private TwoDimensionalMatrix B;
    
    
    public MatrixHelperTest() {
        n = 400;
        threadCount = 8;
        A = new TwoDimensionalMatrix(n);
        B = new TwoDimensionalMatrix(n);
        A.fillRandom();
        B.fillRandom();
    }
    
    /**
     * Test of add method, of class MatrixHelper.
     */
    @Test
    public void testAdd() {
        System.out.println("add test");    
        MatrixHelper mh = new MatrixHelper();
        MatrixHelperMultiThready mhmt = new MatrixHelperMultiThready();
        Matrix result = null;
        Matrix resultMultiThreadly = null;
        result = mh.add(A, B);
        resultMultiThreadly = mhmt.add(A, B, threadCount);                
        
        assertEquals(result, resultMultiThreadly);
    }

    /**
     * Test of subtract method, of class MatrixHelper.
     */
    @Test
    public void testSub() {
        System.out.println("sub test");
        MatrixHelper mh = new MatrixHelper();
        MatrixHelperMultiThready mhmt = new MatrixHelperMultiThready();
        Matrix result = null;
        Matrix resultMultiThreadly = null;
        result = mh.subtract(A, B);
        resultMultiThreadly = mhmt.subtract(A, B, threadCount);
        
        assertEquals(result, resultMultiThreadly);
    }

    /**
     * Test of multiply method, of class MatrixHelper.
     */
    @Test
    public void testMul_Matrix_Matrix() {
        System.out.println("mul test");
        
        MatrixHelper mh = new MatrixHelper();
        MatrixHelperMultiThready mhmt = new MatrixHelperMultiThready();
        
        Matrix result = null;
        Matrix resultMultiThreadly = null;
        Matrix resultSimpleMul = null;
        
        result = mh.multiply(A, B);
        resultMultiThreadly = mhmt.multiply(A, B, threadCount);
        resultSimpleMul = mh.simpleMultiply(A, B);
        
        assertEquals(resultSimpleMul, result);
        assertEquals(resultSimpleMul, resultMultiThreadly);
        assertEquals(result, resultMultiThreadly);

    }

    /**
     * Test of simpleMultiply method, of class MatrixHelper.
     */
    @Test
    public void testSimpleMul() {
        System.out.println("simpleMul test");
        
        int n = 2;   
        
        Matrix A = new TwoDimensionalMatrix(n);
        A.set(0, 0, 0.0);
        A.set(0, 1, -1.0);
        A.set(1, 0, 1.0);
        A.set(1, 1, 0.0);
        
        Matrix B = new TwoDimensionalMatrix(n);
        A.set(0, 0, 1.0);
        A.set(0, 1, 2.0);
        A.set(1, 0, 3.0);
        A.set(1, 1, 4.0);
        
        Matrix expected = new TwoDimensionalMatrix(n);
        A.set(0, 0, -3.0);
        A.set(0, 1, -4.0);
        A.set(1, 0, 1.0);
        A.set(1, 1, 2.0);
                
        MatrixHelper mh = new MatrixHelper();
        Matrix result = mh.simpleMultiply(A, B);
                
        assertEquals(expected, result);
    }

    /**
     * Test of multiply method, of class MatrixHelper.
     */
    @Test
    public void testMul_Matrix_double() {
        System.out.println("mul test for scalar");
        MatrixHelper mh = new MatrixHelper();
        Matrix result = A;        
        double x = 0.0;
        Matrix expected = new TwoDimensionalMatrix(n);
        result = mh.multiply(result, x);
        assertEquals(expected, result);
    }

    /**
     * Test of transpose method, of class MatrixHelper.
     */
    @Test
    public void testTranspose() {
        System.out.println("transpose test");
        MatrixHelper mh = new MatrixHelper();
        Matrix expected = A;        
        Matrix transposed = mh.transpose(expected);

        assertEquals( expected, mh.transpose(transposed));
    }
    
}
