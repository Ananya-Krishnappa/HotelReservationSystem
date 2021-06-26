package bridgelabz.WorkshopCollections;

public class Hotel {
	String name;
	Double rateForRegularCustomer;

	public Hotel(String name, Double rateForRegularCustomer) {
		this.name = name;
		this.rateForRegularCustomer = rateForRegularCustomer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getRateForRegularCustomer() {
		return rateForRegularCustomer;
	}

	public void setRateForRegularCustomer(Double rateForRegularCustomer) {
		this.rateForRegularCustomer = rateForRegularCustomer;
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", rateForRegularCustomer=" + rateForRegularCustomer + "]";
	}

}
