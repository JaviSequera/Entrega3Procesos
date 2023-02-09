package Ej2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        try {
            InetAddress direccion = InetAddress.getLocalHost();
            // 1 - Crear un socket de tipo cliente indicando IP y puerto del servidor
            System.out.println("(Cliente) Estableciendo conexión con el servidor");
            Socket cliente = new Socket(direccion, 49200);

            // 2 - Abrir flujos de lectura y escritura
            InputStream is = cliente.getInputStream();
            OutputStream os = cliente.getOutputStream();
            System.out.println("(Cliente) Conexión establecida");

            System.out.println("Escriba el sitio web para obtener su ip");
            String sitioWeb = s.next();

            // 3 - Intercambio de datos con el servidor
            System.out.println("(Cliente): Envía mensaje al servidor...");
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(sitioWeb);
            bw.newLine();
            bw.flush();


            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String mensaje = br.readLine();

            // Leo mensajes que me envía el servidor
            System.out.println("El servidor me envía el siguiente mensaje: " + mensaje);

            // 4 - Cerrar flujos de lectura y escritura
            is.close();
            os.close();

            // 5 - Cerrar la conexión
            System.out.println("(Cliente) Cerrando conexiones...");
            cliente.close();
            System.out.println("(Cliente) Conexiones cerradas...");

        } catch (UnknownHostException e) {
            System.err.println("No se encuentra el host especificado.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error en la conexión con el servidor.");
            e.printStackTrace();
        }
    }
}
