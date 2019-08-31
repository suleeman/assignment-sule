package assignment;

import java.util.*;

public class Veterinarian {

	// private String specialisation; // Veterinarian's special skill
	public ArrayList<Animal> listOfAnimals; // Veterinarian's personal list of animals responsible for care
	public int maxNumOfAnimals; // Maximum no. of animals a Veterinarian can have at one time
	private double hourlyRate; // Hourly rate of pay
	private static int vetIdNo = 1000; // Internal use only - no get/set methods
	public String name; // Name of Veterinarian
	// private int age; // Age of Veterinarian
	private String identifier; // Veterinarian's staff id

	public final boolean HasAnimals() { // does the Vet have any animals registered for care?
		return !listOfAnimals.isEmpty();
	}

	public Veterinarian() { // constructor to set up a Veterinarian object
		listOfAnimals = new ArrayList<Animal>(); // initially no animals registered
		listOfAnimals.clear();
		maxNumOfAnimals = 0; // maximum number of animals that can be registered to the Vet
		hourlyRate = 100;
		this.setIdentifier();
	}

	public Veterinarian(String name, int animals2) {
		this.name = name;
		listOfAnimals = new ArrayList<Animal>();
		listOfAnimals.clear();
		maxNumOfAnimals = animals2;
		hourlyRate = 100;
		this.setIdentifier();
	}

	public Veterinarian(String name, int animals2, double hourlyRate) {
		this.name = name;
		listOfAnimals = new ArrayList<Animal>();
		listOfAnimals.clear();
		maxNumOfAnimals = animals2;
		this.setIdentifier();
		this.hourlyRate = hourlyRate;
	}

	public String toString() {
		return "*******************\n Dr " + this.name + "\n\t id number: " + identifier
				+ "\n\t Max Number of Patients: " + this.maxNumOfAnimals;
	}

	public final double getHourlyRate() {
		return this.hourlyRate;
	}

	public final String printListOfAnimals() {
		String temp = "";
		temp += "\nList of Animals registered for Dr " + this.name + "\n\n";
		for (int i = 0; i < this.listOfAnimals.size(); i++) {
			temp += ((Animal) this.listOfAnimals.get(i)) + "\n";
		}
		return temp;
	}

	public final void setIdentifier() {
		this.identifier = "V" + String.valueOf(vetIdNo++);
	}

}