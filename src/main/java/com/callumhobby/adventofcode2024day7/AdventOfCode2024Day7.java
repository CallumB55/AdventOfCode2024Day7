/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.callumhobby.adventofcode2024day7;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author CallumBinns
 */
public class AdventOfCode2024Day7 {

    public static void main(String[] args) {
        InputReader in = new InputReader("src\\main\\java\\com\\callumhobby\\adventofcode2024day7\\Input.txt");
        List<String> operators = new ArrayList<>();
        operators.add("*");
        operators.add("+");
        List<Equation> equations = new ArrayList<>();
        for (String line : in.getLines()) {
            equations.add(new Equation(line,operators));
        }
        Long totalCalibrationResult = 0L;
        for (Equation equation : equations) {
            if (equation.isValid) {
                totalCalibrationResult += equation.testValue;
            }
        }
        System.out.println("total calibration result pre-concatenation: "+totalCalibrationResult);
        
        operators.add("||");
        List<Equation> concatenatedEquations = new ArrayList<>();
        for (String line : in.getLines()) {
            concatenatedEquations.add(new Equation(line,operators));
        }
        totalCalibrationResult = 0L;
        for (Equation equation : concatenatedEquations) {
            if (equation.isValid) {
                totalCalibrationResult += equation.testValue;
            }
        }
        System.out.println("total calibration result post-concatenation: "+totalCalibrationResult);
        
    }
}
