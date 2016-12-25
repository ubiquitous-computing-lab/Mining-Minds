package org.uclab.mm.icl.llc.AER.vui;

public class OutputAdapter {
	private String userId;
	private String deviceId;
	private String timestamp;
	private String label;
	private String processedData;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getProcessedData() {
		return processedData;
	}
	
	public void attachMetadata() {
		processedData = String.format("{\"user_id\":\"%s\",\"device_id\":\"%s\",\"timestamp\":\"%s\",\"label\":\"%s\"}", userId, deviceId, timestamp, label);
	}
}
