package CharacterManager;

/**
 * Simple Data Storage for Characteristic Stats, purpose within Cloak is to store the default values for all the stats within the CharacterDataSets class
 *@param Label Characteristic Name Label
 *@param initialValue default starting value
 *@param Cost cost per characteristic point
 *@param SoftCap soft upper limit on characteristic value, additional points should be made to cost more in implementing classes
 */
public class CharData {
	public String Label;
	public int initialValue;
	public double Cost;
	public int SoftCap;
	
	public CharData (String Label, int initialValue, double Cost, int SoftCap) {
		this.Label = Label;
		this.initialValue = initialValue;
		this.Cost = Cost;
		this.SoftCap = SoftCap;
	}
}
