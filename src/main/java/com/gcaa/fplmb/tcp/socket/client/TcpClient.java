package com.gcaa.fplmb.tcp.socket.client;

import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.model.ConnectionInterface;
import com.gcaa.fplmb.tcp.socket.FplMbSocket;

@Component
@Scope("prototype")
public class TcpClient extends FplMbSocket {

	Logger LOGGER = Logger.getLogger(TcpClient.class);

	private ConnectionInterface connDetail;

	Socket socket = null;

	public void init(ConnectionInterface tcpClient) throws Exception {
		this.connDetail = tcpClient;
		socket = new Socket(tcpClient.getHost(), tcpClient.getPort());
		// Create Client Request for initial pulse
		String request = "CLIENT_REQUEST:SEND_SYSTEM_TIME \n Client socket name = " + tcpClient.getInterfaceName();

		sendData(socket, request.getBytes());

		communicateToSocket(socket);
	}

	@Override
	public String doProcess(String message) {
		return null;
	}

	public ConnectionInterface getTcpClient() {
		return connDetail;
	}

	public void setTcpClient(ConnectionInterface tcpClient) {
		this.connDetail = tcpClient;
	}


}