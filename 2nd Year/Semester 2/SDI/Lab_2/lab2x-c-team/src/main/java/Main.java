import model.Client;
import model.Domain;
import model.Rental;

public class Main {
    public static void main(String args[]) {
        Client client = new Client("TestClient", false);
        Domain domain = new Domain("TestDomain", 100);
        Rental rental = new Rental(client, domain);
        System.out.println(client);
        System.out.println(domain);
        System.out.println(rental);
        System.out.println("Hello world");
    }
}
