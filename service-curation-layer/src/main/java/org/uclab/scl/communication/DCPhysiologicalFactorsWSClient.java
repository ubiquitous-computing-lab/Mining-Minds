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
import org.uclab.scl.datamodel.PhysiologicalFactors;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
/**
 * @version MM v2.5
 * @author Afzal
 *
 */
public class DCPhysiologicalFactorsWSClient extends AbstractRestClient<List<PhysiologicalFactors>, String> {
  
  /**
   * Instantiates PhysiologicalFactors Webservice client
   * 
   * <p>
   * @param baseURI
   */
  public DCPhysiologicalFactorsWSClient(String baseURI) {
    super(baseURI);
  }
  
  /**
   * 
   * <p>
   * @param resource URI
   * @return PhysiologicalFactors
   */
  @Override
  public List<PhysiologicalFactors> GetData(String getResourceURI) {
    ClientConfig config = new DefaultClientConfig();
    config.getClasses().add(JacksonJsonProvider.class);
    Client client = Client.create(config);
    WebResource service = client.resource(UriBuilder.fromUri(baseURI).build());
    List<PhysiologicalFactors> physiologicalFactors = 
        service.path(getResourceURI).accept(MediaType.APPLICATION_JSON).get(new GenericType<List<PhysiologicalFactors>>() {});
    if (physiologicalFactors.size() > 0)
      return physiologicalFactors;
    else
      return null;
  }
  /**
   * 
   * <p>
   * @param resource URI
   * @param PhysiologicalFactors list
   * @return String
   */
  @Override
  public String PostData(String postResourceURI, List<PhysiologicalFactors> postedResource) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
