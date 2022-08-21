package pl.ug.ug_ci.webclient.converter.dto;

import lombok.Getter;
import java.time.LocalDate;

@Getter
public class NBPConverterRates {
    private LocalDate effectiveDate;
    private Double mid;
}
