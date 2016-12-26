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
import org.uclab.scl.interpreterdatamodel.LifeLogData;

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
public class DCLifeLogDataWSClient extends AbstractRestClient<List<LifeLogData>, List<LifeLogData>> {
  
  /**
   * instantiates Web Service Client
   * <p>
   * @param baseURI
   */
  public DCLifeLogDataWSClient(String baseURI) {
    super(baseURI);
  }
  
  /**
   * 
   * <p>
   * @param resource URI
   * @return life log data list
   */
  @Override
  public List<LifeLogData> GetData(String getResourceURI) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
  
  /**
   * 
   * <p>
   * @param resource URI
   * @param life log data list
   * @return list life log data
   */
  @Override
  public List<LifeLogData> PostData(String postResourceURI, List<LifeLogData> postedResource) {

    LifeLogData lifeLogData = postedResource.get(0);
    ClientConfig config = new DefaultClientConfig();
    config.getClasses().add(JacksonJsonProvider.class);
    Client client = Client.create(config);
    WebResource service = client.resource(UriBuilder.fromUri(baseURI).build());

    ClientResponse response = service.path(postResourceURI)
        .type(MediaType.APPLICATION_JSON)
        .post(ClientResponse.class, lifeLogData);
    List<LifeLogData> listLifeLogData = response.getEntity(new GenericType<List<LifeLogData>>() {});
    return listLifeLogData;
  }
}
