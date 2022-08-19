package pl.ug.ug_ci.webclient.converter;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ug.ug_ci.ConverterDto.ConverterDto;
import pl.ug.ug_ci.webclient.converter.dto.NBPConverterDto;

@Component
public class ConverterClient {

    public static final String USD_COURSE_URL = "https://api.nbp.pl/api/exchangerates/rates/a/usd/";
    public static final String FORMAT_JSON = "/?format=json";
    private RestTemplate restTemplate = new RestTemplate();


    public ConverterDto getDateOfUSDConvertion(String dateOfConvertion) {
        NBPConverterDto nbpConverterDto = restTemplate.getForObject(USD_COURSE_URL + dateOfConvertion + FORMAT_JSON, NBPConverterDto.class);

        return ConverterDto.builder()
                .currency(nbpConverterDto.getCode())
                .currencyConvertionDate(nbpConverterDto.getNbpConverterRatesDto().getEffectiveDate())
                .exchangeRate(nbpConverterDto.getNbpConverterRatesDto().getMid()).build();
    }
//
//    public NBPConverterDto getDateOfUSDConvertion(String dateOfConvertion) {
//        return restTemplate.getForObject(USD_COURSE_URL + dateOfConvertion + FORMAT_JSON, NBPConverterDto.class);
//    }
}
