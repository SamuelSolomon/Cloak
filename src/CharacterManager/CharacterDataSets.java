package CharacterManager;

public interface CharacterDataSets {
	public enum Quality {WEAK,STRONG,POWERFUL,LEGENDARY};
	public enum InfoCategory {GENERAL,APPEARANCE,MENTALITY,HISTORY,ABILITIES};
		public enum InfoGeneralField {NAME,RACE,AGE,GENDER};
		public enum InfoAppearanceField {HEIGHT,HAIRCOLOR,EYECOLOR,SKINCOLOR,BUILD,WEIGHT,GENDER,SCARS};
		public enum InfoMentalityField {DEMEANOR,GENDER,LIKES,DISLIKES};
		public enum InfoHistoryField {NATIONALITY,OCCUPATIONS,HOBBIES,FAMILY};
		public enum InfoAbilitiesField {CLASS,SKILLS,MAGIC};
	public String defaultHeldCPLabel = "Current Held CP : ";
	public CharData Strength = new CharData("STR",5,1,20);
	public CharData Dexterity = new CharData("DEX",5,2,20);
	public CharData Constitution = new CharData("CON",5,1,20);
	public CharData Intelligence = new CharData("INT",5,1,20);
	public CharData Ego = new CharData("EGO",5,1,20);
	public CharData Presence = new CharData("PRE",5,1,20);
	public CharData OffensiveCombatValue = new CharData("OCV",1,5,8);
	public CharData DefensiveCombatValue = new CharData("DCV",2,2,8);
	public CharData OffensiveMentalCombatValue = new CharData("OMV",1,3,8);
	public CharData DefensiveMentalCombatValue = new CharData("DMV",1,3,8);
	public CharData Speed = new CharData("SPD",2,10,4);
	public CharData PhysicalDefense = new CharData("PD",0,1,8);
	public CharData ResistantPhysicalDefense = new CharData("rPD",0,3,12);
	public CharData ResistantEnergyDefense= new CharData("rED",0,3,12);
	public CharData EnergyDefense = new CharData("ED",0,1,8);
	public CharData Recovery = new CharData("REC",3,1,10);
	public CharData Stamina = new CharData("STAM",10,0.5,50);
	public CharData Body = new CharData("BODY",6,1,20);
	public CharData Stun = new CharData("STUN",12,0.5,50);
	public CharData Running = new CharData("RUN",12,1,20);
	public CharData Leaping = new CharData("LEAP",3,0.5,10);
	public CharData Swimming = new CharData("SWIM",2,0.5,10);
	public CharData Jumping = new CharData("JUMP",1,2,3);
	
	public static CharData[] getCharacteristicArray() {
		return new CharData[] {
				Strength,
				Dexterity,
				Constitution,
				Intelligence,
				Ego,
				Presence,
				OffensiveCombatValue,
				DefensiveCombatValue,
				OffensiveMentalCombatValue,
				DefensiveMentalCombatValue,
				Speed,
				PhysicalDefense,
				ResistantPhysicalDefense,
				EnergyDefense,
				ResistantEnergyDefense,
				Recovery,
				Stamina,
				Body,
				Stun,
				Running,
				Leaping,
				Swimming,
				Jumping};
	}
}