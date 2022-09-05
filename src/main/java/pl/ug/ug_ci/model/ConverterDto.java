package pl.ug.ug_ci.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class ConverterDto {
    private LocalDate postingDate;
    private Float exchangeRate;
}
