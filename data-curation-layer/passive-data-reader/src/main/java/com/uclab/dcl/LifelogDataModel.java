package com.uclab.dcl;

import java.util.ArrayList;
/**
 * processing the request from other Module
 * this module required for make result to JSON Format
 * @author CSHSC
 *
 */
public class LifelogDataModel {

	private final String data_id;
	private final String data_name;
	private final ArrayList<Object> data_attributes;
	private final ArrayList<Object> data_values;

	public LifelogDataModel(String data_id, String data_name, ArrayList<Object> data_attributes,
			ArrayList<Object> data_values) {
		this.data_id = data_id;
		this.data_name = data_name;
		this.data_attributes = data_attributes;
		this.data_values = data_values;
	}

	public String getData_id() {
		return data_id;
	}

	public String getData_name() {
		return data_name;
	}

	public ArrayList<Object> getData_attributes() {
		return data_attributes;
	}

	public ArrayList<Object> getData_values() {
		return data_values;
	}

}
