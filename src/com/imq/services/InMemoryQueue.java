package com.imq.services;

import com.imq.pojo.Message;
import com.imq.services.impl.ConsumerImpl;

public interface InMemoryQueue {

	public boolean registerConsumer(ConsumerImpl consumer);
	public boolean registerConsumerWithDependency(ConsumerImpl consumerToBeAdded,ConsumerImpl consumerToBeDependentOn);
	public boolean addMessageToQueue(Message message);
	
}
