package pl.ug.ug_ci.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class Order {
    @Column(name = "nazwa")
    private String name;
    @Column(name = "data_zaksiegowania")
    LocalDate orderPostingDate;
    @Column(name = "koszt_USD")
    private Float payInDollar;
    @Id
    @Column(name = "koszt_PLN")
    private Float payInPLN;

    @XmlElement(name = "nazwa")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "data_ksiegowania")
    public void setOrderPostingDate(LocalDate orderPostingDate) {
        this.orderPostingDate = orderPostingDate;
    }

    @XmlElement(name = "koszt_PLN")
    public void setPayInPLN(Float payInPLN) {
        this.payInPLN = payInPLN;
    }

    @XmlElement(name = "koszt_USD")
    public void setPayInDollar(Float payInDollar) {
        this.payInDollar = payInDollar;
    }
}
