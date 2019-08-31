package assignment;

import java.util.*;

public class Animal {

	public Veterinarian assignedVeterinarian = null; // Veterinarian assigned to Animal
	private static int pIdNo = 100000; // No get and set methods - internal use only
	private String name;
	private String identifier; // Animal identifier - unique for each animal in the system
	public String animalType = "Uninsured"; // can be "Insured" or "Uninsured" - Uninsured should be "default"
	private String preferredVeterinarian = null; // Only useful for "Insured" - null for Uninsured animal
	private String insuredHealthFundNo; // Fund number for animals with insured health
	private int hoursTreated; // No of hours animal treated by Veterinarian

	public final String getName() {
		return name;
	}

	public Animal(String name, int hoursTreated) { // constructor to create an uninsured animal
		this.name = name;
		assignedVeterinarian = null;
		this.SetIdentifier();
		this.preferredVeterinarian = null;
		this.insuredHealthFundNo = null;
		this.hoursTreated = hoursTreated;
	}

	public Animal(String name, String animalType, String preferredVeterinarian, String insuredHealthFundNo,
			int hoursTreated) { // create insured animal
		this.name = name;
		// this.age = age;
		assignedVeterinarian = null;
		this.SetIdentifier();
		this.animalType = animalType;
		this.preferredVeterinarian = preferredVeterinarian;
		this.insuredHealthFundNo = insuredHealthFundNo;
		this.hoursTreated = hoursTreated;
		// this.animalBreed = aBreed;
	}

	public final boolean assignVeterinarian(ArrayList<Veterinarian> listOfVeterinarians) {
		if (animalType.equals("Insured")) {
			return insuredVeterinarians(listOfVeterinarians);
		} else if (animalType.equals("Uninsured")) {
			return uninsuredVeterinarians(listOfVeterinarians);
		}
		return false;
	}

	private boolean insuredVeterinarians(ArrayList<Veterinarian> listOfVeterinarians) {
		for (int i = 0; i < listOfVeterinarians.size(); i++) {
			Veterinarian temp = (Veterinarian) listOfVeterinarians.get(i);
			if (this.preferredVeterinarian.equals(temp.name)) { // is this the preferred vet?
				if (checkAnimalSize(temp.listOfAnimals.size(), temp.maxNumOfAnimals)) { // allocate animal to this vet -
																						// if possible
					// assignedVeterinarian = temp;
					// temp.listOfAnimals.add(this);
					addAnimalToList(temp);
					return true;
				} else // that preferred vet has no capacity for new animals on their list, need to
						// find another vet at this clinic
				{
					int vets = listOfVeterinarians.size();
					mvToANewVet(this.animalType);
					for (int ii = 0; ii < vets; ii++) { // choose which vet to allocate - find a vet with room left
														// in list of registered animals
						Veterinarian temp2 = (Veterinarian) (listOfVeterinarians.get(ii));
						if (checkAnimalSize(temp2.listOfAnimals.size(), temp2.maxNumOfAnimals)) {

							addAnimalToList(temp2);
							// temp2.listOfAnimals.add(this);
							System.out.println("Allocating " + this.getName() + " to " + temp2.name + "\n");
							// this.assignedVeterinarian = temp2;
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	private boolean uninsuredVeterinarians(ArrayList<Veterinarian> listOfVeterinarians) {
		int vets = listOfVeterinarians.size();

		for (int i = 0; i < vets; i++) { // choose which vet to allocate - search for a vet at this clinic with room
											// left in list of registered animals
			Veterinarian temp = (Veterinarian) (listOfVeterinarians.get(i));
			if (checkAnimalSize(temp.listOfAnimals.size(), temp.maxNumOfAnimals)) {
				addAnimalToList(temp);
				return true;
			}
		}

		return false;
	}

	private void addAnimalToList(Veterinarian temp) {
		temp.listOfAnimals.add(this);
		this.assignedVeterinarian = temp;
	}

	public boolean checkAnimalSize(int listSize, int maxNumOfAnimals) {
		return listSize < maxNumOfAnimals;
	}

	public final String mvToANewVet(String animalInsuranceType) {
		String output = "";
		if (animalInsuranceType.equals("Uninsured")) {
			output += "Assigning uninsured animal " + this.name
					+ " to the waiting list until a veterinarian becomes available somewhere\n";
		} else if (animalInsuranceType.equals("Insured")) {
			output += "Preferred Veterinarian unavailable. Reassigning Insured Animal " + this.name
					+ " to a different Vet at this clinic\n";
		}
		System.out.print(output);
		return output;
	}

	public final String mv(String animalInsuranceType) {
		String output = "";
		if (animalInsuranceType.equals("Uninsured")) {
			output += "Will need to assign uninsured animal " + this.name
					+ " to the waiting list until a veterinarian becomes available\n";
		} else if (animalInsuranceType.equals("Insured")) {
			output += "Will need to reassign Insured Animal " + this.name + " to a different clinic\n";
		}
		System.out.print(output);
		return output;
	}

	public final void SetIdentifier() {
		this.identifier = "P" + pIdNo++;
	}

	public String toString() {
		if (animalType.equals("Uninsured")) {
			String temp = "Uninsured Animal - " + this.name + "\n\t Identifier: " + this.identifier
					+ "\n\t Assigned Veterinarian: ";

			String temp2;
			if (this.assignedVeterinarian == null) {
				temp2 = "not assigned as yet";
			} else {
				temp2 = this.assignedVeterinarian.name + "\n\t Fee for " + this.hoursTreated + " hours consultation = $"
						+ this.hoursTreated * this.assignedVeterinarian.getHourlyRate();
			}
			return temp + temp2 + "\n";
		} else { // if (animalType == "Insured")
			String temp = "Insured Animal - " + this.name + "\n\t Identifier: " + this.identifier
					+ "\n\t Preferred Veterinarian: " + this.preferredVeterinarian + "\n\t Insured Health Fund Number: "
					+ this.insuredHealthFundNo + "\n\t Assigned Veterinarian: ";
			String temp2;
			if (this.assignedVeterinarian == null) {
				temp2 = "not assigned as yet";
			} else {
				temp2 = this.assignedVeterinarian.name + "\n\t Fee for " + this.hoursTreated + " hours consultation = $"
						+ this.hoursTreated * this.assignedVeterinarian.getHourlyRate();
			}
			return temp + temp2 + "\n";
		}
	}

}
