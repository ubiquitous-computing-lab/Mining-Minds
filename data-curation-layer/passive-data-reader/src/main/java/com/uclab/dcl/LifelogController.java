package com.uclab.dcl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * Copyright [2016] [name of copyright owner <-Put your name]
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 * 
 * 
 * main controller
 * connecting HDFS and HIVE
 * recieve query from other components
 * root dir : dcl
 * main RequestMapping : /lifelog
 * 
 * @author CSHSC
 *
 */
@Controller
@RequestMapping("/lifelog")
public class LifelogController {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	
	private ArrayList<String> columns;
	private ArrayList<String> tables;

	@RequestMapping(value = "/schema", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LifelogSchemaModel schema() throws Exception {

		// TODO get real value

		Class.forName(driverName);

		System.out.println("schema infomation\n");

		Connection con = DriverManager.getConnection("jdbc:hive2://163.180.116.94:10000/test1", "hive", "hive");
		Statement stmt = con.createStatement();
		String tableName = "userinfo";
		String sql;
		ResultSet res;
		sql = "describe " + tableName;
		// System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		System.out.print("TableName : "+tableName+"\n\n");
		ArrayList<String[]> coldes = new ArrayList<String[]>();
		int i = 0;
		while (res.next()) {
			String[] col = new String[2];
			col[0] = res.getString(1);
			col[1] = res.getString(2);
			System.out.println("ColumnName : "+res.getString(1)+"(Type : "+res.getString(2)+")");
			coldes.add(col);
			// System.out.println(res.getString(1) + "\t" + res.getString(2));
			i++;
		}
		String schema_id = "s1";
		String schema_name = "User Schema";
		ArrayList<Object> schema_tables = new ArrayList<Object>();
		// TODO implementation of connection DB and store to object
		// 1st table----------------------------------------------
		{
			LinkedHashMap<String, Object> tables = new LinkedHashMap<String, Object>();
			tables.put("table_id", (String) "t1-s1");
			tables.put("table_name", tableName);
			// table_metadata
			ArrayList<LinkedHashMap<String, String>> table_metadata = new ArrayList<LinkedHashMap<String, String>>();

			for (int j = 0; j < coldes.size(); j++) {
				LinkedHashMap<String, String> metadata = new LinkedHashMap<String, String>();
				metadata.put("column_name", coldes.get(j)[0]);
				metadata.put("datatype", "(" + coldes.get(j)[1] + ")");
				table_metadata.add(metadata);
			}
			tables.put("table_metadata", (ArrayList<LinkedHashMap<String, String>>) table_metadata);
			schema_tables.add(tables);
		}
		// 2nd table -----------------------------------------------------
		tableName = "useractivity";
		sql = "describe " + tableName;
		res = stmt.executeQuery(sql);
		ArrayList<String[]> coldes1 = new ArrayList<String[]>();
		System.out.print("\nTableName : "+tableName+"\n\n");
		int i1 = 0;
		while (res.next()) {
			String[] col = new String[2];
			col[0] = res.getString(1);
			col[1] = res.getString(2);
			System.out.println("ColumnName : "+res.getString(1)+"(Type : "+res.getString(2)+")");
			coldes1.add(col);
			// System.out.println(res.getString(1) + "\t" + res.getString(2));
			i1++;
		}
		System.out.println("Condition : age > 20");
		{
			LinkedHashMap<String, Object> tables = new LinkedHashMap<String, Object>();
			tables.put("table_id", (String) "t2-s1");
			tables.put("table_name", (String) "useractivity");
			// table_metadata
			ArrayList<LinkedHashMap<String, String>> table_metadata = new ArrayList<LinkedHashMap<String, String>>();
			for (int j = 1; j < coldes1.size(); j++) {
				LinkedHashMap<String, String> metadata = new LinkedHashMap<String, String>();
				metadata.put("column_name", coldes1.get(j)[0]);
				metadata.put("datatype", "(" + coldes1.get(j)[1] + ")");
				table_metadata.add(metadata);
			}
			tables.put("table_metadata", (ArrayList<LinkedHashMap<String, String>>) table_metadata);
			schema_tables.add(tables);
		}

		return new LifelogSchemaModel(schema_id, schema_name, schema_tables);
	}
	
	@RequestMapping("/data")
	public @ResponseBody LifelogDataModel data(@RequestParam(value="query", required=true) String query) throws Exception {
		System.out.println("Receving the Query From KCL");
		this.tables = new ArrayList<String>();
		this.columns = new ArrayList<String>();
		System.out.println(query);
		Class.forName(driverName);
		String sql = parsingQuery(query);
		//bodymassindex 0.1  diabetespedigreefunction 0.123
		System.out.println("Selected Table   by Request : "+this.tables.toString());
		System.out.println("Selected Columns by Request : "+this.columns.toString());
		System.out.println("Condition : age > 20");
		System.out.println("Searching Requested Value....");
		Connection con = DriverManager.getConnection("jdbc:hive2://163.180.116.94:10000/test1", "hive", "hive");
		java.sql.PreparedStatement stmte =null;
		ResultSet res;
		ResultSetMetaData rsmd;
		stmte= con.prepareStatement(sql);
		res =stmte.executeQuery();
		rsmd = res.getMetaData();
		int colcount = rsmd.getColumnCount();
		System.out.println(colcount+"columns Selected");
		ArrayList<Object> data_attributes = new ArrayList<Object>();
		ArrayList<Object> data_values = new ArrayList<Object>();

		System.out.println("\n\n------The Requested Result Sent to KCL------");
		for(int j=1; j<=colcount; j++ ){
			
//			System.out.print(rsmd.getColumnLabel(j)+" : ");
//		System.out.println(res.getString(j));
		{
			LinkedHashMap<String, String> attributes = new LinkedHashMap<String, String>();
			String attribute_name = rsmd.getColumnLabel(j).split("\\.")[1];
			System.out.print(String.format("%-35s",("|"+rsmd.getColumnLabel(j).split("\\.")[1]+"("+rsmd.getColumnTypeName(j)+")")));
			attributes.put("attribute_name", attribute_name);
			String attribute_type = rsmd.getColumnTypeName(j);
			attributes.put("Attribute_type", attribute_type);
			data_attributes.add(attributes);
		}

		}
		System.out.println("");
		while(res.next()){

			{
				ArrayList<Object> values = new ArrayList<Object>();
				for(int j = 1; j <=colcount; j++)
				{
				//values.add(res.getString(j));
					System.out.print(String.format("%-35s", "|"+adjustNumber(rsmd.getColumnLabel(j).split("\\.")[1], res.getString(j))));
				values.add(adjustNumber(rsmd.getColumnLabel(j).split("\\.")[1], res.getString(j)));
				}
				data_values.add(values);
			}
			System.out.println("");
			
		}
		
		
		//TODO get real value
		String data_id = "d1";
		String data_name = "Diabetes Diagnostic Record of Females";
		return new LifelogDataModel(data_id, data_name, data_attributes, data_values);
	}
	
	
	
	@RequestMapping("/test")
	public @ResponseBody LifelogDataModel data() throws Exception {
		return null;
	}

	private String parsingQuery(String query) throws Exception {
		query = query.toLowerCase();
//		System.out.println(query);
		JSONArray ary = new JSONArray(query);
		ArrayList<String[]> line = new ArrayList<String[]>();
		for(int i = 0; i < ary.length(); i++) {
			JSONObject obj = ary.getJSONObject(i);
			String row[] = {obj.get("table").toString(), obj.get("column").toString(), obj.get("condition").toString()};
			line.add(row);
		}
		
		String sqlSelect = "SELECT";
		String sqlFrom = " FROM";
		String sqlWhere = " WHERE";
		String table = "";
		for(int i = 0; i < line.size(); i++) {
			// not same table case
			if(!table.equals(line.get(i)[0])) {
				tables.add(line.get(i)[0]);
				if(!table.equals("")) {
					if(!sqlWhere.equals(" WHERE")) {
						sqlWhere += " AND";
					}
					sqlWhere += " " + table + ".user_id = " + line.get(i)[0] + ".user_id";
				}
				table = line.get(i)[0];
				if(!sqlFrom.equals(" FROM")) {
					sqlFrom += ",";
				}
				sqlFrom += " " + line.get(i)[0];
			}
			
			if(!sqlSelect.equals("SELECT")) {
				sqlSelect += ",";
			}
				sqlSelect += " " + table + "." + line.get(i)[1];
				columns.add(line.get(i)[1]);
			
			if(!line.get(i)[2].equals("")) {
				if(!sqlWhere.equals(" WHERE")) {
					sqlWhere += " AND";
				}
				sqlWhere += " " + line.get(i)[0] + "." + line.get(i)[1] + line.get(i)[2];
			}
			
		}
		
		String sql = sqlSelect + sqlFrom + sqlWhere;
		
		System.out.println("Requested Query : "+sql);
		
		return sql;
	}
	
	private String adjustNumber(String column, String data) {
		if(column.equals("body_mass_index")) {
			return String.format("%.1f", Float.valueOf(data));
		}
		if(column.equals("diabetes_pedigree_function")) {
			return String.format("%.3f", Float.valueOf(data));
		}
		return data;
	}



}
