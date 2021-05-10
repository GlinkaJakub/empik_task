package com.glinka.empik.service;

import com.glinka.empik.dto.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MathServiceTest {

    IMathService mathService;

    @Autowired
    public MathServiceTest(IMathService mathService) {
        this.mathService = mathService;
    }

    @Test
    void addOneNumber() {
        Result result = mathService.add("1");
        assertEquals(1, result.getSum());
    }

    @Test
    void addSimpleNumber() {
        Result result = mathService.add("1;2;3");
        assertEquals(6, result.getSum());
    }

    @Test
    void addWithEmptyString() {
        Result result = mathService.add("");
        assertEquals(0, result.getSum());
    }

    @Test
    void addWithNewLineSeparator(){
        Result result = mathService.add("2;3\n5;8\n6");
        assertEquals(24, result.getSum());
    }

    @Test
    void addWithNewLineAndComaSeparator() {
        assertThrows(IllegalArgumentException.class, () -> mathService.add("2;3;\n5;8\n6"));
    }

    @Test
    void addWithWrongFormatNumber() {
        assertThrows(IllegalArgumentException.class, () -> mathService.add("//[;;]\\n_empik"));
    }

    @Test
    void addThrowException() {
        assertThrows(IllegalArgumentException.class, () -> mathService.add("a, 1, 2, 3"));
    }

    @Test
    void addWithCustomDelimiter() {
        Result result = mathService.add("//[;;]\\n1;;2;;7");
        assertEquals(10, result.getSum());
    }

    @Test
    void addWithCustomDelimiterSpecialRegexChar() {
        Result result = mathService.add("//[***]\\n1***2***7");
        assertEquals(10, result.getSum());
    }

    @Test
    void addWithCustomMultiDelimiter() {
        Result result = mathService.add("//[***][..][;]\\n1***2;7..3..2***300");
        assertEquals(315, result.getSum());
    }

    @Test
    void addWithNegativeNumber(){
        assertThrows(NumberFormatException.class, () -> mathService.add("1;-2;7;-3;2"));
    }

    @Test
    void addWithNumber1000() {
        Result result = mathService.add("//[***][..][;]\\n1***2;7..3..2***1000");
        assertEquals(1015, result.getSum());
    }

    @Test
    void addWithNumberBiggerThan1000() {
        Result result = mathService.add("//[***][..][;]\\n1***2;7..3..2***1001");
        assertEquals(15, result.getSum());
    }
}