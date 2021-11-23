package cz.cvut.tjv.internet_shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Publisher implements Serializable {

    @Id
    @Column(name = "publisher_name")
    private String id;

    @Column(name = "publisher_income")
    private Integer income;

    public Publisher() {
    }

    public Publisher(String name, Integer income) {
        this.id = name;
        this.income = income;
    }

    public String getId() {
        return id;
    }

    public int getIncome() {
        return income;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id.equals(publisher.id) && income.equals(publisher.income);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, income);
    }
}
