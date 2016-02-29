package com.gcaa.fplmb.tcp.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.gcaa.fplmb.model.ConnectionInterface;
import com.gcaa.fplmb.processor.MessageProcessor;

public abstract class FplMbSocket {

	@Autowired
	private MessageProcessor processor;
	
	
	private static final Logger LOGGER = Logger.getLogger(FplMbSocket.class);

	public abstract void init(ConnectionInterface tcpSocketModel) throws Exception;

    public void communicateToSocket(Socket remoteClientSocket){

    	try
    	{    		
	    	// Running continuously in-order to process Client Request
	        while (true)
	        {
	        	try{
		            // Fetches the Client request
	 	            String receivedData = receiveData(remoteClientSocket);
		            // Processing Client request
		            String clientData = doProcess(receivedData);
		            
		            processor.process(receivedData.getBytes());
		
		            // Sending Response to Client
/*		            sendData(remoteClientSocket, clientData.getBytes());*/
		            LOGGER.info("Response Message sent to Client = " + clientData);
	        	}catch (Exception e) {
	    			LOGGER.error(e.getMessage());
	    		}
	        }
    	}catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
    }

	/**
	 * Check for STX and ETX here And forward to DB and Output Channels
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public abstract String doProcess(String message);

	/**
	 * Method receives the incoming data
	 */
	public String receiveData(Socket connectedSocket) throws Exception {
		Scanner in = null;
		try {
			
			in = new Scanner( new BufferedInputStream( connectedSocket.getInputStream() ) );
			
			StringBuffer sb = new StringBuffer();
			
            while( in.hasNext())
            {
                String line = in.nextLine();
                sb.append(line);
                System.out.println(line);
                if(line.equalsIgnoreCase("exit")){
                	break;
                }
            }
			
			if(sb.length() > 0){
				return sb.toString();
			}
			
			return null;
			/*
			 * byte[] inputData = new byte[1024]; is.read(inputData); return
			 * inputData;
			 */
		} catch (Exception exception) {
			LOGGER.error(exception);
			throw exception;
		}
	}

	/**
	 * Method used to Send the data
	 */
	public void sendData(Socket remoteClient, byte[] byteData) throws Exception {
		DataOutputStream os = new DataOutputStream(new BufferedOutputStream(remoteClient.getOutputStream()));

		if (byteData == null) {
			return;
		}
		try {
			os.write(byteData);
			os.flush();
		} catch (Exception ex) {
			LOGGER.error(ex.getCause());
			LOGGER.error(ex.getMessage());
		}
	}

	public MessageProcessor getProcessor() {
		return processor;
	}
	
	

}
