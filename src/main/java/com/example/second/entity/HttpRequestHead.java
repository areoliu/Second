package com.example.second.entity;

public class HttpRequestHead {
	private String token;
	private String lag;
	private String date;
	private String deviceNum;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLag() {
		return lag;
	}
	public void setLag(String lag) {
		this.lag = lag;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

}
