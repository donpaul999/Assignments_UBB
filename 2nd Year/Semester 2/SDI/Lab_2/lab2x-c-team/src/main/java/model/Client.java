package model;

/**
 * @autor paulcolta.
 */
public class Client extends BaseEntity<Long> {
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
}
