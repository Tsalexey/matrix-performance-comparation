/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ncedu.tsarev.matrlib;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Алексей
 */
public class Executor {
    
    public static void main(String[] args){
        String filename = "summary";
        int startSize = 100;
        int finishSize = 1000;
        int step = 100;
        int threadsCount = 8;
        PerformanceHelper ph = new PerformanceHelper(startSize,finishSize, step);
        PerformanceComparator pc = new PerformanceComparator();
        
        List matrixSizes = ph.getMatrixSizes();
        
        List addTime = ph.timeForAdd();        
        List subTime = ph.timeForSubtract();
        
        List mulTime = ph.timeForMultiply();      
        List addTimeMultithreadly = ph.timeForAddMultithreadly(threadsCount);
        List subTimeMultithreadly = ph.timeForSubtractMultithreadly(threadsCount);
        List mulTimeMultithreadly = ph.timeForMultiplyMultithreadly(threadsCount);
       
        List addEfficiency = pc.efficiency(addTime, addTimeMultithreadly);
        List subEfficiency = pc.efficiency(subTime, subTimeMultithreadly);
        List mulEfficiency = pc.efficiency(mulTime, mulTimeMultithreadly);
        
        SummaryGenerator sg = new SummaryGenerator();
        sg.append(matrixSizes, "Sizes of matrix");
        sg.append(addTime, "Addition");        
        sg.append(addTimeMultithreadly, "Addition multithreadly");
        sg.append(addEfficiency, "Add vs Add(Threadly), %");
        sg.append(subTime, "Substraction");       
        sg.append(subTimeMultithreadly, "Substraction multithreadly");
        sg.append(subEfficiency, "Sub vs Sub(Threadly), %");
        sg.append(mulTime, "Multiplication");
        sg.append(mulTimeMultithreadly, "Multiplication multithreadly");
        sg.append(mulEfficiency, "Mul vs Mul(Threadly), %");
        
        OutputHelper out = new OutputHelper();
        out.output(sg, filename + startSize + "-" + finishSize, "txt");
        out.output(sg, filename + startSize + "-" + finishSize, "html");
    }            
}
