package com.gcaa.fplmb.tcp.socket.client;

import java.net.Socket;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.model.TcpConnectionModel;
import com.gcaa.fplmb.tcp.socket.FplMbSocket;

@Component
@Scope("prototype")
public class MgpsTcpClient extends FplMbSocket {

	Logger LOGGER = Logger.getLogger(MgpsTcpClient.class);

	private TcpConnectionModel tcpClient;

	Socket socket = null;

	public void init(TcpConnectionModel tcpClient) throws Exception {
		this.tcpClient = tcpClient;
		socket = new Socket(tcpClient.getHost(), tcpClient.getPort());
		// Create Client Request for initial pulse
		String request = "CLIENT_REQUEST:SEND_SYSTEM_TIME \n Client socket name = " + tcpClient.getSocketName();

		sendData(socket, request.getBytes());

		communicateToSocket(socket);
	}

	@Override
	public String doProcess(String message) {
		return null;
	}

	public TcpConnectionModel getTcpClient() {
		return tcpClient;
	}

	public void setTcpClient(TcpConnectionModel tcpClient) {
		this.tcpClient = tcpClient;
	}
}