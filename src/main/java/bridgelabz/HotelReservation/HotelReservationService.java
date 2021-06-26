/**
 * Purpose:Hotel Reservation System- program to help find the customer the cheapest hotel.
 * @author Ananya K
 * @version 1.0
 * @since 26/06/2021
 * 
 */
package bridgelabz.HotelReservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservationService implements IHotelReservationService {
	Scanner scanner = new Scanner(System.in);
	private static final Logger LOG = LogManager.getLogger(HotelReservationService.class);
	List<Hotel> hotelList = new ArrayList<Hotel>();

	public HotelReservationService() {

	}

	public HotelReservationService(List<Hotel> hotelList) {
		this.hotelList = hotelList;
	}

	/**
	 * Function to add a hotel to the hotel list
	 * 
	 * @param hotel
	 * @return List<Hotel>
	 * @throws HotelReservationException
	 */
	@Override
	public List<Hotel> addHotel(Hotel hotel) throws HotelReservationException {
		try {
			hotelList.add(hotel);
			LOG.debug("Hotel added successfully " + hotelList.toString());
			return hotelList;
		} catch (Exception e) {
			throw new HotelReservationException(e.getMessage());
		}
	}

	/**
	 * Function to find a cheapest hotel for the given date
	 * 
	 * @param startDate,endDate,customerType
	 * @return cheapestHotel
	 * @throws HotelReservationException
	 */
	@Override
	public Hotel findCheapestHotel(LocalDate startDate, LocalDate endDate, CustomerType customerType)
			throws HotelReservationException {
		try {
			Hotel cheapestHotel = new Hotel();
			if (startDate == null || endDate == null) {
				throw new IllegalArgumentException(
						"Invalid method argument(s) to findCheapestHotelBetween(" + startDate + "," + endDate + ")");
			}
			Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
					|| date.getDayOfWeek() == DayOfWeek.SUNDAY;
			long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
			Long weekdayCount = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween)
					.filter(isWeekend.negate()).count();
			Long weekendCount = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysBetween).filter(isWeekend)
					.count();
			cheapestHotel = hotelList.stream()
					.map(hotel -> calculateTotalCost(hotel, weekdayCount, weekendCount, customerType))
					.min(Comparator.comparing(Hotel::getTotalCost)).orElseThrow(NoSuchElementException::new);
			LOG.debug(cheapestHotel.toString());
			return cheapestHotel;
		} catch (Exception e) {
			throw new HotelReservationException(e.getMessage());
		}
	}

	/**
	 * Function to calculate the total cost using the formula
	 * (weekdayCount*weekdayRateForCustomerType)+(weekendCount*weekendRateForCustomerType)
	 * 
	 * @param hotel
	 * @param weekdayCount
	 * @param weekendCount
	 * @param customerType
	 * @return hotel
	 */
	private Hotel calculateTotalCost(Hotel hotel, Long weekdayCount, Long weekendCount, CustomerType customerType) {
		Map<DayType, Double> rateMap = hotel.getRateMap().get(customerType);
		Double weekdayRate = rateMap.get(DayType.WEEKDAY);
		Double weekendRate = rateMap.get(DayType.WEEKEND);
		Double weekdayCost = weekdayCount * weekdayRate;
		Double weekendCost = weekendCount * weekendRate;
		Double totalCost = weekdayCost + weekendCost;
		hotel.setTotalCost(totalCost);
		return hotel;
	}

}
