package com.gcaa.fplmb.tcp.socket.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.model.ConnectionInterface;
import com.gcaa.fplmb.tcp.socket.FplMbSocket;

@Component
@Scope("prototype")
public class MgpsTcpServer extends FplMbSocket {

	private static final Logger LOGGER = Logger.getLogger(MgpsTcpServer.class);

	private boolean terminate = false;
	private ServerSocket serverSocket = null;
	private List<Socket> connectedClients = new ArrayList<Socket>();
	private ConnectionInterface tcpConnModel = null;

	public void init(ConnectionInterface tcpServModel) throws Exception {
		tcpConnModel = tcpServModel;
		serverSocket = new ServerSocket(tcpServModel.getPort());
		LOGGER.info("Server Socket is Running...");
		LOGGER.info("Server is waiting for Connections");

		try {

			while (!terminate) {
				final Socket clientSocket = serverSocket.accept();

				new Thread(new Runnable() {

					@Override
					public void run() {
						communicateToSocket(clientSocket);
					}
				}).start();

				addConnectedClient(clientSocket);
			}
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			ex.printStackTrace();
		}
	}

	/**
	 * Check for STX and ETX here And forward to DB and Output Channels
	 *
	 * @param message
	 *            Client Request Message
	 * @return Server Response Message
	 */
	public String doProcess(String message) {
		try {
			LOGGER.info("Received Message = " + message);
			if (message.equalsIgnoreCase("terminate")) {
				terminate = false;
				if (serverSocket != null) {
					serverSocket.close();
				}
			}

			if (message.length() >= "broadcast".length()
					&& message.substring(0, "broadcast".length()).equalsIgnoreCase("broadcast")) {
/*				String clientName = message.substring("broadcast".length() + 1, message.indexOf("ExCxNAME"));*/
				sendToClient(/*clientName.trim(),*/
						"Response Message from Server - " + tcpConnModel.getInterfaceName() + " " + message);
			}

			if (message.length() >= "getClients".length()
					&& message.substring(0, "getClients".length()).equalsIgnoreCase("getClients")) {
				return connectedClients.toString();
			}

			LOGGER.info("Processing the Client Request...");
			Date date = new Date(System.currentTimeMillis());
			return date.toString() + "\n";
		} catch (Exception ex) {
			LOGGER.error("Error while processing the message " + message + ". Ignoring this message \n");

			return "Error while processing the message " + message
					+ "\n. Ignoring this message and continuing processing the next. \n";
		}
	}

	private void sendToClient(/*String clientName,*/ String message) throws Exception {
		for (Socket socket : connectedClients) {
			sendData(socket, message.getBytes());
		}
		
	}

	private void addConnectedClient(Socket e) {
		connectedClients.add(e);
	}

}