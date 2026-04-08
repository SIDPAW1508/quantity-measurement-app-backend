package com.app.quantitymeasurement.controller;

import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.dto.ArithmeticRequest;
import com.app.quantitymeasurement.dto.ConversionRequest;
import com.app.quantitymeasurement.service.ArithmeticService;
import com.app.quantitymeasurement.service.ConversionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/arithmetic")
public class ArithmeticController {

    private ArithmeticService arithmeticService;
    private ConversionService conversionService;

    public ArithmeticController(ArithmeticService arithmeticService, ConversionService conversionService) {
        this.arithmeticService = arithmeticService;
        this.conversionService = conversionService;
    }
   

    @PostMapping("/add")
    public double addPost(@RequestBody ArithmeticRequest request) {

        return arithmeticService.add(
                request.getValue1(),
                request.getUnit1(),
                request.getValue2(),
                request.getUnit2()
        );
    }


    @PostMapping("/subtract")
    public double subtractPost(@RequestBody ArithmeticRequest request) {

        return arithmeticService.subtract(
                request.getValue1(),
                request.getUnit1(),
                request.getValue2(),
                request.getUnit2()
        );
    }

    @PostMapping("/multiply")
    public double multiplyPost(@RequestBody ArithmeticRequest request) {

        return arithmeticService.multiply(
                request.getValue1(),
                request.getUnit1(),
                request.getValue2(),
                request.getUnit2()
        );
    }

    @PostMapping("/divide")
    public double dividePost(@RequestBody ArithmeticRequest request) {

        return arithmeticService.divide(
                request.getValue1(),
                request.getUnit1(),
                request.getValue2(),
                request.getUnit2()
        );
    }

    @PostMapping("/convert")
    public double convertPost(@RequestBody ConversionRequest request) {

        return conversionService.convert(
                request.getValue(),
                request.getFrom(),
                request.getTo()
        );
    }
}