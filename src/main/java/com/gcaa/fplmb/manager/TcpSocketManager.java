package com.gcaa.fplmb.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.model.ConnectionInterface;
import com.gcaa.fplmb.tcp.socket.FplMbSocket;
import com.gcaa.fplmb.tcp.socket.SocketFactory;

@Component
public class TcpSocketManager {

	@Autowired
	private SocketFactory socketFactory;

	private static final Logger logger = Logger.getLogger(TcpSocketManager.class);

	public void initializeSockets(List<ConnectionInterface> tcpSocketModels) {
		if (tcpSocketModels.size() > 0) {
			for (final ConnectionInterface tcpSocketModel : tcpSocketModels) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							FplMbSocket connectionSocket = socketFactory
									.getSocket(tcpSocketModel.getPubSubType());
							connectionSocket.init(tcpSocketModel);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, tcpSocketModel.getInterfaceName()).start();
			}
		}
	}

	/*	private List<TcpConnectionModel> tcpClientConnections = new ArrayList<TcpConnectionModel>();
	private List<TcpConnectionModel> tcpServerConnections = new ArrayList<TcpConnectionModel>();*/
	
	/*	@PostConstruct
	public void initiateServerClient() {
		populateTcpClients();
		populateTcpServers();

		initializeSockets(tcpServerConnections);
		initializeSockets(tcpClientConnections);
	}*/
	
/*	private TcpConnectionModel createEntity(String host, int port, String socketType, String socketName) {
		TcpConnectionModel tcpModel = new TcpConnectionModel();
		tcpModel.setHost(host);
		tcpModel.setPort(port);
		tcpModel.setSocketType(socketType);
		tcpModel.setSocketName(socketName);
		return tcpModel;
	}*/

/*	private List<TcpConnectionModel> populateTcpClients() {
		// Load clients from DB
		tcpClientConnections.add(createEntity("localhost", 5678, "CLIENT", "AMHS-CLIENT"));
		logger.info("Registered client AMHS-CLIENT");
		
		tcpClientConnections.add(createEntity("1.1.1.1", 5678, "CLIENT", "AFTN-CLIENT"));
		logger.info("Registered client AFTN-CLIENT");
		
		tcpClientConnections.add(createEntity("localhost", 5678, "CLIENT", "FDPS-CLIENT"));
		logger.info("Registered client FDPS-CLIENT");

		return tcpClientConnections;
	}*/

/*	private List<TcpConnectionModel> populateTcpServers() {
		int port = 5678;
		// Load clients from DB
		tcpServerConnections.add(createEntity("localhost", port, "SERVER", "MGPS-SERVER"));
		logger.info("Registered MGPS-Server. Connectivity port is : " +port);
		
		return tcpServerConnections;
	}*/
}