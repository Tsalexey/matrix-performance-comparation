/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

/**
 *
 * @author Алексей
 */
public class MatrixHelperMultiThready{
    private String operation;
    
    /**
     * Multithreadly summation of matrix
     * @param A
     * @param B
     * @param threadsCount
     * @return 
     */
    public Matrix add(Matrix A, Matrix B, int threadsCount) {
        operation = "+";
        Matrix answer = calculate(A, B, threadsCount, operation);
        return answer;
    }

    /**
     * Multithreadly matrix substraction
     * @param A
     * @param B
     * @param threadsCount
     * @return 
     */
    public Matrix subtract(Matrix A, Matrix B, int threadsCount) {
        operation = "-";
        Matrix answer = calculate(A, B, threadsCount, operation);
        return answer;
    }

    /**
     * Multithreadly matrix multiplication
     * @param A
     * @param B
     * @param threadsCount
     * @return 
     */
    public Matrix multiply(Matrix A, Matrix B, int threadsCount){
        operation = "*";
        Matrix answer = calculate(A, B, threadsCount, operation);
        return answer;
    }
    
    /**
     * Calculate in threads given operation \n helper function 
     * @param A
     * @param B
     * @param threadsCount
     * @param operation
     * @return 
     */
    private Matrix calculate(Matrix A, Matrix B, int threadsCount, String operation) {
        Matrix result = null;
        try{
            if (A == null || B == null) {
                throw new IllegalArgumentException("Matrix A or B is null value!");
            }
            if (A.getRows() != B.getCols()) {
                throw new IllegalArgumentException("Matrix are not consistent!");
            }
            if ( threadsCount < 1){
                throw new IllegalArgumentException("Number of threads must be more then 0, was: " + threadsCount);
            }
            if ( "*".compareTo(operation)!=0 && "+".compareTo(operation)!=0 && "-".compareTo(operation)!=0 ){
                throw new IllegalArgumentException("Wrong sign of operation! Must be '*', '+' or '-', sign was: "+ operation);
            }
            
            if (threadsCount > A.getRows()) {
                threadsCount = A.getRows();
            }

            result = new TwoDimensionalMatrix(A.getRows(), B.getCols());

            int rowsForThread = A.getRows() / threadsCount;
            int additionalRows = A.getRows() % threadsCount; 
            
            Thread[] threads = new Thread[threadsCount];
            int start = 0;
            for (int i = 0; i < threadsCount; i++) {
                int count = ((i == 0) ? rowsForThread + additionalRows : rowsForThread);
                threads[i] = new MatrixHelperThread(A, B, result, start, start + count - 1, operation);
                start += count;
                threads[i].start();
            }

            try {
                for (Thread thread : threads) {
                    thread.join();
                }
            } catch (InterruptedException e) {
                System.out.println("Error: calculating threads was interrumpted!");
            }
            
        } catch( IllegalArgumentException iae){
            System.out.println("Error: " + iae.getMessage());
        }        
        return result;
    }
    
}
