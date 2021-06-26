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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservationService implements IHotelReservationService {
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
	 * @return List<Hotel>
	 * @throws HotelReservationException
	 */
	@Override
	public List<Hotel> findCheapestHotels(LocalDate startDate, LocalDate endDate, CustomerType customerType)
			throws HotelReservationException {
		try {
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
			hotelList.stream().map(hotel -> calculateTotalCost(hotel, weekdayCount, weekendCount, customerType))
					.collect(Collectors.toList());
			List<Hotel> cheapestHotelList = filterCheapestHotels(hotelList);
			LOG.debug("Cheapest hotel " + cheapestHotelList.toString());
			return cheapestHotelList;
		} catch (Exception e) {
			throw new HotelReservationException(e.getMessage());
		}
	}

	/**
	 * Function to get cheapest hotels
	 * 
	 * @param hotelList
	 * @return
	 */
	private List<Hotel> filterCheapestHotels(List<Hotel> hotelList) {
		List<Hotel> cheapestHotelList = new ArrayList<Hotel>();
		if (null != hotelList && hotelList.size() > 0) {
			Double minTotalCost = hotelList.get(0).getTotalCost();
			for (Hotel hotel : hotelList) {
				if (hotel.getTotalCost().compareTo(minTotalCost) < 0) {
					minTotalCost = hotel.getTotalCost();
					cheapestHotelList.clear();
					cheapestHotelList.add(hotel);
				} else if (hotel.getTotalCost().compareTo(minTotalCost) == 0) {
					cheapestHotelList.add(hotel);
				}
			}
		}
		return cheapestHotelList;
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

	/**
	 * configuring rate for customer type
	 * 
	 * @return
	 * @throws HotelReservationException
	 */
	public List<Hotel> configureRateForCustomerType(String hotelName, CustomerType customerType, Double weekdayRate,
			Double weekendRate) throws HotelReservationException {
		try {
			Map<DayType, Double> dayTypeAndRateMap = new HashMap<DayType, Double>();
			dayTypeAndRateMap.put(DayType.WEEKDAY, weekdayRate);
			dayTypeAndRateMap.put(DayType.WEEKEND, weekendRate);
			hotelList.stream().filter(hotel -> hotel.getName().equalsIgnoreCase(hotelName))
					.map(hotel -> updateWeekDayRate(hotel, customerType, dayTypeAndRateMap))
					.collect(Collectors.toList());
			System.out.println(hotelList.toString());
			return hotelList;
		} catch (Exception e) {
			throw new HotelReservationException(e.getMessage());
		}
	}

	/**
	 * function to update week day rate for the given hotel and customer type
	 * 
	 * @param hotel
	 * @param customerType
	 * @param dayTypeAndRateMap
	 * @return
	 */
	private Hotel updateWeekDayRate(Hotel hotel, CustomerType customerType, Map<DayType, Double> dayTypeAndRateMap) {
		hotel.getRateMap().put(customerType, dayTypeAndRateMap);
		return hotel;
	}
}
