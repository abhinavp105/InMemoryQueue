package com.imq.test;

import java.util.ArrayList;
import java.util.List;

import com.imq.factory.IMQueueFactory;
import com.imq.pojo.Message;
import com.imq.pojo.Producer;
import com.imq.services.InMemoryQueue;
import com.imq.services.impl.ConsumerImpl;

public class App {

	public static void main(String[] args) {

		InMemoryQueue inMemoryQueue = IMQueueFactory.createInstance(5);

		inMemoryQueue.registerConsumer(new ConsumerImpl(1, "abc"));
		inMemoryQueue.registerConsumerWithDependency(new ConsumerImpl(2, "abc"), new ConsumerImpl(1, ""));
		inMemoryQueue.registerConsumerWithDependency(new ConsumerImpl(3, "abc"), new ConsumerImpl(2, ""));

		Producer producer = new Producer("producer 123");
		List<Message> list = new ArrayList<>();

		list.add(new Message("message 1"));
		list.add(new Message("message 2"));
		list.add(new Message("message 3"));
		list.add(new Message("message 4"));
		list.add(new Message("message 5"));
		list.add(new Message("message 6"));

		producer.sendMessages(list, inMemoryQueue);
	}

}
