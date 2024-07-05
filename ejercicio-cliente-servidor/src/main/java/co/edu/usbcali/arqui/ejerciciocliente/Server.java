package co.edu.usbcali.arqui.ejerciciocliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> handleClient(socket)).start();
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private void handleClient(Socket socket) {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (MensajesUtils.SALUDO_SERVER.equalsIgnoreCase(inputLine)) {
                    out.println(MensajesUtils.SALUDO_CLIENTE);
                } else if (MensajesUtils.DESPEDIDA_SERVER.equalsIgnoreCase(inputLine)) {
                    out.println(MensajesUtils.DESPEDIDA_CLIENTE);
                    break;
                } else {
                    out.println("unrecognized message");
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
