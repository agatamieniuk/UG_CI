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
@Setter
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nazwa")
    private String name;
    @Column(name = "data_zaksiegowania")
    LocalDate postingoOrderDate;
    @Column(name = "koszt_USD")
    private Double usdcost;
    @Column(name = "koszt_PLN")
    private Double plncost;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", postingoOrderDate=" + postingoOrderDate +
                ", usdcost=" + usdcost +
                ", plncost=" + plncost +
                '}';
    }

    //    @XmlElement(name = "id")
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    @XmlElement(name = "name")
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @XmlElement(name = "postingoOrderDate")
//    public void setPostingoOrderDate(LocalDate postingoOrderDate) {
//        this.postingoOrderDate = postingoOrderDate;
//    }
//
//    @XmlElement(name = "usdcost")
//    public void setUsdcost(Double usdcost) {
//        this.usdcost = usdcost;
//    }
//
//    @XmlElement(name = "plncost")
//    public void setPlncost(Double plncost) {
//        this.plncost = plncost;
//    }

    //    public void setPlncost(ConverterDto converterDto) {
//        System.out.println(converterDto);
//        this.plncost = converterDto.getExchangeRate() * usdcost;
//    }
}
