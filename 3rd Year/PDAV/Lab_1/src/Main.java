import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PPM image = new PPM("nt-P3.pbm");
        Encoder encoder = new Encoder(image);
        Decoder decoder = new Decoder(encoder, "img.pbm");
    }
}
