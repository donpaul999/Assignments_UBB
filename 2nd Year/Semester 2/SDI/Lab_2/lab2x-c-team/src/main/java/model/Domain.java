package model;

import java.text.DateFormat;

public class Domain extends BaseEntity<Long> {
    private String name;
    private Integer price;

    public Domain() {
        name = "";
        price = 0;
    }

    public Domain(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Domain domain = (Domain) o;

        if (!name.equals(domain.name)) return false;
        if (!price.equals(domain.price)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Domain " +
                "{name='" + name + '\'' +
                ", price=" + price +
                "} " + super.toString();
    }

}
