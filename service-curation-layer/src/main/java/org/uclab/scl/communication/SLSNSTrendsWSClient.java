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

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.uclab.scl.datamodel.SNSTrends;

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
public class SLSNSTrendsWSClient extends AbstractRestClient<SNSTrends, String> {
  private static Logger LOG = LogManager.getRootLogger();
  
  public SLSNSTrendsWSClient(String baseURI) {
    super(baseURI);
  }

  @Override
  public SNSTrends GetData(String GET_ResourceURI) {
    List<SNSTrends> listSNSTrends = new ArrayList<>();
    SNSTrends snsTrends = null;
    try {
      ClientConfig config = new DefaultClientConfig();
      config.getClasses().add(JacksonJsonProvider.class);
      Client client = Client.create(config);
      baseURI += GET_ResourceURI;
      WebResource service = client.resource(UriBuilder.fromUri(baseURI).build());
      listSNSTrends = service.accept(MediaType.APPLICATION_JSON).get(new GenericType<List<SNSTrends>>() {});
      if (listSNSTrends.size() > 0)
        return snsTrends = listSNSTrends.get(0);
      else
        return null;
    } catch (Exception exp) {
      LOG.error(exp.getMessage());
    }
    return snsTrends;
  }
  
  @Override
  public String PostData(String POST_ResourceURI, SNSTrends postedResource) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
