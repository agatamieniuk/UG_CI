package pl.ug.ug_ci.ConverterController;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ug.ug_ci.ConverterService.ConverterService;
import pl.ug.ug_ci.model.ConverterDto;
import pl.ug.ug_ci.webclient.converter.dto.NBPConverterDto;

@RestController
public class ConverterController {

    final ConverterService converterService;

    public ConverterController(ConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping
    public ConverterDto getUSD() throws JsonProcessingException {
        return converterService.getUSD();
    }
}
