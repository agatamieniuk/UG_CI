package pl.ug.ug_ci.webclient.converter;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.ug.ug_ci.webclient.converter.dto.NBPConverterDto;


@Component
public class ConverterClient {

    public static final String USD_COURSE_URL = "https://api.nbp.pl/api/exchangerates/rates/a/usd/";
    public static final String FORMAT_JSON = "/?format=json";
    private RestTemplate restTemplate = new RestTemplate();

        public NBPConverterDto getDateforConvertion(String dateOfConvertion) {
        return restTemplate.getForObject(USD_COURSE_URL + dateOfConvertion + FORMAT_JSON, NBPConverterDto.class);
    }

}
