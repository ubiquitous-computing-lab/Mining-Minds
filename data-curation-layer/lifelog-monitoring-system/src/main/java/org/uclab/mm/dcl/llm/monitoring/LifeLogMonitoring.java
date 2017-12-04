/**
 * 
 * Copyright [2016] [Bilal Ali]
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under 
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 *  ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */
package org.uclab.mm.dcl.llm.monitoring;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.uclab.mm.dcl.llm.dataaccess.DBConnection;
import org.uclab.mm.dcl.llm.exception.DatabaseConnectionProblem;

/**
 * This is the main class to start the physical activity monitoring. 
 * Physical activities are monitored after every 3 seconds.
 * 
 * @author Rizvi
 */
public class LifeLogMonitoring implements Runnable{

  private static Connection conn = null;
  static org.apache.log4j.Logger log = Logger.getLogger(NutritionMonitor.class.getName());
  /**
   * @param args the command line arguments
   */
  static MonitoringContext objMonitoringContext = null;
/**
 * The main method is used to start the process of physical activities monitoring.
 * Monitoring is performed after every 3 seconds.
 * Time taken by each 
 * @param args
 * @throws SQLException
 * @throws IOException 
 */
 /* public static void main(String[] args) throws SQLException, IOException{
    args = new String[]{"3"};
    try{
      BasicConfigurator.configure();

      log.info("..........welcome for testing.........");
      conn = DBConnection.getDBConnection().getConnection();
      if (conn == null){
        throw new DatabaseConnectionProblem("cant open database.");
      }
      objMonitoringContext = new MonitoringContext(new PhysicalActivityMonitor());
      if (args.length == 0){
        log.info(" plz enter the number of second duration");
        System.exit(0);
      }
      int timelimit = Integer.parseInt(args[0]);
      for (int i = 0; i > -1; i++){
        log.info(" timer : " + i * timelimit + "Sec");
        long startTime = System.currentTimeMillis();
        objMonitoringContext.executeStrategy(conn, "3");
        long endTime = System.currentTimeMillis();
        log.info("That took " + (endTime - startTime) + " milliseconds");
        log.info("-------------------------------");
        Thread.sleep(timelimit * 1000);
      }
    } catch (DatabaseConnectionProblem | SQLException | InterruptedException exp){
      log.error("error exception in database connectivity", exp);
    }

  }*/
	public void startMonitoring(String[] args) throws SQLException, IOException {
		args = new String[] { "3" };
		try {
			BasicConfigurator.configure();
			
			log.info("..........welcome for testing.........");
		      conn = DBConnection.getDBConnection().getConnection();
		      if (conn == null){
		        throw new DatabaseConnectionProblem("cant open database.");
		      }
		      objMonitoringContext = new MonitoringContext(new PhysicalActivityMonitor());
		      if (args.length == 0){
		        log.info(" plz enter the number of second duration");
		        System.exit(0);
		      }
		      int timelimit = Integer.parseInt(args[0]);
		      for (int i = 0; i > -1; i++){
		        log.info(" timer : " + i * timelimit + "Sec");
		        long startTime = System.currentTimeMillis();
		        objMonitoringContext.executeStrategy(conn, "3");
		        long endTime = System.currentTimeMillis();
		        log.info("That took " + (endTime - startTime) + " milliseconds");
		        log.info("-------------------------------");
		        Thread.sleep(timelimit * 1000);
		      }
		    } catch (DatabaseConnectionProblem | SQLException | InterruptedException exp){
		      log.error("error exception in database connectivity", exp);
		    }
	}
  
  @Override
	public synchronized void run() {
		try {
			this.startMonitoring(null);
		} catch (SQLException ex) {
			java.util.logging.Logger.getLogger(
					LifeLogMonitoring.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			java.util.logging.Logger.getLogger(
					LifeLogMonitoring.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IllegalThreadStateException ex) {
			java.util.logging.Logger.getLogger(
					LifeLogMonitoring.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

}
