/*
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package org.uclab.mm.kcl.edkat.datamodel.dclcommunication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SituationEvents implements Serializable {

	private List<SituationEvent> listSEvents = new ArrayList<SituationEvent>();

	public List<SituationEvent> getListSEvents() {
		return listSEvents;
	}

	public void setListSEvents(List<SituationEvent> listSEvents) {
		this.listSEvents = listSEvents;
	}
	
}
