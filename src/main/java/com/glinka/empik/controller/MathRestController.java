package com.glinka.empik.controller;

import com.glinka.empik.service.IMathService;
import com.glinka.empik.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathRestController {

    IMathService mathService;

    @Autowired
    public MathRestController(IMathService mathService) {
        this.mathService = mathService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> addNumbersFromString(@RequestBody(required = false) String numbers) {
        Result result = mathService.add(numbers);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
