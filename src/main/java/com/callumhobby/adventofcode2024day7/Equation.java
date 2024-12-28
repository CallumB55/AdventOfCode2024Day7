/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.callumhobby.adventofcode2024day7;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CallumBinns
 */
public class Equation {

    public Long testValue;
    private List<Long> equationValues;
    public boolean isValid;
    private List<String> operators;

    public Equation(String line, List<String> operators) {
        this.operators = operators;
        String[] ln = line.split("\\s+");
        this.testValue = Long.valueOf(ln[0].replace(":", ""));
        this.equationValues = new ArrayList<>();
        for (int i = 1; i < ln.length; i++) {//starting at 1 to skip the testValue which comes first
            equationValues.add(Long.valueOf(ln[i]));
        }
        this.isValid = false;
        List<List<Long>> possibleCalibrationResults = new ArrayList<>();
        possibleCalibrationResults = evaluator(equationValues.size() - 2, possibleCalibrationResults);//minus 2 because evaluator runs one behind the max and is 0 indexed
        for (Long calibrationResult : possibleCalibrationResults.getLast()) {
            if (calibrationResult.equals(testValue)) {
                isValid = true;
                break;
            }
        }

    }

    private List<List<Long>> evaluator(Integer i, List<List<Long>> calibrationResults) {//should initially called with the i being the index of the final number in the list of values
        List<Long> resultLine = new ArrayList<>();
        if (i > 0) { // stop recursion if at start
            //calibrationResults = 
            //Integer levelBelow = i - 1;
            evaluator(i-1, calibrationResults);
        } else {
            List<Long> startLine = new ArrayList<>();
            startLine.add(equationValues.get(i));
            calibrationResults.add(startLine);
        }
        for (String operator : operators) {

            for (Long integer : calibrationResults.get(i)) {
                resultLine.add(calculator(integer, equationValues.get(i + 1), operator));
            }

        }
        calibrationResults.add(resultLine);

        return calibrationResults;
    }

    private Long calculator(Long a, Long b, String operator) {
        return switch (operator) {
            case "*" ->
                a * b;
            case "||" ->
                (a*(long)Math.pow(10, String.valueOf(b).length()))+b;
            default ->
                a + b;
        };
    }

}
