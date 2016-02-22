package com.gcaa.tcp.socket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.gcaa.tcp.socket.client.MgpsTcpClient;
import com.gcaa.tcp.socket.server.MgpsTcpServer;
import com.gcaa.utils.MGPSConstants;

@Component
public class MGPSSocketFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public MGPSConnectionSocket getSocket(String socketType) {
		MGPSConnectionSocket connectionSocket = null;

		if (socketType.equalsIgnoreCase(MGPSConstants.SOCKET_TYPE_CLIENT)) {
			connectionSocket = this.applicationContext.getBean(MgpsTcpClient.class);
		}

		if (socketType.equalsIgnoreCase(MGPSConstants.SOCKET_TYPE_SERVER)) {
			connectionSocket = this.applicationContext.getBean(MgpsTcpServer.class);
		}
		return connectionSocket;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.applicationContext = context;
	}

}
