package com.uclab.dcl;

import java.util.ArrayList;
/**
 * Make a schema of table in the HIVE
 * @author CSHSC
 *
 */
public class LifelogSchemaModel {

	private final String schema_id;
	private final String schema_name;
	private final ArrayList<Object> schema_tables;

	public LifelogSchemaModel(String schema_id, String schema_name, ArrayList<Object> schema_tables) {
		this.schema_id = schema_id;
		this.schema_name = schema_name;
		this.schema_tables = schema_tables;
	}

	public String getSchema_id() {
		return schema_id;
	}

	public String getSchema_name() {
		return schema_name;
	}

	public ArrayList<Object> getSchema_tables() {
		return schema_tables;
	}

}
