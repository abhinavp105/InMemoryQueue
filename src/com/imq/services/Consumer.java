package com.imq.services;

import com.imq.pojo.Message;

public interface Consumer {

	public void receiveMessage(Message message);
	
	
}
