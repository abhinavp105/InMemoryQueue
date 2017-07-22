package com.imq.services.impl;

import com.imq.pojo.Message;

public class ConsumerImpl {

	private int consumerId;
	private String expression;

	public ConsumerImpl() {
		super();
	}

	public ConsumerImpl(int consumerId, String expression) {
		super();
		this.consumerId = consumerId;
		this.expression = expression;
	}

	public void receiveMessage(Message message) {
		System.out.println("Received message : " + message.getMessage() + " By consumer id " + consumerId);

	}

	public int getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "ConsumerImpl [consumerId=" + consumerId + ", expression=" + expression + "]";
	}

}
