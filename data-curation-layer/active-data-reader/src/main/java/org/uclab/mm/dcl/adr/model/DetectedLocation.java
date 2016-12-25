/*
 * Copyright (C) 2016 Usman Akhtar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.uclab.mm.dcl.adr.model;

public class DetectedLocation {

	private int userdetectedlocationid;
	private int userid;
	private String locationlabel;
	private String starttime;
	private String endtime;
	private int duration;

	/**
	 * @return the userdetectedlocationid
	 */
	public int getUserdetectedlocationid() {
		return userdetectedlocationid;
	}

	/**
	 * @param userdetectedlocationid
	 *            the userdetectedlocationid to set
	 */
	public void setUserdetectedlocationid(int userdetectedlocationid) {
		this.userdetectedlocationid = userdetectedlocationid;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the locationlabel
	 */
	public String getLocationlabel() {
		return locationlabel;
	}

	/**
	 * @param locationlabel
	 *            the locationlabel to set
	 */
	public void setLocationlabel(String locationlabel) {
		this.locationlabel = locationlabel;
	}

	/**
	 * @return the starttime
	 */
	public String getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime
	 *            the starttime to set
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public String getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime
	 *            the endtime to set
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @param userdetectedlocationid
	 * @param userid
	 * @param locationlabel
	 * @param starttime
	 * @param endtime
	 * @param duration
	 */
	public DetectedLocation(int userdetectedlocationid, int userid, String locationlabel, String starttime,
			String endtime, int duration) {
		super();
		this.userdetectedlocationid = userdetectedlocationid;
		this.userid = userid;
		this.locationlabel = locationlabel;
		this.starttime = starttime;
		this.endtime = endtime;
		this.duration = duration;
	}

	/**
	 * Generate the constructor.
	 */
	public DetectedLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

}
