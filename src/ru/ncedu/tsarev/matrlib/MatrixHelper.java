/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.io.IOException;

/**
 *
 * @author Алексей
 */
public class MatrixHelper{
    private int strassenRestriction = 64;
    
    /**
     * Summ of two matrix
     * @param A
     * @param B
     * @return 
     */
    public Matrix add(Matrix A, Matrix B){
        Matrix result = null;
        try {
            
            if (A==null || B==null) {
                throw new NullPointerException(); 
            }
            
            if ( A.getCols() != B.getCols() || A.getRows() != B.getRows() ){
                throw new IOException();
            }
        
            result = new TwoDimensionalMatrix(A.getRows(), A.getCols());
            
            for (int i=0; i<A.getRows(); i++){
                for(int j = 0; j<A.getCols(); j++){
                    result.set(i, j, A.get(i, j) + B.get(i, j) );
                }
            }
            
        } catch (IOException e){
            System.out.println("Wrong dimension of matrix for addition");
        } catch(NullPointerException npe){
            System.out.println("NullPointerException for addition");
        }
        return result;
    }

    /**
     * Subs of two matrix
     * @param A
     * @param B
     * @return 
     */
    public Matrix subtract(Matrix A, Matrix B){
        Matrix result = null;
        try {
            if (A==null || B==null) {
                throw new NullPointerException(); 
            }

            if ( A.getCols() != B.getCols() || A.getRows() != B.getRows() ){
                throw new IOException();
            }
        
            result = new TwoDimensionalMatrix(A.getRows(), A.getCols());
            
            for (int i=0; i<A.getRows(); i++){
                for(int j = 0; j<A.getCols(); j++){
                    result.set(i, j, A.get(i, j) - B.get(i, j) );
                }
            }
            
        } catch (IOException e){
            System.out.println("Wrong dimension of matrix for subtraction");
        } catch(NullPointerException npe){
            System.out.println("NullPointerException for subtraction");
        }
        return result;
    }    
    
    /**
     * matrix multiplication by Strassen algorithm
     * @param A
     * @param B
     * @return 
     */ 
    public Matrix multiply(Matrix A, Matrix B){
        Matrix result = null;
        try {
            
            if (A==null || B==null) {
                throw new NullPointerException(); 
            }
            
            if ( A.getRows() != B.getCols() ){
                throw new IOException();
            }
            
            result = new TwoDimensionalMatrix(A.getRows(), B.getCols());

        if ( A.getRows() <= strassenRestriction || B.getRows() <= strassenRestriction || A.getCols() <= strassenRestriction || B.getCols() <= strassenRestriction ){
            result = simpleMultiply(A, B);
        } else {
            int maxPow = findMaxPow(A.getRows(), A.getCols(), A.getRows(), A.getCols());
            
            Matrix a11 = new TwoDimensionalMatrix(maxPow, maxPow);
            Matrix a12 = new TwoDimensionalMatrix(maxPow, A.getCols() - maxPow);
            Matrix a21 = new TwoDimensionalMatrix(A.getRows() - maxPow, maxPow);
            Matrix a22 = new TwoDimensionalMatrix(A.getRows() - maxPow, A.getCols() - maxPow);
            
            Matrix b11 = new TwoDimensionalMatrix(maxPow, maxPow);
            Matrix b12 = new TwoDimensionalMatrix(maxPow, B.getCols() - maxPow);
            Matrix b21 = new TwoDimensionalMatrix(B.getRows() - maxPow, maxPow);
            Matrix b22 = new TwoDimensionalMatrix(B.getRows() - maxPow, B.getCols() - maxPow);
            
            Matrix c11 = new TwoDimensionalMatrix(maxPow, maxPow);
            Matrix c12 = new TwoDimensionalMatrix(maxPow, B.getCols() - maxPow);
            Matrix c21 = new TwoDimensionalMatrix(A.getRows() - maxPow, maxPow);
            Matrix c22 = new TwoDimensionalMatrix(A.getRows() - maxPow, B.getCols() - maxPow);
            
            a11 = getSubmatrix(A, 0, maxPow, 0, maxPow);
            a12 = getSubmatrix(A, 0, maxPow, maxPow, A.getCols());
            a21 = getSubmatrix(A, maxPow, A.getRows(), 0, maxPow);
            a22 = getSubmatrix(A, maxPow, A.getRows(), maxPow, A.getCols());

            b11 = getSubmatrix(B, 0, maxPow, 0, maxPow);
            b12 = getSubmatrix(B, 0, maxPow, maxPow, B.getCols());
            b21 = getSubmatrix(B, maxPow, B.getRows(), 0, maxPow);
            b22 = getSubmatrix(B, maxPow, B.getRows(), maxPow, B.getCols());
                        
            c11 = add(MatrixHelper.this.multiply(a11, b11),  MatrixHelper.this.multiply(a12, b21) );
            c12 = add(MatrixHelper.this.multiply(a11, b12),  MatrixHelper.this.multiply(a12, b22) );
            c21 = add(MatrixHelper.this.multiply(a21, b11),  MatrixHelper.this.multiply(a22, b21) );
            c22 = add(MatrixHelper.this.multiply(a21, b12),  MatrixHelper.this.multiply(a22, b22) );            
                        
            result = buildMatrix(c11, c12, c21, c22);             
        }
         
        } catch (IOException e){
            System.out.println("Matrix are not consistent!");
        
        } catch(NullPointerException npe){
            System.out.println("NullPointerException for multiplication");
        }
        return result;
    }    
    
    /**
    * Classical matrix multiplication \n Helper-function for strassen matrix multiplication
    * @param A
    * @param B
    * @return 
    */
    public Matrix simpleMultiply(Matrix A, Matrix B){
        Matrix C = null;
        try {
            if (A==null || B==null) {
                throw new NullPointerException(); 
            }
            
            if ( A.getRows() != B.getCols() ){
                throw new IOException();
            }
        
            C = new TwoDimensionalMatrix(A.getRows(), B.getCols());
                
            for (int row = 0; row < A.getRows(); row++) {  
                for (int col = 0; col < B.getCols(); col++) {
                    int sum = 0;
                    for (int i = 0; i < B.getRows(); ++i){
                        sum += A.get(row, i) * B.get(i, col);
                    }
                    C.set(row, col, sum);
                }
            }                
        } catch (IOException e){
            System.out.println("Wrong dimension of matrix for multiplication");
        } catch(NullPointerException npe){
            System.out.println("NullPointerException for multiplication");
        }
        return C;    
    }

    /**
     * Scalar multiplication
     * @param A
     * @param x
     * @return 
     */
    public Matrix multiply(Matrix A, double x){
        Matrix C = null;
        try {
            if (A==null) {
                throw new NullPointerException(); 
            }
        
            C = new TwoDimensionalMatrix(A.getRows(), A.getCols());
                
            for (int row = 0; row < A.getRows(); row++) {  
                for (int col = 0; col < A.getCols(); col++) {
                    C.set( row, col, A.get(col, col)*x );
                }
            }                        
        } catch(NullPointerException npe){
            System.out.println("NullPointerException for multiplication");
        }
        return C;          
    }
    
    /**
     * return maximal aggregate pow of 2
     * @param a
     * @param b
     * @param c
     * @param d
     * @return 
     */
    private int findMaxPow(int a, int b, int c, int d){
        return getHightBit(Math.min( Math.min(a, b), Math.min(c, d)))-1;
    }

    /**
     * find high bit
     * @param x
     * @return 
     */
    private int getHightBit(int x) {
        x |= x >> 1;
        x |= x >> 2;
        x |= x >> 4;
        x |= x >> 8;
        x |= x >> 16;
        return x - (x >> 1);
    }

    /**
     * Build matrix from 4 submatrix for Strassen algorithm
     * @param c11
     * @param c12
     * @param c21
     * @param c22
     * @return 
     */
    private Matrix buildMatrix( Matrix c11, Matrix c12, Matrix c21, Matrix c22){                
        Matrix result = new TwoDimensionalMatrix( c11.getRows() + c21.getRows(), c11.getCols() + c22.getCols());

        int c11Row = c11.getRows();
        int c11Col = c11.getCols();
                                
        for (int i = 0; i < c11Row; i++){
            for(int j = 0; j < c11Col; j++){
                result.set(i, j, c11.get(i, j));
            }
        }
        
        for (int i = 0; i < c11Row; i++){
            for(int j = c11Col, t =0; j < result.getCols(); j++, t++){
                result.set(i, j, c12.get(i, t));
            }
        }
           
        for (int i = c11Row, t = 0; i < result.getRows(); i++, t++){
            for(int j = 0; j < c11Col; j++){
                result.set(i, j, c21.get(t, j));
            }
        }
        
        for (int i = c11Row, t1 = 0; i < result.getRows(); i++, t1++){
            for(int j = c11Col, t2 = 0; j < result.getCols(); j++, t2++){
                result.set(i, j, c22.get(t1, t2));
            }
        }
        
        return result;
    }

    /**
     * Select submatrix of matrix M
     * @param M
     * @param rowFirst
     * @param rowLast
     * @param colFirst
     * @param colLast
     * @return 
     */
    private Matrix getSubmatrix(Matrix M, int rowFirst, int rowLast, int colFirst, int colLast){        
       Matrix result = new TwoDimensionalMatrix(rowLast-rowFirst, colLast-colFirst);
       for (int i = rowFirst, iResult = 0 ; i < rowLast; i++, iResult++){
           for (int j = colFirst, jResult = 0; j < colLast; j++, jResult++){
             result.set(iResult, jResult, M.get(i, j));
           }
       } 
       return result;
    }
    
    /**
     * Transpose matrix
     * @param A
     * @return 
     */
    public Matrix transpose(Matrix A){
        Matrix result = null;
        try {
            if (A==null) {
                throw new NullPointerException(); 
            }
            
            result = new TwoDimensionalMatrix(A.getCols(), A.getRows());

            for (int row = 0; row < A.getRows(); row++) {  
                for (int col = 0; col < A.getCols(); col++) {
                    result.set( col, row, A.get(row, col) );
                }
            }                

        } catch(NullPointerException npe){
            System.out.println("NullPointerException for transpose");
        }
        return result;
    }

    
}
