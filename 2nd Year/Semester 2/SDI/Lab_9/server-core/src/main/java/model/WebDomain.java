package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "WebDomain")
public class WebDomain extends BaseEntity<Long> implements From<String[]>, Into<String[]>, FromNode, IntoNode, Serializable {
    private String name;
    private Integer price;

    public WebDomain() {
        this.name = "";
        this.price = 0;
    }

    public WebDomain(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public Integer getPrice() {
        return this.price;
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
        if (o == null || this.getClass() != o.getClass()) return false;

        WebDomain webDomain = (WebDomain) o;

        if (!this.name.equals(webDomain.name)) return false;
        return this.price.equals(webDomain.price);
    }

    @Override
    public int hashCode() {
        int result = this.price.hashCode();
        result = 31 * result + this.name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Domain " +
                "{name='" + this.name + '\'' +
                ", price=" + this.price +
                "} " + super.toString();
    }

    @Override
    public void from(String[] data) {
        this.setId(Long.parseLong(data[0]));
        this.setName(data[1]);
        this.setPrice(Integer.valueOf(data[2]));
    }

    @Override
    public String[] into() {
        return new String[] { String.valueOf(this.getId()), this.getName(), String.valueOf(this.getPrice()) };
    }

    @Override
    public void fromNode(Node data) {
        Element element = (Element)data;

        this.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
        this.name = element.getElementsByTagName("name").item(0).getTextContent();
        this.price = Integer.parseInt(element.getElementsByTagName("price").item(0).getTextContent());
    }

    @Override
    public Node intoNode(Document document) {
        Element root = document.createElement("client");

        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(this.getId().toString()));
        root.appendChild(idElement);

        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(this.name));
        root.appendChild(nameElement);

        Element priceElement = document.createElement("price");
        priceElement.appendChild(document.createTextNode(this.price.toString()));
        root.appendChild(priceElement);

        return root;
    }
}
