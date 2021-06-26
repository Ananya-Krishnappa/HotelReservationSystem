package bridgelabz.HotelReservation;

import java.util.Map;

public class Hotel {
	String name;
	int rating;
	Map<CustomerType, Map<DayType, Double>> rateMap;
	Double totalCost;

	public Hotel(String name, int rating, Map<CustomerType, Map<DayType, Double>> rateMap, Double totalCost) {
		this.name = name;
		this.rating = rating;
		this.rateMap = rateMap;
		this.totalCost = totalCost;
	}

	public Hotel() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Map<CustomerType, Map<DayType, Double>> getRateMap() {
		return rateMap;
	}

	public void setRateMap(Map<CustomerType, Map<DayType, Double>> rateMap) {
		this.rateMap = rateMap;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", rating=" + rating + ", rateMap=" + rateMap + ", totalCost=" + totalCost + "]";
	}
}
