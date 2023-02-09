package Ej2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GestorProcesos extends Thread {
    private Socket socket;
    private OutputStream os;

    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void realizarProceso() throws IOException {
        os = this.socket.getOutputStream();
        InputStream is = this.socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String mensaje = br.readLine();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(lectura(mensaje));
        bw.newLine();
        bw.flush();
    }

    private static String lectura(String direccion) {
        String lectura = "";
        File archivo;
        String resultado = "No encontrado";
        String arrayLectura[];
        boolean encontrado = false;
        FileReader fr = null;
        BufferedReader br = null;
        Scanner s = null;

        try {

            archivo = new File("fichero.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            s = new Scanner(br);

            while (s.hasNextLine() && !encontrado) {
                lectura = s.nextLine();
                arrayLectura = lectura.split(" ");
                if (direccion.equals(arrayLectura[0])){
                    resultado = arrayLectura[1];
                    encontrado = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return resultado;
    }

}
