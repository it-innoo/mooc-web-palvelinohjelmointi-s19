

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        // odotetaan pyyntöä porttiin 8080
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            // odotetaan pyyntöä
            Socket socket = server.accept();

            // luetaan pyyntö
            Scanner lukija = new Scanner(socket.getInputStream());

            String s;
            boolean loppu = false;
            while ((s = lukija.nextLine()) != null) {
                System.out.println(s);
                if (s.contains("/quit")) {
                    loppu = true;
                }
                if (s.isEmpty()) {
                    break;
                }
            }
            // ...

            // kirjoitetaan vastaus
            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.write("HTTP/1.1 200 OK\r\n");
            kirjoittaja.write("\r\n");

            if (!loppu) {
                Files.lines(Paths.get("index.html")).forEach((x) -> kirjoittaja.write(x));
            }
            kirjoittaja.flush();
            // ...

            // vapautetaan resurssit
            // on ferme les flux.
            lukija.close();
            kirjoittaja.close();
            socket.close();
            break;
        }
        server.close();
    }
}
