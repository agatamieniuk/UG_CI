package pl.ug.ug_ci.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConverterDto {
    private String postingDate;
    private Double exchangeRate;

}
