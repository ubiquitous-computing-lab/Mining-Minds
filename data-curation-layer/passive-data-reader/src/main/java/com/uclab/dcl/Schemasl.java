package com.uclab.dcl;
import java.util.ArrayList;
/**
 * this module is test module
 * you can test the schema of table using this module
 * @author CSHSC
 *
 */
public class Schemasl {

	private final String schema_id;
	private final String schema_name;
	private final ArrayList<Object> schema_tables;

	public Schemasl(String schema_id, String schema_name, ArrayList<Object> schema_tables) {
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