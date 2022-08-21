package pl.ug.ug_ci.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@XmlRootElement(name = "faktura")
public class Order{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
    @Column(name = "nazwa")
    private String name;
    @Column(name = "data_zaksiegowania")
    LocalDate orderPostingDate;
    @Column(name = "koszt_USD")
    private Double payInDollar;
    @Column(name = "koszt_PLN")
    private Double payInPLN;


    @XmlElement(name = "nazwa")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "data_ksiegowania")
    public void setOrderPostingDate(LocalDate orderPostingDate) {
        this.orderPostingDate = orderPostingDate;
    }

    //TODO: SET PLN COST(?) metoda -> dolar * kasa za komputer
//    @XmlElement(name = "koszt_PLN")
//        public void setPlncost(ConverterDto converterDto) {
////????????????????????????????????????????????????????????????
//        this.payInPLN = converterDto.getExchangeRate() * payInDollar;
//    }

    @XmlElement(name = "koszt_USD")
    public void setPayInDollar(Double payInDollar) {
        this.payInDollar = payInDollar;
    }
}
