package model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Rental extends BaseEntity<Long> implements From<String[]>, Into<String[]>, FromNode, IntoNode {
    private Long clientId;
    private Long domainId;
    private String startDate;
    private Integer duration;

    public Rental() {
        clientId = 0L;
        domainId = 0L;
        startDate = "";
        duration = 0;
    }

    public Rental(Long clientId, Long domainId, String startDate, Integer duration) {
        this.clientId = clientId;
        this.domainId = domainId;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getStartDate() {
        return startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rental rental = (Rental) o;

        if (clientId != rental.clientId) return false;
        if (!domainId.equals(rental.domainId)) return false;
        if (!startDate.equals(rental.startDate)) return false;
        if (!duration.equals(rental.duration)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + domainId.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + duration.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rental " +
                "{Client ID='" + clientId + '\'' +
                ", Domain ID=" + domainId +
                ", Start Date=" + startDate +
                ", Duration=" + duration +
                "} " + super.toString();
    }

    @Override
    public void from(String[] data) {
        this.setId(Long.valueOf(data[0]));
        this.setClientId(Long.valueOf(data[1]));
        this.setDomainId(Long.valueOf(data[2]));
        this.setStartDate(data[3]);
        this.setDuration(Integer.valueOf(data[4]));
    }

    @Override
    public String[] into() {
        return new String[] { String.valueOf(this.getId()), String.valueOf(this.getClientId()),
                String.valueOf(this.getDomainId()), this.getStartDate(), String.valueOf(this.getDuration()) };
    }

    @Override
    public void fromNode(Node data) {
        Element element = (Element)data;

        this.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
        this.clientId = Long.parseLong(element.getElementsByTagName("clientId").item(0).getTextContent());
        this.domainId = Long.parseLong(element.getElementsByTagName("domainId").item(0).getTextContent());
        this.startDate = element.getElementsByTagName("startDate").item(0).getTextContent();
        this.duration = Integer.parseInt(element.getElementsByTagName("duration").item(0).getTextContent());

    }

    @Override
    public Node intoNode(Document document) {
        Element root = document.createElement("client");

        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(this.getId().toString()));
        root.appendChild(idElement);

        Element clientIdElement = document.createElement("clientId");
        clientIdElement.appendChild(document.createTextNode(this.clientId.toString()));
        root.appendChild(clientIdElement);

        Element domainId = document.createElement("domainId");
        domainId.appendChild(document.createTextNode(this.domainId.toString()));
        root.appendChild(domainId);

        Element startDateElement = document.createElement("startDate");
        startDateElement.appendChild(document.createTextNode(this.startDate));
        root.appendChild(startDateElement);

        Element durationElement = document.createElement("duration");
        durationElement.appendChild(document.createTextNode(this.duration.toString()));
        root.appendChild(durationElement);

        return root;
    }
}
