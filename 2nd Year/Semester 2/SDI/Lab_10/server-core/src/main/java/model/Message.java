package model;

import java.io.*;

public class Message {
    public static final String HOST = "localhost" ;
    public static final int PORT = 1234 ;
    private String header, body;

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public Message() {

    }

    public void writeTo(OutputStream os) throws IOException {
        String messageToPrint = this.header + System.lineSeparator() + this.body + System.lineSeparator();
        // System.out.println(messageToPrint);
        os.write(messageToPrint.getBytes());
    }

    public void readFrom(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String bufferMessage = "";
        do {
            //System.out.println(bufferMessage);
            bufferMessage += br.readLine();
            bufferMessage += System.lineSeparator();
        } while (br.ready());
        bufferMessage = bufferMessage.substring(0, bufferMessage.length() - System.lineSeparator().length());
        String[] inputParsed = bufferMessage.split(System.lineSeparator(), 2);

        this.header = inputParsed[0];
        if (inputParsed.length > 1) {
            this.body = inputParsed[1];
        }
    }

    public String getHeader() {
        return this.header;
    }

    public String getBody() {
        return this.body;
    }

    @Override
    public String toString(){
        return "<" + this.header + ">:<" + this.body + ">";
    }

}
