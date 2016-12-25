//http://sqa.fyicenter.com/Online_Test_Tools/Random_Date_Time_Value_Generator.php 
// http://stackoverflow.com/questions/1519736/random-shuffling-of-an-array/18456998#18456998
//http://www.mkyong.com/java/java-return-a-random-item-from-a-list/

package org.uclab.mm.icl.hlca;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.uclab.mm.icl.hlc.ContextOntologyManager.ContextOntologyManager;
import org.uclab.mm.icl.hlc.HLC.TimeUtil;
import org.uclab.mm.icl.hlc.HLCBuilder.ContextMapper;

//import com.hp.hpl.jena.util.FileManager;asd


public class LlcRandom {
		static  String directory ="D:\\ICL_LOG\\TDB"; 		                            
		static final String ontologyFile = "file:////D:/ICL_LOG/TDB/context-v2-5.owl";        
		static String llcLabel ;                                                            
		static final String userId = "35";

	private Random random = new Random();
	
	public static void main(String[] args) throws InterruptedException, ParseException {
		
		 long LLC_Start = System.currentTimeMillis();
		 
//		 String llc[] = { "SoyMilk", "SeaWeed", "Ham", "CarbonatedDrink", "WaterMelon", "Cucumber", "Banana", "IceCream", "Egg", "Carrot", "UnidentifiedFood", "Spinach", "Snack", "Pizza", "Milk", "ChickenSnack", "Orange", "Bread", "Hamburger", "FriedFood", "Noodle", "Beans", "Chicken", "Apple", "Tofu", "Yogurt", "Pumpkin", "Pork", "Beef", "Rice", "Fish", "Ramen", "Peanut", "Grape", "QuailEggs", "Neutral", "Boredom", "Surprise", "UnidentifiedEmotion", "Sadness", "Happiness", "Disgust", "Anger", "Fear", "Mall", "Gym", "Home", "Transport", "Restaurant", "UnidentifiedLocation", "Outdoors", "Yard", "Office", "Sitting", "Jumping", "Running", "Cycling", "ClimbingStairs", "Sweeping", "Stretching", "Dancing", "Walking", "RidingEscalator", "RidingElevator", "UnidentifiedActivity", "Standing", "Eating", "DescendingStairs", "LyingDown", "Hiking"};
//		 String llc[] = {  "Standing",  "Sweeping",  "Walking",  "Yard","Happiness", "Neutral"};
		 String llc[] = {  "Standing",  "Sweeping",  "Walking",  "Yard","Happiness", "Neutral","Apple","Orange", "Salmon","Home","Restaurant"};

		 
		 
		 
		LlcRandom obj = new LlcRandom();
		
		 DateFormat formatter = new  SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.KOREA);  
		    Calendar cal=Calendar.getInstance();
		    String start_Time="2016 04 18 10:06:00";												    //2016 04 18 10:06:52
		    String end_Time  ="2016 04 18 10:10:52";

		    for (int i =1; i < 50; i++)
		    {			
		    TimeUtil util = new TimeUtil();
		    
		    cal.setTime(formatter.parse(start_Time));
		    Long value1 = cal.getTimeInMillis();

		    cal.setTime(formatter.parse(end_Time));
		    Long value2 = cal.getTimeInMillis();

		    long value3 = (long)(value1 + Math.random()*(value2 - value1));
		    cal.setTimeInMillis(value3);
		    
//		    System.out.println(formatter.format(cal.getTime()));
		    Calendar cal1= util.parseString(formatter.format(cal.getTime()));
	    	long ontModel_Load_start = System.nanoTime();		    
				// READ THE CONTEXT ONTOLOGY FROM FILE
				OntModel ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM);
				ontModel.read(ontologyFile);
				// CREATE THE CONTEXT ONTOLOGY MANAGER
				ContextOntologyManager mng = new ContextOntologyManager(directory, ontModel);

////  Time Difference commented in MM V2.5				
//			long ontModel_Load_end = System.nanoTime();
//	    	double diff_ontModel_Ld_time = (ontModel_Load_end - ontModel_Load_start);
//	    	  System.out.println("nanosec diff_ontModel_Ld_time: "  + diff_ontModel_Ld_time);
////  Time Difference commented in MM V2.5	    	
	    	
				if (mng.correctInitialization()) {
				// CREATE THE MAPPER
				ContextMapper mapper = new ContextMapper(mng.getContextOntology());


				//mapper.mapLLC(llcLabel, userId, cal);                                         //A_D
				
				llcLabel=obj.getRandomLlc(llc); 
				System.out.println(llcLabel +", "); // System.out.println(llcLabel +", "+ cal1);
				// MAP LLC
				mapper.mapLLC(llcLabel , userId, cal1);                                         ////A_D
				

		        }

				else {
					
					System.out.println("Ont Model not Correctly Initialized");
					
				}
				
				
				
//			short z=1000;
//	    	Thread.sleep(z);/*will provide 2 second delay alter data type of z or value of z for longer                              delays required*/

		    }
		    
////  Time Difference commented in MM V2.5
//		    long elapsed = System.currentTimeMillis() - LLC_Start;
//		    DateFormat df = new SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'");
//		    df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
//		    System.out.println("Time taken in creating " + df.format(new Date(elapsed)));
////  Time Difference commented in MM V2.5		    				
		}
	

	public String getRandomLlc(String[] llc) {

			//0-4
	    	int index = random.nextInt(llc.length);
	    	//	System.out.println("\nIndex :" + index );
	    	return llc [index];
	    
	}
	}