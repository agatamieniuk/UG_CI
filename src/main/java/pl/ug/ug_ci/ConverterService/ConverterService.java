package pl.ug.ug_ci.ConverterService;

import org.springframework.stereotype.Service;
import pl.ug.ug_ci.ConverterDto.ConverterDto;
import pl.ug.ug_ci.webclient.converter.ConverterClient;

@Service
public class ConverterService {

    private final ConverterClient converterClient;

    public ConverterService(ConverterClient converterClient) {
        this.converterClient = converterClient;
    }

    public ConverterDto getUSD(){
        String response = converterClient.getDateOfUSDConvertion("2022-06-02");
        System.out.println(response);
        return null;
    }
}