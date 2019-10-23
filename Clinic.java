import java.util.ArrayList;

public class Clinic {
	
	/*
	* The name of the Clinic.
	*/
	private String name; 

	/*
	* The arrayList of services the Clinic provides.
	*/
	private ArrayList<Service> services;

	/*
	* The arraylist of employees who work at the clinic.
	*/ 
	private ArrayList<Employee> employees;

	/*
	* The array of hours the Clinic opens at.
	*/
	private int[] openHours;

	/*
	* The array of hours the Clinic closes at.
	*/
	private int[] closeHours;


	/**
	* Creates a new Clinic.
	* @param name a string with the name of the Clinic
	*/
	public Clinic(String name) {
		this.name = name;
		this.services = new ArrayList<Service>();
	}


	//These methods will be properly implemented when Clinics are added


	/**
	* Adds a service to the Clinic.
	* @param service the Service to be added to the clinic
	*/
	public void addService(Service service) {
		this.services.add(service);
	}

	/**
	* Removes a service the Clinic no longer offers.
	* @param service the Service to be removed from the Clinic
	*/
	public void removeService(Service service) {
		if (this.services.contains(service)) {
			this.services.remove(service);
		} else {
			throw new IllegalArgumentException("Service not in Clinic.");
		}

	}

	/**
	* Sets the hours of operation of the Clinic.
	*/
	public void setHours() {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	/**
	* Adds the employee to the clinic.
	* @param employee the employee to be added
	*/
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
}