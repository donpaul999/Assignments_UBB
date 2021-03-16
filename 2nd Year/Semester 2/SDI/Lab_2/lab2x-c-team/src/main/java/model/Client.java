package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @autor paulcolta.
 */
public class Client extends BaseEntity<Long> implements From<String[]>, Into<String[]>, FromNode, IntoNode {
    private String name;
    private Boolean isBusiness;

    public Client() {
        name = "";
        isBusiness = false;
    }

    public Client(String name, Boolean isBussiness) {
        this.name = name;
        this.isBusiness = isBussiness;
    }

    public String getName() {
        return name;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsBusiness(Boolean isBusiness) {
        this.isBusiness = isBusiness;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!name.equals(client.name)) return false;
        if (!isBusiness.equals(client.isBusiness)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = isBusiness.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client " +
                "{name='" + name + '\'' +
                ", isBusiness=" + (isBusiness ? "Yes" : "No") +
                "} " + super.toString();
    }

    @Override
    public void from(String[] data) {
        this.setId(Long.parseLong(data[0]));
        this.setName(data[1]);
        this.setIsBusiness(data[2].equals("yes"));
    }

    @Override
    public String[] into() {
        return new String[] { String.valueOf(this.getId()), this.getName(), (isBusiness ? "yes" : "no") };
    }

    @Override
    public void fromNode(Node data) {
        Element element = (Element)data;

        this.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
        this.name = element.getElementsByTagName("name").item(0).getTextContent();
        this.isBusiness = element.getElementsByTagName("isBusiness").item(0).getTextContent().equalsIgnoreCase("YES");
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

        Element isBusinessElement = document.createElement("isBusiness");
        isBusinessElement.appendChild(document.createTextNode((this.isBusiness ? "yes" : "no")));
        root.appendChild(isBusinessElement);

        return root;
    }
}
