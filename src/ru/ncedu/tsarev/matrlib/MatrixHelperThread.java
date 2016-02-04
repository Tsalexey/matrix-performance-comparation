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
public class MatrixHelperThread extends Thread {
        private int startRow, endRow, n;
        private Matrix A, B, result;
        String operation;

        /**
         * Constructor for thread-helper
         * @param A
         * @param B
         * @param result
         * @param startRow
         * @param endRow
         * @param operation 
         */
        public MatrixHelperThread(Matrix A, Matrix B, Matrix result, int startRow, int endRow, String operation) {
            this.A = A;
            this.B = B;
            this.result = result;
            this.startRow = startRow;
            this.endRow = endRow;
            this.operation = operation;
        }

        @Override
        public void run() {
            for (int row = startRow; row <= endRow ; row++) {
                for (int col = 0; col < result.getRows(); col++) {
                    switch(operation){
                        case "*":
                            result.set(row, col, modeMultiply(row, col));
                            break;
                        case "+":
                            result.set(row, col, modeAdd(row, col));
                            break;
                        case "-":
                            result.set(row, col, modeSubtract(row, col));
                            break;
                        default:
                            System.out.println("Wrong mode for MatrixHelperThread, must be '+', '-' or '*'");
                            break;
                    }                                  
                }
            }
        }

        /**
         * Multiplication in thread
         * @param row
         * @param col
         * @return 
         */
        private double modeMultiply(int row, int col) {
            int c = 0;
            for (int i = 0; i < B.getCols(); i++) {
                c += A.get(row, i) * B.get(i, col);
            }
            return c;
        }   

        /**
         * Addition in thread
         * @param row
         * @param col
         * @return 
         */
        private double modeAdd(int row, int col) {
            return A.get(row, col) + B.get(row, col);
        }   

        /**
         * Substraction in thread
         * @param row
         * @param col
         * @return 
         */
        private double modeSubtract(int row, int col) {
            return A.get(row, col) - B.get(row, col);
        }     
    }
    
