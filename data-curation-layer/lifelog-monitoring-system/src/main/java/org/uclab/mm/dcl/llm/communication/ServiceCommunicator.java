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
package org.uclab.mm.dcl.llm.communication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.uclab.mm.dcl.llm.dataaccess.SituationEventDAOImp;
import org.uclab.mm.dcl.llm.monitoring.*;
import org.uclab.mm.dcl.llm.objectmodel.FoodLog;
import org.uclab.mm.dcl.llm.objectmodel.SituationConditions;
import org.uclab.mm.dcl.llm.objectmodel.SituationEvents;
import org.uclab.mm.dcl.llm.objectmodel.Violations;
import org.uclab.mm.dcl.llm.objectmodel.VoilationNotification;

/**
 * REST Web Service
 *
 * @author Rizvi
 */
// @Path("testweb")
@Path("servicecommunicator")
public class ServiceCommunicator {

	@Context
	private UriInfo context;

	/**
	 * Creates a new instance of testweb
	 */
	public ServiceCommunicator() {
	}

	/**
	 * Retrieves representation of an instance of ServiceCommunicator
	 *
	 * @return an instance of java.lang.String
	 */
	@GET
	@Produces("application/json")
	@Path("/")
	public String getJson() {
		// TODO return proper representation object
		throw new UnsupportedOperationException();
	}

	/**
	 * PUT method for updating or creating an instance of ServiceCommunicator
	 *
	 * @param content
	 *            representation for the resource
	 * @return an HTTP response with content of the updated or created resource.
	 */
	@PUT
	@Consumes("application/json")
	@Path("/")
	public void putJson(String content) {
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/AddFoodLog/")
	public String AddFoodLog(FoodLog objOuterFoodLog) {

		// List<String> response = new ArrayList<String>();
		String strResponse = "Done";
		try {
			NutritionMonitor ObjNutritionMonitoring = new NutritionMonitor();
			// strResponse = ObjNutritionMonitoring.doMonitor(objOuterFoodLog);
			ObjNutritionMonitoring.doMonitor(objOuterFoodLog);

			// Gson objJson = new Gson();
			// strResponse = objJson.toJson(response);
		} catch (Exception ex) {
		}

		return strResponse;
	}

	/**
	 * Retrieves violation from the log .
	 *
	 * @return List<Violations>
	 *
	 */
	@GET
	@Path("/Violations/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Violations> getViolationConditions() {
		VoilationNotification objViolationNotification = new VoilationNotification();
		return objViolationNotification.getViolations();

	}

	/**
	 * Retrieves total count of the violation from the log.
	 *
	 * @return List<Violations>
	 */
	@GET
	@Path("/TotalViolations/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Violations> getTotalViolationConditions() {
		VoilationNotification objViolationNotification = new VoilationNotification();
		return objViolationNotification.getTotalViolations();

	}

	/**
	 * Retrieves total count of the violation from the log.
	 *
	 * @return List<Violations>
	 */
	@GET
	@Path("/PhysicalMonitoring/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getStartedPhysicalMonitoring() throws SQLException,
			IOException, IllegalThreadStateException {
		Thread t = new Thread(new LifeLogMonitoring());
		t.start();
		System.out.println("Deamon is running Alone......");
		return "MonitoringStarted ";
	}

	/**
	 * Retrieves total violation from the log against a user information in term
	 * of ID.
	 *
	 * @param uid
	 * @return List<Violations>
	 */
	@GET
	@Path("/UserViolations/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Violations> getUserViolationConditions(
			@PathParam("uid") String uid) {
		VoilationNotification objViolationNotification = new VoilationNotification();
		return objViolationNotification.getUserViolations(uid);

	}

	/**
	 * Retrieves violation details from the log against a log information in
	 * term of ID.
	 *
	 * @param lgid
	 * @return List<Violations>
	 */
	@GET
	@Path("/LogViolations/{lgid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Violations> getLogViolationConditions(
			@PathParam("lgid") String lgid) {
		VoilationNotification objViolationNotification = new VoilationNotification();
		return objViolationNotification.getLogDeatails(lgid);

	}

	/**
	 * Retrieves violation with sequence grater then the provided id from the
	 * log.
	 *
	 * @param lgid
	 * @return List<Violations>
	 */
	@GET
	@Path("/SLViolations/{maxlgid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Violations> getMaxLogViolationConditions(
			@PathParam("maxlgid") String maxlgid) {
		VoilationNotification objViolationNotification = new VoilationNotification();
		return objViolationNotification.getMaxViolations(maxlgid);

	}

	/**
	 * Accept the json of the constraints and monitoring conditions required for
	 * monitoring and assessing the conditions.
	 *
	 * @param siuationEvents
	 * @return
	 */
	@POST
	@Path("SituationAdd")
	@Produces("application/json")
	@Consumes("application/json")
	public String addSituationEvents(SituationEvents siuationEvents) {
		String sitID = "SuccessfullyProcessed ";
		SituationEventDAOImp imp = new SituationEventDAOImp();
		imp.persistentSituation(siuationEvents);
		return sitID;
	}

}
