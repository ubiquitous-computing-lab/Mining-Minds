package org.uclab.mm.icl.hlc.OntologyTools;

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
 * @author Claudia Villalonga
 * @version 2.0
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
		
//		System.out.println("1. I am in LowLevelContext");
		
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

//						System.out.println(super.ctxInstanceName +"  "+ super.ctxTypeName); //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#llc_1098385725_0000000000000000000  http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Bread
						
						StmtIterator stmtIt = res.listProperties();
						

						while (stmtIt.hasNext()) {

							Triple t = stmtIt.nextStatement().asTriple();
							
						
							

							if (t.getPredicate().equals(Nodes.subClassOf))
								this.llcCategoryName = t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food	
//							System.out.println(this.llcCategoryName);
					/*			System.out.println("I am in LowLevelContext");
								System.out.println(t.getObject().getURI());   //Activity Emotion location      SeaFood Not Food Problem
								System.out.println(t.getObject().getLocalName());   //Activity Emotion location      SeaFood Not Food Problem
					*/
							
/*							
							if (t.getObject().getLocalName()=="Activity")
								{this.llcCategoryName = t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food									
								}
							
							else if (t.getObject().getLocalName()=="Emotion") 
									
								{this.llcCategoryName = t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							    }
								
							else if (t.getObject().getLocalName()=="Location") 
									
								{this.llcCategoryName = t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
								}
							
							
							
							
							
							else if  (t.getObject().getLocalName()=="Eggs")  
									
								{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
								}
							
							else if  (t.getObject().getLocalName()=="Fruits")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="Grain")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="Legumes")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="Meat")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="MilkAndDairyProducts")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="Nuts")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="SeaFood")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="Snacks")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}

							else if  (t.getObject().getLocalName()=="Vegetable")  
								
							{this.llcCategoryName = HLCA.foodClassName; // t.getObject().getURI();    //http://www.miningminds.re.kr/icl/context/context-v2-5.owl#Food
							}
							else {this.llcCategoryName = t.getObject().getURI(); };
*/		
						}

					}

				}

			}

		}

	}

	/**
	 * Method to get the name of the LLC category, e.g., Activity, Location or
	 * Emotion.
	 * 
	 * @return Name of the LLC category.
	 */
	public String getLlcCategoryName() {
		

		return llcCategoryName;
	}

}
