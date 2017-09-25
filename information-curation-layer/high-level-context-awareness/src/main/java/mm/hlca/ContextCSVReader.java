package mm.hlca;
import mm.icl.hlc.HLC.TimeUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;


public class ContextCSVReader 
{
//	static final String userId = "9735";
//	static final String llcLabel = "Sitting";     
//	TimeUtil util = new TimeUtil();			 
//	Calendar cal1 = util.parseString("2016 04 18 10:11:25");

	
	//Delimiters used in the CSV file
    private static final String COMMA_DELIMITER = ",";
    
   public static void main(String args[])
//    public void bilalTest()
    
    {
		TimeUtil util = new TimeUtil();		
		BufferedReader br = null;
        try
        {
            //Reading the csv file
            br = new BufferedReader(new FileReader("E:/ICL_LOG/TDB/Activity.csv"));//ContextCSV
            
            //Create List for holding Employee objects
            List<ContextCSV> contextList = new ArrayList<ContextCSV>();
            
            String line = "";
            //Read to skip the header
            br.readLine();
            //Reading from the second line
            while ((line = br.readLine()) != null) 
            {
                String[] ContextCSVDetails = line.split(COMMA_DELIMITER);
                
                if(ContextCSVDetails.length > 0 )
                {
                	ContextCSV context_llc = new ContextCSV(ContextCSVDetails[0], ContextCSVDetails[1],ContextCSVDetails[2]);
                	contextList.add(context_llc);
                }
            }
            
            //Lets print the Employee List
            for(ContextCSV c : contextList)
            { 
            	
                System.out.println(c.getllcLabel()+" "+ c.getuserId()+" "+ util.parseString(c.getcal()));
//                System.out.println(util.parseString(c.getcal())); //                System.out.println(c.getcal()); //                System.out.println(c.getuserId()); //                System.out.println(c.getllcLabel());
            }
        }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }
    }
}