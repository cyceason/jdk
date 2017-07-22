package com.cyc.oio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 
 * @author cyc_e
 *
 */
public class Service {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		BufferedReader is = null;
		PrintWriter pw = null;
		try {
			server = new ServerSocket(8080);
			socket = server.accept();
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = is.readLine();
			System.out.println("received from client : " + line);
			pw = new PrintWriter(socket.getOutputStream());
			pw.println("received data : " + line);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				is.close();
				socket.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
