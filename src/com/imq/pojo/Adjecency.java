package com.imq.pojo;

import java.util.LinkedList;

import com.imq.services.impl.ConsumerImpl;

public class Adjecency {

	private ConsumerImpl consumerImpl;

	private LinkedList<ConsumerImpl> consumerImpls = new LinkedList<>();

	public Adjecency(ConsumerImpl consumerImpl, LinkedList<ConsumerImpl> consumerImpls) {
		super();
		this.consumerImpl = consumerImpl;
		this.consumerImpls = consumerImpls;
	}

	public ConsumerImpl getConsumerImpl() {
		return consumerImpl;
	}

	public void setConsumerImpl(ConsumerImpl consumerImpl) {
		this.consumerImpl = consumerImpl;
	}

	public LinkedList<ConsumerImpl> getConsumerImpls() {
		return consumerImpls;
	}

	public void setConsumerImpls(LinkedList<ConsumerImpl> consumerImpls) {
		this.consumerImpls = consumerImpls;
	}

}
