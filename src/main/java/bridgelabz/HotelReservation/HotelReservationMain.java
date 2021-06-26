package bridgelabz.HotelReservation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservationMain {
	private static final Logger LOG = LogManager.getLogger(HotelReservationMain.class);
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws NumberFormatException, HotelReservationException {
		IHotelReservationService hotelReservationService = new HotelReservationService();
		LOG.debug("Welcome to hotel reservation system");
		hotelReservationService.addHotel(createHotel());
		hotelReservationService.addHotel(createHotel());
		hotelReservationService.addHotel(createHotel());
		hotelReservationService.findCheapestHotel(LocalDate.now(), LocalDate.of(2021, 6, 30), CustomerType.REGULAR);
		scanner.close();
	}

	/**
	 * create a new hotel
	 * 
	 * @param hotel
	 * @return Hotel
	 */
	private static Hotel createHotel() {
		Hotel hotel = new Hotel();
		LOG.debug("Enter hotel name");
		String hotelName = scanner.next();
		hotel.setName(hotelName);
		LOG.debug("Enter rating");
		int rating = scanner.nextInt();
		hotel.setRating(rating);
		Map<CustomerType, Map<DayType, Double>> rateMap = new HashMap<CustomerType, Map<DayType, Double>>();
		rateMap.put(CustomerType.REGULAR, configureRateForCustomerType(CustomerType.REGULAR));
		rateMap.put(CustomerType.REWARDS, configureRateForCustomerType(CustomerType.REWARDS));
		hotel.setRateMap(rateMap);
		return hotel;
	}

	/**
	 * configuring rate for customer type
	 * 
	 * @param customerType
	 * @return
	 */
	private static Map<DayType, Double> configureRateForCustomerType(CustomerType customerType) {
		Map<DayType, Double> dayTypeAndRateMap = new HashMap<DayType, Double>();
		LOG.debug("Enter rates for " + customerType.name() + " Customer");
		LOG.debug("Enter weekday rate");
		String weekdayRate = scanner.next();
		dayTypeAndRateMap.put(DayType.WEEKDAY, Double.valueOf(weekdayRate));
		LOG.debug("Enter weekend rate");
		String weekendRate = scanner.next();
		dayTypeAndRateMap.put(DayType.WEEKEND, Double.valueOf(weekendRate));
		return dayTypeAndRateMap;
	}
}
