package com.gcaa.fplmb.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gcaa.fplmb.entity.AftnMessageEntity;
import com.gcaa.fplmb.entity.MessageQueueEntity;
import com.gcaa.fplmb.model.IcaoAftnMessage;
import com.gcaa.fplmb.parser.AmhsMessageParser;
import com.gcaa.fplmb.repository.QueueRepository;
import com.gcaa.fplmb.repository.impl.FplmbRepository;
import com.gcaa.fplmb.utils.DeliveryStatus;

@Component
@Scope("prototype")
public class MessageProcessor {

/*	@Autowired
	private AftnMsgRepository aftnRepoistory;*/

	@Autowired
	private QueueRepository queueRepoistory;

	@Autowired
	private FplmbRepository fplRepo;

	@Autowired
	private AmhsMessageParser parser;

	/*
	 * @Autowired private PublishingManager jmsManager;
	 */

	public void process(byte[] messageBbytes) {
		// Analyze Message Type
		// Parser it according to the type
		// Store IT in DB.
		// Create a DB queue for outgoing connections
		// Out to Q conditional
		// Out to Output sockets conditional

		IcaoAftnMessage msgModel = parser.parse(messageBbytes);
		AftnMessageEntity msgEntity = saveMessage(msgModel);
		sendToQueue(msgEntity);
		/*
		 * jmsManager.sendMessage(null, new IcaoAftnMessage(), new
		 * String(messageBbytes));
		 */
	}

	private void sendToQueue(AftnMessageEntity msgEntity) {
		// TODO Auto-generated method stub
		MessageQueueEntity mq = new MessageQueueEntity();
		/* AftnMessageEntity message = new AftnMessageEntity(); */
		mq.setMessage(msgEntity);
		mq.setDeliveryStatus(DeliveryStatus.UN_DELIVERED.getCode());
		mq.setRetryCount(0l);

		queueRepoistory.save(mq);
	}

	private AftnMessageEntity saveMessage(IcaoAftnMessage model) {
		AftnMessageEntity entity = new AftnMessageEntity();
		entity.setRaw_Msg(new String(model.getRaw_Msg()));

		// aftnRepoistory.save(entity);
		return fplRepo.saveAftnMessage(entity);
	}

	public void deserializeMessage() {
		// getSerializer().deserialize(inputStream);
	}

}