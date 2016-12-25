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
 * This is an interface for the service notification channel and 
 * have an object of class situation notification.  
 * @author Rizvi
 */
public interface ServiceNotificationChannel{
/**
 * The function specification is required to for different kind of implementation with 
 * respect to the end point address and the notification object. 
 * @param objSituationNotification
 * @param endPoint 
 */
  public void notifyToRestService(SituationNotification objSituationNotification, String endPoint);
}
