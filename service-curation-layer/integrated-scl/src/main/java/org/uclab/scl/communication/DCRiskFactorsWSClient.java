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
import org.uclab.scl.datamodel.RiskFactors;

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
public class DCRiskFactorsWSClient extends
    AbstractRestClient<List<RiskFactors>, String> {

  public DCRiskFactorsWSClient(String baseURI) {
    super(baseURI);
  }

  @Override
  public List<RiskFactors> GetData(String getResourceURI) {
    ClientConfig config = new DefaultClientConfig();
    config.getClasses().add(JacksonJsonProvider.class);
    Client client = Client.create(config);
    WebResource service = client.resource(UriBuilder.fromUri(baseURI).build());
    List<RiskFactors> riskFactors = 
        service.path(getResourceURI).accept(MediaType.APPLICATION_JSON).get(new GenericType<List<RiskFactors>>() {});
    if (riskFactors.size() > 0)
      return riskFactors;
    else
      return null;
  }

  @Override
  public String PostData(String postResourceURI, List<RiskFactors> postedResource) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

}
