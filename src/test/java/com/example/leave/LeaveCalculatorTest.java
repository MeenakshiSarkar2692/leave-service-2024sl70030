
package com.example.leave;

import org.junit.Test;
import static org.junit.Assert.*;

public class LeaveCalculatorTest {

    // Test 1: Remaining leave calculation
    @Test
    public void testRemainingLeave() {

        int result =
            LeaveCalculator.calculateRemainingLeave(20, 5);

        assertEquals(15, result);
    }

    // Test 2: Leave encashment calculation
    @Test
    public void testLeaveEncashment() {

        double result =
            LeaveCalculator.calculateLeaveEncashment(
                10, 30000);

        assertEquals(10000.0, result, 0.01);
    }

    // Test 3: Invalid leave input
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLeave() {

        LeaveCalculator.calculateRemainingLeave(10, 15);
    }
}
