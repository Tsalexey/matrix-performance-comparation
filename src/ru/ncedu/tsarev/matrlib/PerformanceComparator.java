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
public class PerformanceComparator {
    public List efficiency(List a, List b){
        List result = new ArrayList();
        
        int length = a.size();
        
        for (int i = 0; i < length; i++){
            Double time1 = (Double)a.get(i);
            Double time2 = (Double)b.get(i);
            result.add( (Math.round( ((time2*100/time1)-100) * 100.0) / 100.0) + "%" );
        }
        
        return result;
    }

}
