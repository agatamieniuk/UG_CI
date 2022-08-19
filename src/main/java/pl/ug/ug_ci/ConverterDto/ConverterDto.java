package pl.ug.ug_ci.ConverterDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConverterDto {
    private String currency;
    private String currencyConvertionDate;
    private Double exchangeRate;
}
