package chipsmanager.javabean;

/**
 * @author MaoKaining(毛凯宁)
 * 功能:构建chips实例
 *
 */
public class chips {
	public chips(int chipID, String modelID, String chipName, String functions, int pinNumber, String pinDefination,
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
}	
