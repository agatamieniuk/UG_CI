package pl.ug.ug_ci.ConverterService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ug.ug_ci.model.ConverterDto;
import pl.ug.ug_ci.webclient.converter.ConverterClient;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ConverterService {

    private final ConverterClient converterClient;

    public ConverterDto getUSD() {
    //TODO obsługa wyjątków gdy daty nie ma w bazie
        return converterClient.getDateforConvertion(LocalDate.of(2022,06,02));
    }

}