package model;

import java.util.Date;

public class Rental extends BaseEntity<Long> {
    private Long clientId;
    private Long domainId;
    private Date startDate;
    private Integer duration;

    public Rental() {
        clientId = 0L;
        domainId = 0L;
        startDate = new Date();
        duration = 0;
    }

    public Rental(Long clientId, Long domainId, Date startDate, Integer duration) {
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

    public Date getStartDate() {
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

    public void setStartDate(Date startDate) {
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
        return "Domain " +
                "{Client ID='" + clientId + '\'' +
                ", Domain ID=" + domainId +
                ", Start Date=" + startDate +
                ", Duration=" + duration +
                "} " + super.toString();
    }
}
