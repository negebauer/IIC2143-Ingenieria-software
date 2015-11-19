package org.parse4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parse4j.command.ParsePostCommand;
import org.parse4j.command.ParseResponse;

public class ParsePush {

	private List<String> channelSet = null;
	// private ParseQuery<ParseInstallation> query = null;
	private Date expirationTime = null;
	private Date pushTime = null;
	private Long expirationTimeInterval = null;
	@SuppressWarnings("unused")
	private Boolean pushToIOS = null;
	@SuppressWarnings("unused")
	private Boolean pushToAndroid = null;
	private JSONObject pushData = new JSONObject();

	public void setChannel(String channel) {
		this.channelSet = new ArrayList<String>();
		this.channelSet.add(channel);
	}

	public void setChannels(Collection<String> channels) {
		this.channelSet = new ArrayList<String>();
		this.channelSet.addAll(channels);
	}

	public void setPushTime(Date time) {
		this.pushTime = time;
	}	

	public void setExpirationTime(Date time) {
		this.expirationTime = time;
		this.expirationTimeInterval = null;
	}

	public void setExpirationTimeInterval(long timeInterval) {
		this.expirationTime = null;
		this.expirationTimeInterval = Long.valueOf(timeInterval);
	}

	public void clearExpiration() {
		this.expirationTime = null;
		this.expirationTimeInterval = null;
	}

	public void setMessage(String message) {
		try {
			this.pushData.put("alert", message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setBadge(String badge) {
		if(badge == null || badge.length() == 0) {
			badge = "Increment";
		}
		try {
			this.pushData.put("badge", badge);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setSound(String sound) {
		try {
			this.pushData.put("sound", sound);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setTitle(String title) {
		try {
			this.pushData.put("title", title);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setData(String key, String value) {
		try {
			this.pushData.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void send() throws ParseException, JSONException {
		ParsePostCommand command = new ParsePostCommand("push");
		JSONObject requestData = getJSONData();
		command.setData(requestData);
		ParseResponse response = command.perform();
		if(response.isFailed()) {
			throw response.getException();
		}	
	}	

	public void sendInBackground(String message, List<String> channels) {
		SendPushInBackgroundThread event = new SendPushInBackgroundThread();
		ParseExecutor.runInBackground(event);
	}

	class SendPushInBackgroundThread extends Thread {

		public void run() {
			try {
				send();
			} catch (ParseException e) {
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	private JSONObject getJSONData() {
		JSONObject data = new JSONObject();
		try {
			data.put("data", this.pushData);

			if (this.channelSet == null) {
				data.put("channel", "");
			} else {
				data.put("channels", new JSONArray(this.channelSet));
			}

			if(pushTime != null) {
				data.put("push_time", Parse.encodeDate(pushTime));
			}

			if(expirationTimeInterval != null) {
				data.put("expiration_interval", expirationTimeInterval);
			}

			if(expirationTime != null) {
				data.put("expiration_time", expirationTime);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return data;

	}

}
