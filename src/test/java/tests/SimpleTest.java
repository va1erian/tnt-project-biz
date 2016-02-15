/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import calc.CalculatorService;
import calc.NegativeNumberException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Valerian
 */
public class SimpleTest {
    
    CalculatorService s;
    
    public SimpleTest() {
        s = new CalculatorService();
    }
    
    
    @Test
    public void testAddService() throws NegativeNumberException{
        assertEquals(6, s.add(4, 2));
        assertEquals(1, s.add(1, 0));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
