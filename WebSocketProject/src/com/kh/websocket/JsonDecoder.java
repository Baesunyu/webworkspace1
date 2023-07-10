package com.kh.websocket;

import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

public class JsonDecoder implements Text<Message>{

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init(EndpointConfig arg0) {
		
	}
	
	@Override
	public boolean willDecode(String msg) {
		return true;
	}
	
	@Override
	public Message decode(String msg) {
		return new Gson().fromJson(msg, Message.class);
	}
	
	
	
	
	
	
	
}
