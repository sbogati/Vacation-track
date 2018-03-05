/**
   Description:

   This program supports Gifts and Knickknacks to assist Monica to track
   vacations that is being awarded to its employees. A vacation contains
   up to 10 members including the employee itself. Employee can purchase two
   of the vacation types, either Ski Resort or Beach house. User must enter
   employee name, location, zip code, and number of family members who will
   go to the vacation.
   
   For beach house, 1-3 members costs $196, 4-7 members costs $306, 4-7 
   members cost $524. If the user buys hurricane inruance,
   it cost  $9.50 for each family member up to 5 family
   members, and $4.75 for each additional family member above 5 .
   
   For ski resort, cost is based on number of stars. Each costs costs $616. 
   An additional $49.95 is charged each family member who needs ski lessons
   
   This program will allow the user to add vacation, remove vacation, print house 
   vacation bills, print ski resort vacation bills, print vacation summary,
   and the option to quit the program   
**/
import javax.swing.JOptionPane;

public class VacationProgram {
	public static void main(String[] args) {
       //Create a array of vacations
		Vacation vacations[] = new Vacation[Vacation.MAX_VACATION];
      /* Continue prompting the user to enter in position information 
         until they indicate they are done
       */
		int menuChoice = getMenuOption();
		while (menuChoice != 6) {
			int position = Vacation.getNumVacations();			
			switch (menuChoice) {
			case 1:
				addVacation(vacations, position);
            storeLexiographically(vacations, Vacation.getNumVacations());
				break;
			case 2:
				removeVacation(vacations, position);
            storeLexiographically(vacations, Vacation.getNumVacations());
				break;
			case 3:
				printBills(vacations, position, true);
				break;
			case 4:
				printBills(vacations, position, false);
				break;
			case 5:
				printVacationSummary(vacations, position);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Error");
				break;
			}
			menuChoice = getMenuOption();
		}
	}

	/**
      Prompt Monica to enter Vacation information until each valid
      input has been entered. Ask relevant question based on the type of vacation.
            
      @param Vacation[] vacation The vacation array where the Vacation object will be stored
      @param postion The current position in an array
      return void
	*/
   private static void addVacation(Vacation[] vacations, int position) {
		if (position < vacations.length) {
			String empFName = getName("Enter Employee's first Name ");
			String empLName = getName("Enter " + empFName + "'s last Name ");
			int numMembers = getNumber(
					"Enter the number of family members (exluding Exployee) going to the vacation",
					Vacation.MIN_MEMBERS, Vacation.MAX_MEMBERS - 1) + 1;
			String location;
			boolean foundAdd;
			int indexAdd;
			do {
				foundAdd = false;
				location = getName("Enter the location of the vacation ");
				indexAdd = 0;
				while (indexAdd < position && !foundAdd) {
					if (location.equalsIgnoreCase(vacations[indexAdd]
							.getLocation())) {
						foundAdd = true;
					} else {
						++indexAdd;
					}
				}
				if (foundAdd) {
					JOptionPane
					.showMessageDialog(null,
							"This location has been already entered. Enter anoter location instead");
				}
			} while (foundAdd);
			String zipCode = getZipCode();

			Object[] options = { "Beach House", "Ski Resort" };
			int vacationType = JOptionPane.showOptionDialog(null,
					"What type of vacation are you entering?",
					"Create Vacation", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			switch (vacationType) {
			case 0:
				vacations[position] = new BeachHouse(empFName, empLName,
						numMembers, location, zipCode);
				break;
			case 1:
				vacations[position] = new SkiResort(empFName, empLName,
						numMembers, location, zipCode);
				break;
			default:
				vacations[position] = null;
				break;
			}
			if (vacations[position] instanceof BeachHouse) {
				((BeachHouse) vacations[position])
				.setRequireInsurance(JOptionPane
						.showConfirmDialog(null,
								"Would you like to purchase hurricane insurance") == JOptionPane.YES_OPTION);
			} else if (vacations[position] instanceof SkiResort) {
				SkiResort vacation = (SkiResort) vacations[position];
				vacation.setNumOfStars(getNumber(
						"Enter the number of stars this location is rated",
						SkiResort.MIN_STARS, SkiResort.MAX_STARS));
				vacation.setFamilyMemberNames(getFamilyMembers(numMembers,
						vacation));
				vacation.setRequireSkiLesson(getRequireLesson(numMembers,
						vacation));
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"The vacations are full. No more Vacation can be added.");
		}
	}

	/**
      Linear search for the vacation to be deleted. If the location is found,
      delete the vacation. If not, show error message. 
      
      @param Vacation[] vacation The vacation array where the Vacation objects are be stored
      @param postion The current position in an array
      return void
   */
   private static void removeVacation(Vacation[] vacations, int position) {
		String deleteVacation = "";
		String titleDisplay = "";
		if (position > 0) {
			for (int x = 0; x < position; x++) {
				titleDisplay += vacations[x].getLocation() + "\n";
			}
			do {
				deleteVacation = JOptionPane
						.showInputDialog("Enter the location of the vacation that you want to delete \n"
								+ titleDisplay);
				if (deleteVacation.equals("")) {
					JOptionPane.showMessageDialog(null,
							"Enter a location to delete");
				}
			} while (deleteVacation.equals(""));

			int index = 0;
			boolean foundRemove = false;
			while (index < position && !foundRemove) {
				if (deleteVacation.equalsIgnoreCase(vacations[index]
						.getLocation())) {
					foundRemove = true;
				} else {
					++index;
				}
			}
			if (foundRemove) {
				vacations[index] = vacations[position - 1];
				Vacation.decrementNumVacation();
				JOptionPane.showMessageDialog(null, deleteVacation
						+ " has been deleted.");
			} else {
				JOptionPane.showMessageDialog(null,
						"Location could not be found");
			}
		} else {
			JOptionPane.showMessageDialog(null, "No vacation has been entered");
		}
	}

	
   /**
      Compare the lexiographical order based on the location of the Vacation object.
      Then, order objects based on their location's lexiographical order. 
      
      @param Vacation[] vacation The vacation array where the Vacation object will be stored
      @param postion The current position in an array
      return void
   */
   private static void storeLexiographically(Vacation[] vacations, int position) {
		Vacation vacation;
		for (int x = 0; x < position - 1; x++) {
			for (int y = 1; y < position - x; y++) {
				if (vacations[y - 1].getLocation().compareToIgnoreCase(
						vacations[y].getLocation()) > 0) {
					vacation = vacations[y - 1];
					vacations[y - 1] = vacations[y];
					vacations[y] = vacation;
				}
			}
		}
	}

	/**
      Display vacation bill for that is well-formatted, listing the vacation type 
      and all information associated to the vacation 
      
      @param Vacation[] vacation The vacation array where the Vacation object will be stored
      @param postion The current position in an array
      @param beachHouse Whether or not the object is BeachHouse type
      return void
   */
   private static void printBills(Vacation[] vacations, int position,
			boolean beachHouse) {
		String display = "";
		for (int x = 0; x < position; x++) {
			if ((vacations[x] instanceof BeachHouse) == beachHouse) {
				display += vacations[x];
			}
		}		
		if (display.equals("")) {
			JOptionPane.showMessageDialog(null,
					"No Vacation of this type has been entered");
		}
      else{
         JOptionPane.showMessageDialog(null, display);
      }      
	}

	/**
      Input the names of family members for skiresort vacation 
      
      @param vacation The SkiResort object to ask the family member's name for
      @param numMembers The number of members in the family
      return The names of family members
   */
   private static String[] getFamilyMembers(int numMembers, SkiResort vacation) {
		String value[] = new String[numMembers];
		String empName = vacation.getEmpFName() + " " + vacation.getEmpFName();
		value[0] = empName;
		for (int x = 1; x < numMembers; x++) {
			value[x] = getName("Enter the no." + (x)
					+ " name of the family member ");
		}
		return value;
	}

	/**
      Input whether or not each family member requires ski trip lesson 
      
      @param vacation The SkiResort object to ask the family member's name for
      @param numMembers The number of members in the family
      return whether or not each family members require ski trip lesson
   */
   private static boolean[] getRequireLesson(int numMembers, SkiResort vacation) {
		String names[] = vacation.getFamilyMemberNames();
		boolean value[] = new boolean[numMembers];
		for (int x = 0; x < numMembers; x++) {
			value[x] = JOptionPane.showConfirmDialog(null, "Does " + names[x]
					+ " require ski lesson") == JOptionPane.YES_OPTION;
		}
		return value;
	}

	/**
      Input a valid number 
      
      @param message The message to display when inputting
      @param minNum The lower bound for the valid number
      @param maxNum The upper bound for the valid number
      return The valid number
   */
   private static int getNumber(String message, int minNum, int maxNum) {
		int value = 0;
		boolean valid = false;
		do {
			try {
				value = Integer.parseInt(JOptionPane.showInputDialog(message));
				if (value >= minNum && value <= maxNum) {
					valid = true;
				} else {
					JOptionPane.showMessageDialog(null,
							"Number must be between " + minNum + " and "
									+ maxNum);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error. Enter again");
			}
		} while (!valid);
		return value;
	}

	/**
      Input a valid zip code in the format of yyyyy-yyyy where y is a number
      
      @param message The message to display when inputting      
      return The valid zip code
   */
   private static String getZipCode() {
		String zipCode;
		do {
			zipCode = JOptionPane
					.showInputDialog("Enter the zip code in the format of yyyyy-yyyy where y is a number");
			if (!Vacation.validateZipCode(zipCode)) {
				JOptionPane
				.showMessageDialog(
						null,
						"Invalid zip code. Enter the zip code in the format of yyyyy-yyyy where y is a number. \nExample zip code is: 05755-6897 ");
			}
		} while (!Vacation.validateZipCode(zipCode));
		return zipCode;
	}

	/**
      Input a valid name that is all letters
      
      @param message The message to display when inputting      
      return The valid name
   */
   private static String getName(String message) {
		String value = "";
		do {
			value = JOptionPane.showInputDialog(message);
			if (!Vacation.validateName(value)) {
				JOptionPane.showMessageDialog(null,
						"Error. Enter a valid that are all Letters!");
			}
		} while (!Vacation.validateName(value));
		return value;
	}

	/**
      Display summary of all vacations that has been entered listing total number
      of vacations created and total amount of price among all vacations 
      
      @param Vacation[] vacation The vacation array where the Vacation object will be stored
      @param postion The current position in an array
      return void
   */
   private static void printVacationSummary(Vacation[] vacations, int position) {
		double totalCost = 0.00;
		for (int x = 0; x < position; x++) {
			totalCost += vacations[x].calculateCost();
		}
		String output = "Total Number of Vacations created: " + position
				+ "\nTotal vacation cost across all vacations: "
				+ String.format("$%.2f", totalCost);
		JOptionPane.showMessageDialog(null, output);
	}

	/**
      Get the optioins of the menu to be processed
      return The number associated with the menu option
	  */
   private static int getMenuOption() {
		int menuChoice;
		do {
			try {
				menuChoice = Integer.parseInt(JOptionPane
						.showInputDialog("Enter your selection:"
								+ "\n(1) Add a Vacation"
								+ "\n(2) Remove a Vacation"
								+ "\n(3) Print All Beach House Vacation Bills"
								+ "\n(4) Print All Ski Resort Vacation Bills"
								+ "\n(5) Print Print Vacation Summary"
								+ "\n(6) Quit "));
			} catch (NumberFormatException e) {
				menuChoice = 0;
			}
			if (menuChoice < 1 || menuChoice > 6) {
				JOptionPane.showMessageDialog(null,
						"Invalid choice. Please enter a valid menu option.");
			}
		} while (menuChoice < 1 || menuChoice > 6);
		return menuChoice;
	}
}