package com.app.quantitymeasurement.controller;

import org.springframework.web.bind.annotation.*;
import com.app.quantitymeasurement.service.ConversionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/convert")
public class ConversionController {

    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping
    public double convertGet(@RequestParam double value,
                             @RequestParam String from,
                             @RequestParam String to) {

        return conversionService.convert(value, from, to);
    }
}