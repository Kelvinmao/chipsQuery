package chipsmanager.javabean;

import java.util.List;

/**
 * @author MaoKaining(毛凯宁)
 * 功能:构建chips实例
 *
 */
public class Chips {
	public Chips(){
		
	}
	
	public Chips(int chipID, String chipName, String functions, int searchFreq) {
		super();
		this.chipID = chipID;
		ChipName = chipName;
		this.functions = functions;
		this.searchFreq = searchFreq;
	}

	public Chips(int chipID, String chipName,String functions,String freq) {
		super();
		this.chipID = chipID;
		this.ChipName = chipName;
		this.functions = functions;
		this.searchFreq=Integer.parseInt(freq);
	}

	public Chips(String modelID, String chipName, String functions) {
		super();
		ModelID = modelID;
		ChipName = chipName;
		this.functions = functions;
	}

	public Chips(String modelID, String chipName, String functions, int pinNumber, String pinDefination,
			String pinIntroduction) {
		super();
		ModelID = modelID;
		ChipName = chipName;
		this.functions = functions;
		this.pinNumber = pinNumber;
		this.pinDefination = pinDefination;
		this.pinIntroduction = pinIntroduction;
	}

	public Chips(int chipID, String modelID, String chipName, String functions, int pinNumber, String pinDefination,
			String pinIntroduction) {
		super();
		this.chipID = chipID;
		this.ModelID = modelID;
		this.ChipName = chipName;
		this.functions = functions;
		this.pinNumber = pinNumber;
		this.pinDefination = pinDefination;
		this.pinIntroduction = pinIntroduction;
	}
	
	public Chips(List<String> list){
		this.searchFreq=Integer.parseInt(list.get(0));
		this.ChipName=list.get(1);
		this.functions=list.get(2);
		this.chipID=Integer.parseInt(list.get(3));
	}
	
	@Override
	public String toString() {
		return "chipID:"+chipID+"ModelID:"+ModelID+"chipName:s"+ChipName;
	}

	private int chipID;
	private String ModelID;
	private String ChipName;
	private String functions;
	private int pinNumber;
	private String pinDefination;
	private String pinIntroduction;
	private int searchFreq;
	
	
	
	public int getChipID() {
		return chipID;
	}
	public void setChipID(int chipID) {
		this.chipID = chipID;
	}
	public String getModelID() {
		return ModelID;
	}
	public void setModelID(String modelID) {
		ModelID = modelID;
	}
	public String getChipName() {
		return ChipName;
	}
	public void setChipName(String chipName) {
		ChipName = chipName;
	}
	public String getFunctions() {
		return functions;
	}
	public void setFunctions(String functions) {
		this.functions = functions;
	}
	public int getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	public String getPinDefination() {
		return pinDefination;
	}
	public void setPinDefination(String pinDefination) {
		this.pinDefination = pinDefination;
	}
	public String getPinIntroduction() {
		return pinIntroduction;
	}
	public void setPinIntroduction(String pinIntroduction) {
		this.pinIntroduction = pinIntroduction;
	}

	/**
	 * @return the searchFreq
	 */
	public int getSearchFreq() {
		return searchFreq;
	}

	/**
	 * @param searchFreq the searchFreq to set
	 */
	public void setSearchFreq(int searchFreq) {
		this.searchFreq = searchFreq;
	}
}	
