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

import org.uclab.mm.dcl.llm.objectmodel.SituationNotification;

/**
 * This is the concrete class for the implementation of situation notification.  
 * @author Rizvi
 */
public class SCLSituationNotification extends NotifyService{

  private SituationNotification objSituationNotification;
  private String serviceName;
/**
 * Constructor of the class SCLSitatuionNotification.
 * @param objSituationNotification
 * @param serviceName
 * @param objServiceNotification 
 */
  public SCLSituationNotification(SituationNotification objSituationNotification, String serviceName, ServiceNotificationChannel objServiceNotification){
    super(objServiceNotification);
    this.objSituationNotification = objSituationNotification;
    this.serviceName = serviceName;

  }
/**
 * The concrete implementation of sendNotification function of Abstract NotifyService class.
 */
  @Override
  public void sendNotification(){
    objServiceNotification.notifyToRestService(objSituationNotification, serviceName);
  }

}
