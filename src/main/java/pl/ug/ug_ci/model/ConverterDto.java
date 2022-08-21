package pl.ug.ug_ci.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ConverterDto {
    //TODO: postingDate i LocalDate postingoOrderDate; z ConverterDto jako jedna zmienna
    private LocalDate postingDate;
    private Double exchangeRate;


}
