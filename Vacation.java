public abstract class Vacation {
	public static int MAX_VACATION = 37;
	public static int MAX_MEMBERS = 10;
	public static int MIN_MEMBERS = 0;
	private String empFName;
	private String empLName;
	private String location;
	private String zipCode;
	private int numMembers;
	private static int numVacations;

	//Default constructor that increments everytime a Vacation object is created
   public Vacation() {
		numVacations++;
	}

	/**
   Constructor that sets empFName, empLName, numMembers, location, zipCode  
   and calls the default constructor
   @param empFName The first name of the employee
   @param empLName The last name of the employee
   @param numMembers The number of members in the family including the employee
   @param location The location of the vacation
   @param zipCode The zip code of the location       
   **/
   public Vacation(String empFName, String empLName, int numMembers,
			String location, String zipCode) {
		this();
		this.empFName = empFName;
		this.empLName = empLName;
		this.numMembers = numMembers;
		this.location = location;
		this.zipCode = zipCode;
	}

	/**
   The following methods returns the informations associated
   with the current instance of an object    
   **/
   public static int getNumVacations() {return numVacations;}
	public int getNumMembers() {return this.numMembers;}
	public String getEmpFName() {return this.empFName;}
	public String getEmpLName() {return this.empLName;}
	public String getLocation() {return this.location;}
	public String getZipCode() {return this.zipCode;}  
   public static void decrementNumVacation() {numVacations--;}
   
	/**
      Set the first name of the employee if it is valid
      @param empFName the first name to be set
      @return Whether or not the first name has been set
	  **/
   public boolean setEmpFName(String EmpFName) {
		if (validateName(EmpFName)) {
			this.empFName = empFName;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the last name of the employee if it is valid
      @param empLName the last name to be set
      @return Whether or not the last name has been set
	  **/
   public boolean setEmpLName(String empLName) {
		if (validateName(empLName)) {
			this.empLName = empLName;
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the location of the vacation if it is valid
      @param location The location to be set
      @return Whether or not the location has been set
	  **/
   public boolean setLocation(String location) {
		if (validateName(location)) {
			this.location = location;
			return true;
		} else {
			return false;
		}
	}

	
   /**
      Validate the name so that it contains all letters
      @param input The input to be validated
      @return Whether or not input is valid
	  **/
   public static boolean validateName(String input) {
		boolean valid = true;
		if (input.equals("")) {
			valid = false;
		} else {
			for (int x = 0; x < input.length(); x++) {
				if (!Character.isLetter(input.charAt(x))) {
					valid = false;
				}
			}
		}
		return valid;
	}

	/**
      Set the zip code of the vacation if it is valid
      @param zipCode the zip code to be set
      @return Whether or not the location has been set
	  **/
   public boolean setZipCode(String zipCode) {
		if (validateZipCode(zipCode)) {
			this.zipCode = zipCode;
			return true;
		} else {
			return false;
		}
	}

	/**
      Validate the zip so that is in correct format
      @param input The input to be validated
      @return Whether or not input is valid
	  **/
   public static boolean validateZipCode(String zipCode) {
		boolean valid = false;
		if (zipCode.length() == 10) {
			if (zipCode.charAt(5) == '-') {
				valid = true;
				if (valid) {
					for (int x = 0; x < zipCode.length(); x++) {
						if (Character.isLetter(zipCode.charAt(x))) {
							valid = false;
						} else if (x != 5) {
							if (!Character.isDigit(zipCode.charAt(x))) {
								valid = false;
							}
						}
					}
				}
			}
		}
		if (valid) {
			return true;
		} else {
			return false;
		}
	}

	/**
      Set the number of family members
      @param numMember The number of members to be set
      @return Whether or not the number of members has been set
	  **/
   public boolean setNumMembers(int numMember) {
		if (numMember < MAX_MEMBERS) {
			this.numMembers = numMembers;
			return true;
		} else {
			return false;
		}
	}

	//Abstract method that is has to be implemented by its subclass
   public abstract double calculateCost();
   
   
	/**
      @return The string representation of vacation object
	  **/
   public String toString() {
		String output = "";
		output += "\n   Employee Name: " + this.getEmpFName() + " "
				+ this.getEmpLName() + "\n   Location: " + this.getLocation()
				+ "\n   Zip Code: " + this.getZipCode()
				+ "\n   Number of family members: " + this.getNumMembers();
		return output;
	}

}