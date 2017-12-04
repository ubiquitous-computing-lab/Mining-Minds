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

/**
 * Data Model of the Detected Location table. 
 *
 * @author Usman Akhtar
 */
package org.uclab.mm.dcl.adr.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Here you define the default table schema. based on the data schema we have
 * define the data id, name, attributes and values.
 */

public class SLDataModel {

	private final String data_id;
	private final String data_name;
	private final ArrayList<Object> data_attributes;
	private final LinkedHashMap<String, String> data_values;

	/**
	 * Constructs an empty list with an initial capacity of ten.
	 *
	 * @param data_id the data id
	 * @param data_name the data name
	 * @param data_attributes the data attributes
	 * @param data_values the data values
	 */
	public SLDataModel(String data_id, String data_name, ArrayList<Object> data_attributes,
			LinkedHashMap<String, String> data_values) {
		this.data_id = data_id;
		this.data_name = data_name;
		this.data_attributes = data_attributes;
		this.data_values = data_values;
	}

	/**
	 * Gets the data id.
	 *
	 * @return the data_id of the table stored in Aapche Hive
	 */
	public final String getData_id() {
		return data_id;
	}

	/**
	 * @return the data_name
	 */
	public final String getData_name() {
		return data_name;
	}

	/**
	 * @return the data_attributes
	 */
	public final ArrayList<Object> getData_attributes() {
		return data_attributes;
	}

	/**
	 * @return the data_values
	 */
	public final LinkedHashMap<String, String> getData_values() {
		return data_values;
	}

	

}