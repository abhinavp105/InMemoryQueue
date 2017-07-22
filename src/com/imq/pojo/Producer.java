package com.imq.pojo;

import java.util.List;

import com.imq.services.InMemoryQueue;

public class Producer {

	private String name;

	public Producer(String name) {
		super();
		this.name = name;
	}

	public void sendMessages(List<Message> messages, InMemoryQueue inMemoryQueue) {

		for (Message message : messages) {

			inMemoryQueue.addMessageToQueue(message);

		}

	}

}
