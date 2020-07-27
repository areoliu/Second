package com.example.second.entity;

public class HttpResponse {
	
	private HttpResponseHead responseHead;
	private HttpResponseBody responseBody;
	public HttpResponseHead getResponseHead() {
		return responseHead;
	}
	public void setResponseHead(HttpResponseHead responseHead) {
		this.responseHead = responseHead;
	}
	public HttpResponseBody getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(HttpResponseBody responseBody) {
		this.responseBody = responseBody;
	}
	

}
