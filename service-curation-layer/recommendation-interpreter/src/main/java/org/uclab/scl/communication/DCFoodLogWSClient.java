/**
 * Copyright [2016] [Muhammad Afzal]
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
package org.uclab.scl.communication;

import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.uclab.scl.interpreterdatamodel.FoodLog;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class DCFoodLogWSClient extends AbstractRestClient<List<FoodLog>, List<FoodLog>> {
  
  /**
   * Instantiates Food Log Web service Client
   * <p>
   * 
   * @param baseURI
   * 
   */
  public DCFoodLogWSClient(String baseURI) {
    super(baseURI);
  }
  /**
   * <p>
   * 
   * @param resource URI
   * @param food log list
   * @return list food log
   */
  @Override
  public List<FoodLog> PostData(String postResourceURI, List<FoodLog> postedResource) {
    FoodLog foodLog = postedResource.get(0);
    ClientConfig config = new DefaultClientConfig();
    config.getClasses().add(JacksonJsonProvider.class);
    Client client = Client.create(config);
    WebResource service = client.resource(UriBuilder.fromUri(baseURI).build());
    ClientResponse response = service.path(postResourceURI).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, foodLog);
    List<FoodLog> foodLogList = response.getEntity(new GenericType<List<FoodLog>>() {});
    return foodLogList;
  }
  /**
   * <p>
   * @param resource URI
   * @return food log list
   */
  @Override
  public List<FoodLog> GetData(String getResourceURI) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
