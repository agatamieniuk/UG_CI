package pl.ug.ug_ci.ConverterService;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ug.ug_ci.ConverterDto.ConverterDto;

@Service
public class ConverterService {

    public static final String USD_COURSE_URL = "https://api.nbp.pl/api/exchangerates/rates/a/usd/";
    public static final String FORMAT_JSON = "/?format=json";
    private RestTemplate restTemplate = new RestTemplate();

    public ConverterDto getUSD(){
        String response = getDateOfUSDConvertion("2022-08-01");
        System.out.println(response);
        return null;
    }

    private String getDateOfUSDConvertion(String dateOfConvertion) {
        return restTemplate.getForObject(USD_COURSE_URL + dateOfConvertion + FORMAT_JSON, String.class, "2022-01-19");
    }
}