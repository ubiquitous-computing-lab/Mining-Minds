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
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.reasoner.StandardValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
/**
 * InferredContextOntology extends the ContextOntology class in order to provide
 * the mechanisms to validate the Context Ontology which represents the Mining
 * Minds Context Model.
 * 
 * @author Claudia Villalonga and Muhammad Asif Razzaq
 * @version 2.5
 * @since 2015-10-28
 */
public class InferredContextOntology extends ContextOntology {
	/**
	 * Constructor of a new InferredContextOntology
	 * 
	 * @param ctxModel
	 *            OntModel associated to the inferred version of the Context
	 *            Ontology which represents the Mining Minds Context Model.
	 */
	public InferredContextOntology(OntModel ctxModel) {
		super(ctxModel);
	}
	/**
	 * Method to validate the Context Ontology which represents the Mining Minds
	 * Context Model.
	 * 
	 * @return true if the Context Ontology passed the consistency check, and
	 *         false otherwise.
	 */
	public boolean isValid() {
		return (validate().isValid());
	}
	/**
	 * Method to validate the Context Ontology which represents the Mining Minds
	 * Context Model.
	 * 
	 * @return Validity Report which contains the description of the errors in
	 *         the consistency check of the Context Ontology.
	 */
	public ValidityReport validate() {
		ValidityReport validity = ctxModel.validate();
		if (validity.isValid()) {
			if (!validity.isClean()) {
				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "Context Ontology contains errors in the class definition", "");
				Iterator<Report> it = validity.getReports();
				while (it.hasNext())
					report.add(it.next());
				return report;
			}
			if (ctxModel.getNsURIPrefix(HLCA.ns) == null) {
				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "Context Ontology does not contain the namespace", HLCA.ns);
				return report;
			}
			if (ctxModel.getOntClass(HLCA.pacClassName) == null) { 
				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "Context Ontology does not contain the class", HLCA.pacClassName); 
				return report;
			}
			if (ctxModel.getOntClass(HLCA.nutrClassName) == null) { 
				StandardValidityReport report = new StandardValidityReport();
				report.add(true, "Context Ontology does not contain the class", HLCA.nutrClassName); 
				return report;
			}
		}
		return validity;
	}
}
