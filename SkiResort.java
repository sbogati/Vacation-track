public class SkiResort extends Vacation {
	public static double COST_PER_STAR = 308.00;
	public static double COST_PER_SKI_LESSON = 49.95;
	public static int MIN_STARS = 2;
	public static int MAX_STARS = 4;
	private int numOfStars;
	private String[] familyMemberNames = new String[super.getNumMembers()];
	private Boolean[] requireSkiLesson = new Boolean[super.getNumMembers()];

	/**
   Constructor that sets empFName, empLName, numMembers, location, zipCode  
   by caling the superclass constructor
   
   @param empFName The first name of the employee
   @param empLName The last name of the employee
   @param numMembers The number of members in the family including the employee
   @param location The location of the vacation
   @param zipCode The zip code of the location       
   **/
   public SkiResort(String empFName, String empLName, int numMembers,
			String location, String zipCode) {
		super(empFName, empLName, numMembers, location, zipCode);
	}
   
   //@return the number of stars resort is rated
   public int getNumOfStars() {return this.numOfStars;}

	//@return the list of names of family members
   public String[] getFamilyMemberNames() {
		String[] tempArray = new String[this.familyMemberNames.length];
		for (int x = 0; x < super.getNumMembers(); x++) {
			tempArray[x] = this.familyMemberNames[x];
		}
		return tempArray;
	}

	//@return the list of whether or not each family member requires ski lesson
   public Boolean[] getRequireSkiLesson() {
		Boolean[] tempArray = new Boolean[this.familyMemberNames.length];
		for (int x = 0; x < super.getNumMembers(); x++) {
			tempArray[x] = this.requireSkiLesson[x];
		}
		return tempArray;
	}

	/**
      Set the number of stars resort is rated if it is valid or return false if not
      
      @param numOfStaras The number of stars resort is rated
      @return Whether or not the star is valid
	  **/
   public boolean setNumOfStars(int numOfStars) {
		if (numOfStars >= MIN_STARS && numOfStars <= MAX_STARS) {
			this.numOfStars = numOfStars;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the names of family members
      
      @param familyMemberNames The names of family members      
	  **/
   public void setFamilyMemberNames(String[] familyMemberNames) {
		for (int x = 0; x < super.getNumMembers(); x++) {
			this.familyMemberNames[x] = familyMemberNames[x];
		}
	}

	/**
      Set whether each family member requires ski lesson or not
      
      @param requireSkiLesson whether each family member requires ski lesson or not      
	  **/
   public void setRequireSkiLesson(boolean[] requireSkiLesson) {
		for (int x = 0; x < super.getNumMembers(); x++) {
			this.requireSkiLesson[x] = requireSkiLesson[x];
		}
	}

	/**
      Calculate the cost of the current ski resort vacation
      @return The calculated cost of the current beach house vacation
	  **/
   public double calculateCost() {
		double cost = this.getNumOfStars() * COST_PER_STAR;
		for (int x = 0; x < super.getNumMembers(); x++) {
			if (this.requireSkiLesson[x] == true) {
				cost += COST_PER_SKI_LESSON;
			}
		}
		return cost;
	}

	/**
      Create a string representation of family member names and if they require ski lesson
      @return The string representation of family member names and if they require ski lesson
	  **/
   public String familyMembersString() {
		String output = "";
		for (int x = 0; x < super.getNumMembers(); x++) {
			output += "      Name: " + this.familyMemberNames[x]
					+ "  |  Require Ski Lesson? ";
			if (this.requireSkiLesson[x] == true) {
				output += "Yes";
			} else {
				output += "No";
			}
			output += "\n";
		}
		return output;
	}

	/**
      Create a string representation of ski trip vacation
      @return The string representation of ski trip vacation
	  **/
   public String toString() {
		String output = "Vacation Type: Ski Resort";
		output += super.toString() + "\n   Number of stars: "
				+ this.getNumOfStars() + "\n   List of family members \n"
				+ familyMembersString() + "   Cost of this vacation: "
				+ String.format("   $%.2f", this.calculateCost()) + "\n\n";
		return output;
	}

}