/**
* 
* Copyright [2016] [Claudia Villalonga & Muhammad Asif Razzaq]
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed under 
* the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
*  ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and limitations under the License.
*/
package mm.test;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.ReadWrite;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDB;
import com.hp.hpl.jena.tdb.TDBFactory;



public class TestRetrieveContextInstance {

static final String directory = "E:\\ICL_LOG\\TDB";///"D:\\HLCA" ;  
	
static final String ctxName = 
			"http://www.miningminds.re.kr/icl/context/context-v2-5.owl#llc_0388160157_0000000000000000000"; 
		
		
	public static void main(String[] args) {
			
			Dataset dataset = TDBFactory.createDataset(directory);
			TDB.getContext().set(TDB.symUnionDefaultGraph, true);
		
			dataset.begin(ReadWrite.READ);

			Model inst = dataset.getNamedModel(ctxName);
			dataset.end();
			
			if (!inst.isEmpty()){
				OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, inst);
				ontModel.write(System.out, "Turtle");
			}
	}
}
