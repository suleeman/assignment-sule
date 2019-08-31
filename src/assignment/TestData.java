package assignment;

import java.util.ArrayList;

public class TestData {

	public static ArrayList<Veterinarian> prepareVeterinarianTestData(ArrayList<Veterinarian> listOfVeterinarians) {
		listOfVeterinarians.add(new Veterinarian("Ben Casey", 3));
		listOfVeterinarians.add(new Veterinarian("Hawkeye Pierce", 3));
		listOfVeterinarians.add(new Veterinarian("Doogie Howser", 1));
		return listOfVeterinarians;
	}

	public static ArrayList<Animal> prepareAnimalTestData(ArrayList<Animal> listOfAnimals) {
		listOfAnimals.add(new Animal("Fred Bear", "Insured", "Ben Casey", "HCF236788", 10));
		listOfAnimals.add(new Animal("Betty Bird", 7));
		listOfAnimals.add(new Animal("Bella Plant", "Insured", "Ben Casey", "HCF265123", 23));
		listOfAnimals.add(new Animal("Dagwood Dog", "Insured", "Doogie Howser", "HCF265988", 2));
		listOfAnimals.add(new Animal("Ernie", "Insured", "Ben Casey", "HCF134231", 1));
		listOfAnimals.add(new Animal("Tinkerbell", 1));
		listOfAnimals.add(new Animal("Slinky", 1));
		listOfAnimals.add(new Animal("Mickey Mouse", "Insured", "Ben Casey", "HCF234511", 1));
		return listOfAnimals;
	}

}
