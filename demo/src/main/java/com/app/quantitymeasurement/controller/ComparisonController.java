package com.app.quantitymeasurement.controller;

import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.ComparisonRequest;
import com.app.quantitymeasurement.service.ComparisonService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/compare")
public class ComparisonController {

    private ComparisonService comparisonService;

    public ComparisonController(ComparisonService comparisonService) {
        this.comparisonService = comparisonService;
    }

    @PostMapping
    public String comparePost(@RequestBody ComparisonRequest request) {

        return comparisonService.compare(
                request.getValue1(),
                request.getUnit1(),
                request.getValue2(),
                request.getUnit2()
        );
    }
}