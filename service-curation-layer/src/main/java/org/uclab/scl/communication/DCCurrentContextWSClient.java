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
import org.uclab.scl.datamodel.CurrentContext;

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
public class DCCurrentContextWSClient extends AbstractRestClient<CurrentContext, String> {
  
  /**
   * Instantiates WebService Client
   * <p>
   * @param baseURI
   */
  public DCCurrentContextWSClient(String baseURI) {
    super(baseURI);
  }
  
  /**
   * return current Context
   * 
   * <p>
   * @param Resource Url
   * @return current Context
   */
  @Override
  public CurrentContext GetData(String GET_ResourceURI) {
    List<CurrentContext> currentContextList = null;
    CurrentContext currentContext = null;
    try {
      ClientConfig config = new DefaultClientConfig();
      config.getClasses().add(JacksonJsonProvider.class);
      Client client = Client.create(config);
      WebResource service = client.resource(UriBuilder.fromUri(baseURI).build());
      currentContext = service.path(GET_ResourceURI).accept(MediaType.APPLICATION_JSON).get(new GenericType<CurrentContext>() {});
    } catch (Exception exp) {
      System.err.println(exp.getMessage());
    }
    return currentContext;
  }
  /**
   * 
   * <p>
   * @param Resource Uri
   * @param currentContext
   * @return String
   */
  @Override
  public String PostData(String POST_ResourceURI, CurrentContext postedResource) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
