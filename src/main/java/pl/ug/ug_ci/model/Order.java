package pl.ug.ug_ci.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")

public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nazwa")
    private String name;
    @Column(name = "data_zaksiegowania")
    LocalDate orderPostingDate;
    @Column(name = "koszt_PLN")
    private Double payInPLN;
    @Column(name = "koszt_USD")
    private Double payInDollar;

 //TODO SET PLN COST(?) -> dolar * kasa za komputer
    //    public void setPlncost(ConverterDto converterDto) {
//        System.out.println(converterDto);
//        this.plncost = converterDto.getExchangeRate() * usdcost;
//    }
}
