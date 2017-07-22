package com.imq.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import com.imq.pojo.Adjecency;
import com.imq.services.impl.ConsumerImpl;

public class TopologicalSortingUtil {

	public static LinkedList<ConsumerImpl> topologicalSort(ArrayList<LinkedList<ConsumerImpl>> consumerAcycGraph,
			HashMap<Integer, ConsumerImpl> mapOfConsumer) {

		HashSet<Integer> vistedConsumers = new HashSet<>();
		Stack<ConsumerImpl> stack = new Stack();
		int index = 0;
		for (LinkedList<ConsumerImpl> consumer : consumerAcycGraph) {

			topologicalSortUtil(consumerAcycGraph, vistedConsumers, stack, index, mapOfConsumer);
			index++;
		}

		return createConsumerList(stack);
	}

	public static LinkedList<ConsumerImpl> createConsumerList(Stack<ConsumerImpl> stack) {
		LinkedList<ConsumerImpl> consumerImpls = new LinkedList<>();
		while (!stack.isEmpty()) {
			consumerImpls.add(stack.pop());
		}
		return consumerImpls;
	}

	public static void topologicalSortUtil(ArrayList<LinkedList<ConsumerImpl>> consumerAcycGraph,
			HashSet<Integer> visitedConsumers, Stack<ConsumerImpl> stack, int index,
			HashMap<Integer, ConsumerImpl> mapOfConsumer) {

		if (!visitedConsumers.contains(index)) {
			visitedConsumers.add(index);

			LinkedList<ConsumerImpl> consumers = consumerAcycGraph.get(index);

			for (ConsumerImpl consumer : consumers) {

				topologicalSortUtil(consumerAcycGraph, visitedConsumers, stack, consumer.getConsumerId(),
						mapOfConsumer);
			}

			stack.push(mapOfConsumer.get(index));

		}
	}

}
