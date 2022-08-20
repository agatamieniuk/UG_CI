package pl.ug.ug_ci.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ConverterDto {
    private LocalDate postingDate;
    private Double exchangeRate;

}
