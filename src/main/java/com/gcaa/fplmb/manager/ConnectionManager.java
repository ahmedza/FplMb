package com.gcaa.fplmb.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Service;

import com.gcaa.fplmb.entity.ConnectionInterfaceEntity;
import com.gcaa.fplmb.model.ConnectionInterface;
import com.gcaa.fplmb.repository.ConnectionRepository;
import com.gcaa.fplmb.utils.InterfaceType;

@Service
public class ConnectionManager {

	Map<String, List<ConnectionInterface>> connectionsMap = new HashMap<String, List<ConnectionInterface>>();
	@Autowired
	private ConnectionRepository connRepo;

	@Autowired
	private TcpSocketManager tcpManager;
	
	private static final Logger logger = Logger.getLogger(ConnectionManager.class);

	@PostConstruct
	public void initInterfaceConnections() {

		connectionsMap = getAllConnections();

		if (null == connectionsMap || connectionsMap.size() == 0) {
			logger.info("Interface conneciton configuration not available.");
		}
		
		tcpManager.initializeSockets(connectionsMap.get(InterfaceType.TCP.getCode()));
		
		System.out.println("hello Workd");
		getJmsConnectionManager();
	}

	public ConnectionFactory getJmsConnectionManager(){
		ConnectionFactory jmsConnFactory = null;
		Properties jndiEnvironment = new Properties();
/*		jndiEnvironment.setProperty("cache"				, "true");
		jndiEnvironment.setProperty("lookUponStartup"	, "false");
		jndiEnvironment.setProperty("proxyInterface"	, "javax.jms.ConnectionFactory");*/
/*		jndiEnvironment.setProperty("jndiName"			, "java:comp/env/jms/fplmb.fdps.out.");//"java:comp/env/jms/myCF");
*/		jndiEnvironment.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		jndiEnvironment.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiEnvironment.setProperty(Context.SECURITY_PRINCIPAL, "admin");
		jndiEnvironment.setProperty(Context.SECURITY_CREDENTIALS, "admin");
		jndiEnvironment.setProperty("queue.fplmb","example.fplmb");

		JndiObjectFactoryBean jndiFactory = new JndiObjectFactoryBean();
		jndiFactory.setJndiEnvironment(jndiEnvironment);
		
		jndiFactory.setJndiName("fplmb");
		jndiFactory.setCache(true);
		jndiFactory.setLookupOnStartup(true);
		/*jndiFactory.setProxyInterface(ConnectionFactory.class);*/
		jndiFactory.setExpectedType(javax.jms.Destination.class);
		try {
			jndiFactory.afterPropertiesSet();
		} catch (IllegalArgumentException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object connObject = jndiFactory.getObject();

		return jmsConnFactory;
	}
	
	public Map<String, List<ConnectionInterface>> getAllConnections() {

		Map<String, List<ConnectionInterface>> connMap = null;
		List<ConnectionInterface> conns = new ArrayList<ConnectionInterface>();

		Iterable<ConnectionInterfaceEntity> connEntities = connRepo.findAll();

		if (connEntities == null) {
			return connMap;
		}

		for (ConnectionInterfaceEntity connEntity : connEntities) {
			ConnectionInterface conn = new ConnectionInterface();
			BeanUtils.copyProperties(connEntity, conn);
			conns.add(conn);
		}

		return filterConnections(conns);
	}

	private Map<String, List<ConnectionInterface>> filterConnections(List<ConnectionInterface> conns) {

		Map<String, List<ConnectionInterface>> connMap = new HashMap<String, List<ConnectionInterface>>();

		List<ConnectionInterface> tcpConns;
		List<ConnectionInterface> jmsConns;
		List<ConnectionInterface> soapConns;
		List<ConnectionInterface> restConns;

		tcpConns = conns.stream().filter(conn -> conn.getInterfaceType().equals(InterfaceType.TCP.getCode()))
				.collect(Collectors.toList());
		jmsConns = conns.stream().filter(conn -> conn.getInterfaceType().equals(InterfaceType.JMS.getCode()))
				.collect(Collectors.toList());
		soapConns = conns.stream().filter(conn -> conn.getInterfaceType().equals(InterfaceType.SOAP.getCode()))
				.collect(Collectors.toList());
		restConns = conns.stream().filter(conn -> conn.getInterfaceType().equals(InterfaceType.REST.getCode()))
				.collect(Collectors.toList());

		if (null != tcpConns || !tcpConns.isEmpty()) {
			connMap.put(InterfaceType.TCP.getCode(), tcpConns);
		}
		if (null != jmsConns || !jmsConns.isEmpty()) {
			connMap.put(InterfaceType.JMS.getCode(), jmsConns);
		}
		if (null != soapConns || !soapConns.isEmpty()) {
			connMap.put(InterfaceType.SOAP.getCode(), soapConns);
		}
		if (null != restConns || !restConns.isEmpty()) {
			connMap.put(InterfaceType.REST.getCode(), restConns);
		}
		return connMap;

	}

}
