package com.imq.factory;

import com.imq.services.InMemoryQueue;
import com.imq.services.impl.InMemoryQueueImpl;

public abstract class IMQueueFactory {

	public static InMemoryQueue createInstance(int sizeOfQueue) {

		return new InMemoryQueueImpl(sizeOfQueue);

	}

}
