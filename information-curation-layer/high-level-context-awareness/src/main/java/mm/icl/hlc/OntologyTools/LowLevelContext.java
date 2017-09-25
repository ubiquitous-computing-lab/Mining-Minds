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
package mm.icl.hlc.OntologyTools;
import java.util.Iterator;
import com.hp.hpl.jena.graph.Triple;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDFS.Nodes;
/**
 * Low Level Context is the class encapsulating the OntModel associated to a Low
 * Level Context Instance. This is an instance of the Low Level Context class in
 * the Context Ontology which represents the Mining Minds Context Model. This
 * class provides supporting methods to facilitate the access to Low Level
 * Context.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class LowLevelContext extends Context {
	/**
	 * Name of the LLC category, e.g., Activity, Location or Emotion.
	 */
	private String llcCategoryName;
	/**
	 * Constructor of a new Low Level Context instance.
	 * 
	 * @param llcModel
	 *            OntModel associated to the Low Level Context instance.
	 */
	public LowLevelContext(OntModel llcModel) {
		super(llcModel);
	}
	/**
	 * Constructor of a new Low Level Context instance.
	 * 
	 * @param llcModel
	 *            OntModel associated to the Low Level Context instance.
	 * @param ont
	 *            Context Ontology which represents the Mining Minds Context
	 *            Model.
	 */
	public LowLevelContext(OntModel llcModel, ContextOntology ont) {
		super(llcModel);
		OntModel llcInst = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, llcModel);
		llcInst.addSubModel(ont.getCtxModel());
		ExtendedIterator<Individual> it = llcInst.listIndividuals();
		while (it.hasNext()) {    
			Individual inst = (Individual) it.next();
			ExtendedIterator<Resource> iter = inst.listRDFTypes(true);
			while (iter.hasNext()) {
				Resource res = iter.next();
				String uri = res.getURI();
				if (uri != null && !uri.equals(OWL2.NamedIndividual.getURI())) {
					if (!uri.equals(HLCA.userClassName)) {
						super.ctxInstanceName = inst.getURI();
						super.ctxTypeName = uri;
						StmtIterator stmtIt = res.listProperties();
						while (stmtIt.hasNext()) {
							Triple t = stmtIt.nextStatement().asTriple();
							if (t.getPredicate().equals(Nodes.subClassOf))
								this.llcCategoryName = t.getObject().getURI();    
						}
					}
				}
			}
		}
	}
	/**
	 * Method to get the name of the LLC category, e.g., Activity, Location, food or
	 * Emotion.
	 * 
	 * @return Name of the LLC category.
	 */
	public String getLlcCategoryName() {
		return llcCategoryName;
	}
}
