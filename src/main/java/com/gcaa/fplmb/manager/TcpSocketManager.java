package com.gcaa.fplmb.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SocketUtils;

import com.gcaa.fplmb.model.TcpConnectionModel;
import com.gcaa.fplmb.tcp.socket.FplMbSocket;
import com.gcaa.fplmb.tcp.socket.MGPSSocketFactory;

@Component
public class MgpsSocketManager {

	@Autowired
	private MGPSSocketFactory socketFactory;

	private List<TcpConnectionModel> tcpClientConnections = new ArrayList<TcpConnectionModel>();
	private List<TcpConnectionModel> tcpServerConnections = new ArrayList<TcpConnectionModel>();

	private static final Logger logger = Logger.getLogger(MgpsSocketManager.class);

	@PostConstruct
	public void initiateServerClient() {
		populateTcpClients();
		populateTcpServers();

		initializeSockets(tcpServerConnections);
		initializeSockets(tcpClientConnections);
	}

	private void initializeSockets(List<TcpConnectionModel> tcpSocketModels) {

		if (tcpSocketModels.size() > 0) {
			for (final TcpConnectionModel tcpSocketModel : tcpSocketModels) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							FplMbSocket connectionSocket = socketFactory
									.getSocket(tcpSocketModel.getSocketType());
							connectionSocket.init(tcpSocketModel);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, tcpSocketModel.getSocketName()).start();
			}
		}
	}

	private TcpConnectionModel createEntity(String host, int port, String socketType, String socketName) {
		TcpConnectionModel tcpModel = new TcpConnectionModel();
		tcpModel.setHost(host);
		tcpModel.setPort(port);
		tcpModel.setSocketType(socketType);
		tcpModel.setSocketName(socketName);
		return tcpModel;
	}

	private List<TcpConnectionModel> populateTcpClients() {
		// Load clients from DB
		tcpClientConnections.add(createEntity("localhost", 5678, "CLIENT", "AMHS-CLIENT"));
		logger.info("Registered client AMHS-CLIENT");
/*		
		tcpClientConnections.add(createEntity("1.1.1.1", 5678, "CLIENT", "AFTN-CLIENT"));
		logger.info("Registered client AFTN-CLIENT");
		
		tcpClientConnections.add(createEntity("localhost", 5678, "CLIENT", "FDPS-CLIENT"));
		logger.info("Registered client FDPS-CLIENT");*/

		return tcpClientConnections;
	}

	private List<TcpConnectionModel> populateTcpServers() {
		int port = 5678;
		// Load clients from DB
		tcpServerConnections.add(createEntity("localhost", port, "SERVER", "MGPS-SERVER"));
		logger.info("Registered MGPS-Server. Connectivity port is : " +port);
		
		return tcpServerConnections;
	}
}