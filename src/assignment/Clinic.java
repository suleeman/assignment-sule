package assignment;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Clinic {
	private ArrayList<Veterinarian> listOfVeterinarians;
	private ArrayList<Animal> listOfAnimals;
	private String inputFileName;

	public Clinic() {
		listOfVeterinarians = new ArrayList<Veterinarian>();
		listOfVeterinarians.clear();
		listOfAnimals = new ArrayList<Animal>();
		listOfAnimals.clear();
		inputFileName = "MyInput.csv";
	}

	public final String run() {
		String output = "";

		System.out.println("Begin run\n");
		// set up data for clinic

		try {
			File inputFile = new File(inputFileName);
			Scanner s = new Scanner(inputFile);
			String vName, line, tokens[], aName, aPreferredVet, aInsuranceNo;
			int vMax, aHoursTreated;
			while (s.hasNext()) {
				line = s.nextLine();
				tokens = line.split(",");

				switch (tokens[0]) {
				case "Veterinarian":
					vName = tokens[1];
					// vAge = Integer.valueOf(tokens[2]);
					vMax = Integer.valueOf(tokens[3]);
					// vSpeciality = tokens[4];
					this.listOfVeterinarians.add(new Veterinarian(vName, vMax));
					break;
				case "InsuredAnimal":
					aName = tokens[1];
					// aAge = Integer.valueOf(tokens[2]);
					aPreferredVet = tokens[3];
					aInsuranceNo = tokens[4];
					aHoursTreated = Integer.valueOf(tokens[5]);
					// aBreed = tokens[6];
					this.listOfAnimals.add(new Animal(aName, "Insured", aPreferredVet, aInsuranceNo, aHoursTreated));

					break;
				case "Animal":
					aName = tokens[1];
					// aAge = Integer.valueOf(tokens[2]);
					aHoursTreated = Integer.valueOf(tokens[3]);
					// aBreed = tokens[4];
					this.listOfAnimals.add(new Animal(aName, aHoursTreated));
					break;
				}
			}
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
			this.listOfVeterinarians = TestData.prepareVeterinarianTestData(this.listOfVeterinarians);
			// set up animal data
			this.listOfAnimals = TestData.prepareAnimalTestData(this.listOfAnimals);
		}

		// Add details of data to output string

		output += "___________________\n\nList of registered veterinarians\n___________________\n\n";
		output = iterateListOfVeterinarians(output);

		output += "\n";

		output += "\n___________________\n\nList of animals before veterinarians assigned\n___________________\n\n";
		for (int i = 0; i < this.listOfAnimals.size(); i++) {
			output += ((Animal) this.listOfAnimals.get(i)).toString() + "\n";
		}

		// assign animals to a particular veterinarian in this clinic

		output += "\n___________________\n\n Assigning Veterinarians to Animals\n___________________\n";
		ArrayList<Animal> list = this.listOfAnimals;
		for (int i = 0; i < list.size(); i++) {
			Animal temp = (Animal) list.get(i);
			if (this.listOfVeterinarians == null) {
				output += "- No available veterinarians\n*******************\n";
				break; // go no further will allocations, list of Veterinarians is empty - may need to
						// check for availability in another clinic in a later version
			}
			if (temp.assignVeterinarian(this.listOfVeterinarians) == false) { // attempt to assign animal to a
																				// veterinarian
				output += "\n********************************************************************\n Not yet assigned animal "
						+ temp.getName()
						+ "- No available veterinarians\n********************************************************************\n";
				output += ((Animal) list.get(i)).mv(temp.animalType);
			} else {
				output += "Assigning veterinarian " + temp.assignedVeterinarian.name + " to " + temp.getName() + "\n";
			}
		}

		// Add new information of assignments to output string

		output += "\n___________________\n\nList of animals after veterinarians assigned\n___________________\n";
		for (int i = 0; i < this.listOfAnimals.size(); i++) {
			output += ((Animal) this.listOfAnimals.get(i)).toString() + "\n";
		}

		output += "___________________\n\nList of veterinarians after animals assigned\n___________________\n" + "\n";
		output = iterateListOfVeterinarians(output);

		return output; // return string to calling method to print out
	}

	/**
	 * @param output
	 * @return
	 */
	private String iterateListOfVeterinarians(String output) {
		for (int i = 0; i < this.listOfVeterinarians.size(); i++) {
			Veterinarian temp = ((Veterinarian) this.listOfVeterinarians.get(i));
			output += temp.toString() + "\n";
			if (temp.HasAnimals()) {
				output += temp.printListOfAnimals();
			} else {
				output += "No animals assigned to this veterinarian as yet";
			}
			output += "\n";
		}
		return output;
	}
}
