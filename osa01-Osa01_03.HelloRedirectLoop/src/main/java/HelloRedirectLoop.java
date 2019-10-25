
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        int laskuri = 1;

        while (true) {
            Socket asiakas = server.accept();
            System.out.println("Pyyntö: " + laskuri);
            laskuri++;

            Scanner s = new Scanner(asiakas.getInputStream());

            String p = s.nextLine();

            if (p.contains("quit")) {
                break;
            }

 

            PrintWriter kirjoittaja = new PrintWriter(asiakas.getOutputStream());

            kirjoittaja.write("HTTP/1.1 302 Found\r\n");
            kirjoittaja.write("Location: http://localhost:8080\r\n");
            kirjoittaja.flush();

 

            kirjoittaja.close();
            asiakas.close();
            s.close();
        }
        server.close();
    }
}
