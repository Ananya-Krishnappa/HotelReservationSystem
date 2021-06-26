package bridgelabz.HotelReservation;

import java.time.LocalDate;
import java.util.List;

public interface IHotelReservationService {
	/**
	 * Function to add a hotel to the hotel list
	 * 
	 * @param hotel
	 * @return List<Hotel>
	 * @throws HotelReservationException
	 */
	List<Hotel> addHotel(Hotel hotel) throws HotelReservationException;

	/**
	 * This method finds the cheapest hotel
	 * 
	 * @param startDate
	 * @param endDate
	 * @param customerType
	 * @return List<Hotel>
	 * @throws HotelReservationException
	 */
	List<Hotel> findCheapestHotels(LocalDate startDate, LocalDate endDate, CustomerType customerType)
			throws HotelReservationException;

	/**
	 * Function to configure rate for customer based on type
	 * 
	 * @param hotelName
	 * @param customerType
	 * @param weekdayRate
	 * @param weekendRate
	 * @return List<Hotel>
	 * @throws HotelReservationException
	 */
	List<Hotel> configureRateForCustomerType(String hotelName, CustomerType customerType, Double weekdayRate,
			Double weekendRate) throws HotelReservationException;

	/**
	 * This method is used to add ratings for hotel
	 * 
	 * @param hotelName
	 * @param rating
	 * @return List<Hotel>
	 * @throws HotelReservationException
	 */
	List<Hotel> updateRatingsForHotel(String hotelName, int rating) throws HotelReservationException;

	/**
	 * This method id used to find the best rated hotel
	 * 
	 * @param startDate
	 * @param endDate
	 * @param customerType
	 * @return Hotel
	 * @throws HotelReservationException
	 */
	Hotel findCheapestBestRatedHotel(LocalDate startDate, LocalDate endDate, CustomerType customerType)
			throws HotelReservationException;
}
