package com.example.second.entity;

public class HttpRequest {
	
	private HttpRequestHead requestHead;
	private HttpRequestBody requestBody;
	public HttpRequestHead getRequestHead() {
		return requestHead;
	}
	public void setRequestHead(HttpRequestHead requestHead) {
		this.requestHead = requestHead;
	}
	public HttpRequestBody getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(HttpRequestBody requestBody) {
		this.requestBody = requestBody;
	}
	

}
