package com.cyc.oio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 * 
 * @author cyc_e
 *
 */
public class Client {
	public static void main(String[] args) {
		String msg = "Clinet date";
		Socket socket = null;
		BufferedReader is = null;
		PrintWriter pw = null;
		try {
			socket = new Socket("localhost", 8080);
			pw = new PrintWriter(socket.getOutputStream());
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw.println(msg);
			pw.flush();
			String line = is.readLine();
			System.out.println("received from server : " + line);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.close();
			try {
				is.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
