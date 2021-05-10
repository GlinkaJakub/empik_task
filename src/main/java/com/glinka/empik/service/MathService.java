package com.glinka.empik.service;

import com.glinka.empik.dto.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class MathService implements IMathService {

    static int counter = 0;

    @Override
    public Result add(String numbers) {
        if (numbers == null || numbers.isEmpty() || numbers.isBlank()){
            return new Result(0, ++counter);
        }
        String pattern = "-?[0-9]+([;|\n+]-?[0-9]+)*";
        if (numbers.startsWith("//")){
            int id = numbers.indexOf("\\n");
            String[] delimiters = numbers.substring(3, id-1).split("]\\[");
            numbers = numbers.substring(id+2);
            for(String d : delimiters){
                numbers = numbers.replace(d, ";");
            }
        }
        if(!numbers.matches(pattern)) {
            throw new IllegalArgumentException("Not match pattern");
        }
        String[] numbersAsString = numbers.split("[\n;]");
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        boolean hasNegative = false;
        for (String s : numbersAsString) {
            int i = Integer.parseInt(s);
            if (i < 0){
                negatives.add(i);
                hasNegative = true;
            } else if (i <= 1000){
                sum += i;
            }
        }
        if (hasNegative){
            throw new NumberFormatException("negatives not allowed: " + negatives);
        }
        return new Result(sum, ++counter);
    }

}
