//http://stackoverflow.com/questions/16064992/apache-jena-deleting-all-data-in-the-model
package mm.icl.hlc.ContextOntologyManager;

import mm.icl.hlc.OntologyTools.HLCA;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.tdb.TDBFactory;

public class ContextRemovalManager {

	private static Model model;
	private static Dataset dataset;

	public static Model getJenaModel(){
	    if(model!=null){
	        return model;
	    }
	    else{
	        String directory = "E:\\ICL_LOG\\TDB" ;
	        try{
	            dataset = TDBFactory.createDataset(directory);
	            model = dataset.getNamedModel(HLCA.ns); //"DB"
	            return model;
	            
	        }catch(Exception e){
	            System.out.println("Error when retrieving model: "+e.getMessage());
	        }              
	    }
	    return null;
	}

	public static void SaveAndCloseModel(){
	    if(model!=null && dataset!=null){
	        model.commit();
	        model.close();
	        dataset.close();
	        System.out.println("SavedClosedModel");
	    }
	}

	public static  void ClearModel(){
		
		model=getJenaModel();

		if(model!=null ){
	        model.removeAll();
	        SaveAndCloseModel();
	        System.out.println("ClearedModel");
	    }
	}
	
//	public static void main(String[] args) {
//		
//		try{
//System.out.println("plz check it......");
// 
//ClearModel();
//		}
//		catch(Exception e)
//		{
//			
//		}
//		
//	}

}