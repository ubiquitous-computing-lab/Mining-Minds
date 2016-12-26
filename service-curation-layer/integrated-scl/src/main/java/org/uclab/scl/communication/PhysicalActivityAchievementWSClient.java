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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.uclab.scl.outputModel.Achievement.Achievement;
/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class PhysicalActivityAchievementWSClient extends AbstractRestClient<Achievement, List<String>> {

  public PhysicalActivityAchievementWSClient(String baseURI) {
    super(baseURI);
  }

  @Override
  public Achievement GetData(String GET_ResourceURI) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public List<String> PostData(String POST_ResourceURI, Achievement postedResource) {
    List<String> phyActAchievementResult = new ArrayList<>();
    Achievement phyAAch = postedResource;
    ClientConfig config = new DefaultClientConfig();
    config.getClasses().add(JacksonJsonProvider.class);
    Client client = Client.create(config);
    WebResource service = client.resource(UriBuilder.fromUri(baseURI).build());
    ClientResponse response = service.path(POST_ResourceURI).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, phyAAch);
    phyActAchievementResult.add(response.getEntity(new GenericType<List<String>>() {}).get(0));
    return phyActAchievementResult;
  }
}
