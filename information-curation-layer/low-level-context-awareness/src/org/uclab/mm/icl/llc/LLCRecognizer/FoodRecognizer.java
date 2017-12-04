package org.uclab.mm.icl.llc.LLCRecognizer;

import org.uclab.mm.icl.MainServlet;
import org.uclab.mm.icl.data.DeviceData;
import org.uclab.mm.icl.data.SmartphoneData;
import org.uclab.mm.icl.llc.LLCManager.ContextLabel;
import org.uclab.mm.icl.llc.LLCManager.LLCUnifier;
import org.uclab.mm.icl.llc.config.RecognizerType;
import org.uclab.mm.icl.llc.restservices.RestClients;
import org.uclab.mm.icl.llc.config.ContextType;
import org.uclab.mm.icl.llc.config.DeviceType;

class FoodData{

	byte[] image;
	String label;
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public FoodData(byte[] image, String label) {
		super();
		this.image = image;
		this.label = label;
	}
}

public class FoodRecognizer extends LLCRecognizer<FoodData>{

	int eatingCnt = 0;
	
	public FoodRecognizer(long userID) {
		super(userID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ConsumeData(DeviceType it, DeviceData obj) {
		// TODO Auto-generated method stub
		if(it.equals(DeviceType.SmartPhone)){
			SmartphoneData sd = (SmartphoneData) obj;
			//get the z coordinate of smartphone data
			byte[] data = null;
			String label = null;
			if(sd.getImage() != null)data = sd.getImage();
			if(sd.getFoodTag() != null)label = sd.getFoodTag();
			setData(new FoodData(data, label));
		}
	}

	@Override
	public RecognizerType getType() {
		// TODO Auto-generated method stub
		return RecognizerType.FR;
	}

	@Override
	public ContextLabel recognize(FoodData input, String timeStamp) {
		// TODO Auto-generated method stub
		
		
		String[] labels = getType().getContext().getLabels(); //up, down, nodir
		return new ContextLabel(userID, labels[labels.length-1],timeStamp, getType().getContext()); //down
	}
	
	@Override
	public Runnable getRunnable(String timeStamp, LLCUnifier unifier){
    	return new Runnable() { // new Handler and Runnable
            @Override
            public void run() {
            	if(MainServlet.server.getUser(userID).getcurLabels().get(ContextType.Food.getValue()).equals("Eating")){
        			eatingCnt++;
        		}else{
        			eatingCnt = 0;
        		}
        		if(eatingCnt > 4){ //if eating prolonged than 12seconds
        			while(isThresholdReached()){
        				FoodData idd = inputDatas.get(0);
                		inputDatas.remove(0);
                		ContextLabel rlt = recognize(idd, timeStamp);
                		try {
							RestClients.AddFoodLog(rlt);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                		//unifier.push(recognize(idd, timeStamp), getType());
                	}
        		}
            	
            }
        };
    }

}
