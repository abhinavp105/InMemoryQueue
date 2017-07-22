package com.imq.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import com.imq.pojo.Message;
import com.imq.services.InMemoryQueue;
import com.imq.util.TopologicalSortingUtil;

public class InMemoryQueueImpl implements InMemoryQueue {

	private BlockingQueue<Message> inMemoryQueue;
	private HashMap<Integer, ConsumerImpl> mapOfConsumer = new HashMap<Integer, ConsumerImpl>();
	private ArrayList<LinkedList<ConsumerImpl>> consumerAcycGraph = new ArrayList<>();

	private LinkedList<ConsumerImpl> consumerListAfterSort = new LinkedList<>();

	private int sizeOfQueue;

	public InMemoryQueueImpl(int size) {
		this.sizeOfQueue = size;
		inMemoryQueue = new LinkedBlockingQueue<>(size);
	}

	@Override
	public boolean registerConsumer(ConsumerImpl consumerImpl) {

		mapOfConsumer.put(consumerImpl.getConsumerId()-1, consumerImpl);
		consumerListAfterSort.add(consumerImpl);
		consumerAcycGraph.add(new LinkedList<ConsumerImpl>());
		return true;
	}

	@Override
	public boolean registerConsumerWithDependency(ConsumerImpl consumerToBeAdded,
			ConsumerImpl consumerToBeDependentOn) {

		if (!mapOfConsumer.containsKey(consumerToBeDependentOn.getConsumerId()-1)) {
			System.out.println("consumerToBeDependentOn is not already added");
			return false;
		}
		if (mapOfConsumer.containsKey(consumerToBeAdded.getConsumerId())) {
			LinkedList<ConsumerImpl> consumerImpls = consumerAcycGraph.get(consumerToBeAdded.getConsumerId() - 1);
			mapOfConsumer.put(consumerToBeAdded.getConsumerId()-1, consumerToBeAdded);
			consumerImpls.add(mapOfConsumer.get(consumerToBeDependentOn.getConsumerId()));
		} else {
			LinkedList<ConsumerImpl> consumerImpls = new LinkedList<>();
			consumerImpls.add(consumerToBeDependentOn);
			mapOfConsumer.put(consumerToBeAdded.getConsumerId()-1, consumerToBeAdded);
			consumerAcycGraph.add(consumerImpls);
		}

		consumerListAfterSort = TopologicalSortingUtil.topologicalSort(consumerAcycGraph, mapOfConsumer);

		return true;
	}

	@Override
	public boolean addMessageToQueue(Message message) {

		synchronized (inMemoryQueue) {

			try {
				while (true) {
					if (isQueueFull()) {

						if (isQueueFull()) {
							Thread.sleep(1000);
						}
					} else {

						break;
					}

				}

				inMemoryQueue.add(message);

				informConsumers(inMemoryQueue);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private void informConsumers(BlockingQueue<Message> blockingQueue) {
		synchronized (blockingQueue) {
			int count = blockingQueue.size();

			while (count > 0)

				for (ConsumerImpl consumer : consumerListAfterSort) {
					Message message = blockingQueue.poll();
					consumer.receiveMessage(message);
					count--;

					if (inMemoryQueue.isEmpty())
						return;
				}
		}

	}

	private boolean isQueueFull() {
		if (inMemoryQueue.size() == sizeOfQueue)
			return true;
		else
			return false;
	}
}
