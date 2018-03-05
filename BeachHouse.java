public class BeachHouse extends Vacation {
	public static double COST_1_TO_3 = 196;
	public static int MEMBERS_3 = 3;
	public static double COST_4_TO_7 = 306;
	public static int MEMBERS_7 = 7;
	public static double COST_8_TO_10 = 524;
	public static double INSURANCE_COST = 9.50;
	public static double ADDITIONAL_INSURANCE_COST = 4.75;
	public static int MEMBERS_BELOW_ADDITIONAL_COST = 5;
	private boolean requireInsurance;

	/**
   Constructor that sets empFName, empLName, numMembers, location, zipCode  
   by caling the superclass constructor
   
   @param empFName The first name of the employee
   @param empLName The last name of the employee
   @param numMembers The number of members in the family including the employee
   @param location The location of the vacation
   @param zipCode The zip code of the location       
   **/
   public BeachHouse(String empFName, String empLName, int numMembers,
			String location, String zipCode) {
		super(empFName, empLName, numMembers, location, zipCode);
	}


   //@return whether the current instance of an object requires hurricane insurance
   private boolean getRequireInsurance() {return this.requireInsurance;}

	/**
      Set whether or not hurricane insurance is required
      @param requireInsurance whether or not hurricane insurance is required
	  **/
   public void setRequireInsurance(boolean requireInsurance) {
		this.requireInsurance = requireInsurance;
	}

	/**
      Calculate the cost of the current beach house vacation
      @return The calculated cost of the current beach house vacation
	  **/
   public double calculateCost() {
		double cost;
		if (super.getNumMembers() <= MEMBERS_3) {
			cost = COST_1_TO_3;
		} else if (super.getNumMembers() > MEMBERS_3
				&& super.getNumMembers() <= MEMBERS_7) {
			cost = COST_4_TO_7;
		} else {
			cost = COST_8_TO_10;
		}
		if (this.requireInsurance == true) {
			if (super.getNumMembers() <= MEMBERS_BELOW_ADDITIONAL_COST) {
				cost += super.getNumMembers() * INSURANCE_COST;
			} else {
				cost += (MEMBERS_BELOW_ADDITIONAL_COST * INSURANCE_COST)
						+ ((super.getNumMembers() - MEMBERS_BELOW_ADDITIONAL_COST) * ADDITIONAL_INSURANCE_COST);
			}
		}
		return cost;
	}

	/**
      Create a string representation of beach house vacation
      @return The string representation of beach house vacation object
	  **/
   public String toString() {
		String output = "Vacation Type: Beach House";
		output += super.toString()
				+ "\n   Does this vacation require insurance? ";
		if (this.getRequireInsurance() == true) {
			output += "Yes";
		} else {
			output += "No";
		}
		output += "\n   Cost of this vacation: "
				+ String.format("   $%.2f", this.calculateCost()) + "\n\n";
		return output;
	}

}
