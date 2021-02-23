package model;

public class Rental extends BaseEntity<Long> {
    private Client client;
    private Domain domain;

    public Rental() {
        client = new Client();
        domain = new Domain();
    }

    public Rental(Client client, Domain domain) {
        this.client = client;
        this.domain = domain;
    }

    public Client getClient() {
        return client;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rental rental = (Rental) o;

        if (client != rental.client) return false;
        if (!domain.equals(rental.domain)) return false;
        return client.equals(rental.client);
    }

    @Override
    public int hashCode() {
        int result = client.hashCode();
        result = 31 * result + domain.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Domain " +
                "{Client='" + client + '\'' +
                ", Domain=" + domain +
                "} " + super.toString();
    }
}
