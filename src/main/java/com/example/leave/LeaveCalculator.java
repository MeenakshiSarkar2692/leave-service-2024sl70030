nano src/main/java/com/example/leave/LeaveCalculator.java
//BITS ID: 2024SL70030
package com.example.leave;

public class LeaveCalculator {

    // Calculates the remaining leave balance
    public static int calculateRemainingLeave(
            int totalLeave, int leavesTaken) {

        if (totalLeave < 0 || leavesTaken < 0) {
            throw new IllegalArgumentException(
                "Leave values cannot be negative");
        }

        if (leavesTaken > totalLeave) {
            throw new IllegalArgumentException(
                "Leaves taken cannot exceed total leave");
        }

        return totalLeave - leavesTaken;
    }

    // Calculates leave encashment amount
    public static double calculateLeaveEncashment(
            int remainingLeaveDays, double monthlySalary) {

        if (remainingLeaveDays < 0 || monthlySalary < 0) {
            throw new IllegalArgumentException(
                "Values cannot be negative");
        }

        double dailySalary = monthlySalary / 30.0;

        return remainingLeaveDays * dailySalary;
    }
}
