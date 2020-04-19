
package client.controller;

import java.io.*;
import java.net.Socket;

import shared.model.Transmission;

/**
 * Communicates data to the server.
 * 
 * @author Parker Link
 * @version 1.0
 * @since 13-03-2020
 *
 */
public class ClientCommunicator {
	/**
	 * used to communicate data to the server
	 */
	private Socket socket;
	
	/**
	 * For sending objects to server (client-to-server).
	 */
	private ObjectOutputStream socketSend = null;
	
	/**
	 * For receiving objects from server (server-to-client).
	 */
	private ObjectInputStream socketReceive = null;
	
	
	/**
	 * Creates a new object for client-to-server requests, at the default address and port (address param, 9090).
	 * @param address Server address (ex: localhost)
	 */
	public ClientCommunicator(String address) {
		// Create connections to server
		try {
			socket = new Socket(address, 9090);
			socketSend = new ObjectOutputStream(socket.getOutputStream());
			socketReceive = new ObjectInputStream(socket.getInputStream());
		}
		catch (IOException e) {
			System.err.println("Error opening sockets on Client connection to server.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a transmission to the server.
	 * 
	 * @param tx the transmission to send
	 * @param waitForResponse determines whether or not to wait for a response from the client
	 * @return the response from the server
	 */
	public Transmission sendTransmission(Transmission tx, boolean waitForResponse) {
		Transmission rx = null;		
		// Send the transmission
		try {
			socketSend.writeObject(tx);
		} catch (IOException e) {
			System.err.println("Error sending transmission to server.");
			e.printStackTrace();
		}
		
		if (waitForResponse) {
			// Read the transmission
			try {
				rx = (Transmission) socketReceive.readObject();
			} catch (IOException | ClassNotFoundException e) {
				System.err.println("Error receiving transmission from server.");
				e.printStackTrace();
			}
			return rx;
		}
		
		return null;		
	}
	
	/**
	 * Closes all socket connections.
	 */
	public void close() {
		try {
			socketSend.close();
			socketReceive.close();
			socket.close();
		}
		catch (IOException e) {
			System.err.println("Error closing sockets on Client connection to server.");
			e.printStackTrace();
		}
	}

}
