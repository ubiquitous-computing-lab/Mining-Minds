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
package org.uclab.mm.rs.datacuration;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Taqdir Ali
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.uclab.mm.rs.datacuration.DataCurationResource.class);
        resources.add(org.uclab.mm.rs.informationcuration.InformationCurationResource.class);
        resources.add(org.uclab.mm.rs.servicecuration.ServiceCurationResource.class);
        resources.add(org.uclab.mm.rs.supportinglayer.SupportingLayerResource.class);
    }
    
}
