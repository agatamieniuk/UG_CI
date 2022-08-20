package pl.ug.ug_ci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ug.ug_ci.service.ConverterService;
import pl.ug.ug_ci.model.ConverterDto;

@RestController
public class ConverterController {

    final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping
    public ConverterDto getUSD(){
        return converterService.getUSD();
    }
}
