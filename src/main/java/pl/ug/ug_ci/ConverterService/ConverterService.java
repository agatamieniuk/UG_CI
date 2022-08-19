package pl.ug.ug_ci.ConverterService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ug.ug_ci.webclient.converter.ConverterClient;
import pl.ug.ug_ci.webclient.converter.dto.NBPConverterDto;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final ConverterClient converterClient;

    public NBPConverterDto getUSD() {
        return converterClient.getDateforConvertion("2022-06-02");
    }

}