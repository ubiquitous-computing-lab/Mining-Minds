package mm.hlca;
import mm.icl.hlc.ContextOntologyManager.ContextRemovalManager;

public class RemoveContext {

	public static void main(String[] args) {
		
		try{
			ContextRemovalManager jdh = new ContextRemovalManager();
			jdh.ClearModel();
			System.out.println("All Instance Removed.");

		}
		catch(Exception e)
		{
			
		}
		
		
	}
}

