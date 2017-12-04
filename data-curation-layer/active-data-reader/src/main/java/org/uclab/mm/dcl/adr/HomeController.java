/*
 * Copyright (C) 2016 Usman Akhtar.
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
 * Home Controller are responsible for mapping
 *  the the HTTP request with the Methods. 
  */
package org.uclab.mm.dcl.adr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uclab.mm.dcl.adr.model.SLDataModel;

// TODO: Auto-generated Javadoc
/**
 * Handles requests mapping to data access object with the service.
 * 
 * Indicates that an annotated class is a "Controller" (e.g. a web controller).
 * 
 * @Controller annotation indicates that a particular class serves the role of a
 *             controller
 */
@Controller
public class HomeController {

	/**
	 * LoggerFactory: Return a logger named corresponding to the class passed as
	 * parameter, In case the class parameter differs from the name of the
	 * caller as computed internally by SLF4J, a logger name mismatch warning
	 * will be printed but only if the slf4j.detectLoggerNameMismatch system
	 * property is set to true.
	 */
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * If the org.apache.hive.jdbc.HiveDriver does not recognize the check the
	 * pom.xml. We have added the Apache Hive version 1.2.1.
	 */
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	/**
	 * Simply selects the home view to render by returning its name.
	 *
	 * @param locale
	 *            the locale
	 * @param model
	 *            the model
	 * @return the string
	 * 
	 * @RequestMapping: Annotation for mapping web requests onto specific
	 *                  handler classes and/or handler methods. Purpose: The
	 *                  main purpose of this method is to return the display on
	 *                  the home page and it serve as a static HomePage.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/**
	 * Location.
	 *
	 * @param userid the userid
	 * @return This method will return the location count that are stored in the
	 *         Apache Hive
	 * @throws SQLException the SQL exception
	 * @RequestMapping: Annotation for mapping web requests onto location method
	 *                  Usage: after starting the service goto the Rest Client
	 *                  and type www.localhost:8080/analytics/location?userid=0.
	 *                  This serves as a Get Request.
	 */
	@RequestMapping(value = "/location")
	public @ResponseBody SLDataModel location(@RequestParam(value = "userid") String userid) throws SQLException {

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		/**
		 * The main connection String. We have added the JDBC connection setting
		 * inside spring.xml. Just replace the JDBC url with your own setting.
		 * You must enable the thrift Apache Hive post to interact with.
		 */
		Connection con = DriverManager.getConnection("jdbc:hive2://163.180.116.94:10000/test1", "hive", "hive");
		Statement stmt = con.createStatement();
		String tableName = "detectedlocation1";
		String sql = "show tables '" + tableName + "'";
		String data_name = "SCLData";
		String data_id = "1";
		System.out.println("Running: " + sql);
		ResultSet res = stmt.executeQuery(sql);
		ResultSetMetaData rsmd;

		if (res.next()) {
			System.out.println(res.getString(1));
		}

		ArrayList<Object> data_attributes = new ArrayList<Object>();
		ArrayList<Object> data_values = new ArrayList<Object>();
		LinkedHashMap<String, String> data_array = new LinkedHashMap<String, String>();

		/**
		 * This service will map the user id. Get the Id = 0 and calculate the
		 * number of label from the Big Data Storage.
		 */

		if (userid.equals("0")) {
			sql = "select * from detectedlocation1";
			res = stmt.executeQuery(sql);

			int home = 0;
			int office = 0;
			int yard = 0;
			int gym = 0;
			int mall = 0;
			int restaurant = 0;
			int outdoors = 0;
			int transport = 0;
			LinkedHashMap<String, String> attributes = new LinkedHashMap<String, String>();

			attributes.put("attribute_name", "Count of Location");
			data_attributes.add(attributes);
			while (res.next()) {
				if (res.getString(3).equals("Home")) {
					home++;
				} else if (res.getString(3).equals("Office")) {
					office++;
				} else if (res.getString(3).equals("Yard")) {
					yard++;
				} else if (res.getString(3).equals("Gym")) {
					gym++;
				} else if (res.getString(3).equals("Mall")) {
					mall++;
				} else if (res.getString(3).equals("Restaurant")) {
					restaurant++;
				} else if (res.getString(3).equals("Outdoors")) {
					outdoors++;
				} else if (res.getString(3).equals("Transport")) {
					transport++;
				}
			}

			String home_count = "Home:" + home;
			String office_count = "Office:" + office;
			String yard_count = "Yard:" + yard;
			String gym_count = "Gym:" + gym;
			String mall_count = "Mall:" + mall;
			String restaurant_count = "Restaurant:" + restaurant;
			String outdoors_count = "Outdoors:" + outdoors;
			String transport_count = "Transport:" + transport;

			data_values.add(home_count);
			data_values.add(office_count);
			data_values.add(yard_count);
			data_values.add(gym_count);
			data_values.add(mall_count);
			data_values.add(restaurant_count);
			data_values.add(outdoors_count);
			data_values.add(transport_count);

			data_array.put("Home", String.valueOf(home));
			data_array.put("Office", String.valueOf(office));
			data_array.put("Yard", String.valueOf(yard));
			data_array.put("Mall", String.valueOf(mall));
			data_array.put("Restaurant", String.valueOf(restaurant));
			data_array.put("Outdoors", String.valueOf(outdoors));
			data_array.put("Transport", String.valueOf(transport));

		}
		/**
		 * Other then the userid=0 this will search the label inside the Apache
		 * Hive and send the results.
		 */

		else {
			sql = "select * from detectedlocation where userid=" + userid;
			ResultSet resultSet = res = stmt.executeQuery(sql);
			System.out.println("Running: " + sql);

			rsmd = res.getMetaData();
			int colcount = rsmd.getColumnCount();

			for (int j = 1; j <= colcount; j++) {

				{
					LinkedHashMap<String, String> attributes = new LinkedHashMap<String, String>();
					String attribute_name = rsmd.getColumnLabel(j).split("\\.")[1];
					attributes.put("attribute_name", attribute_name);
					String attribute_type = rsmd.getColumnTypeName(j);
					attributes.put("Attribute_type", attribute_type);
					data_attributes.add(attributes);

				}
			}
			while (res.next()) {

				{
					ArrayList<Object> values = new ArrayList<Object>();

					for (int j = 1; j <= colcount; j++) {

						values.add(res.getString(j));

					}
					data_values.add(values);
				}
			}

		}
		/**
		 * Display the userid in the terminal.
		 */
		String line = "incoming userid = " + userid;

		System.out.println(line);

		/**
		 * Finally the result is send back to the model.
		 */
		return new SLDataModel(data_id, data_name, data_attributes, data_array);
	}

}
