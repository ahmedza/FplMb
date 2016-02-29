package com.gcaa.fplmb.tcp.socket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.tcp.socket.client.TcpClient;
import com.gcaa.fplmb.tcp.socket.server.MgpsTcpServer;
import com.gcaa.fplmb.utils.InterfacePubSubType;

@Component
public class SocketFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public FplMbSocket getSocket(String socketType) {
		FplMbSocket connectionSocket = null;

		if (socketType.equalsIgnoreCase(InterfacePubSubType.PUBLISHER.getCode())) {
			connectionSocket = this.applicationContext.getBean(TcpClient.class);
		}

		if (socketType.equalsIgnoreCase(InterfacePubSubType.SUBSCRIBER.getCode())) {
			connectionSocket = this.applicationContext.getBean(MgpsTcpServer.class);
		}
		return connectionSocket;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext = context;
	}

}
