package bridgelabz.HotelReservation;

import java.time.LocalDate;
import java.util.List;

public interface IHotelReservationService {
	/**
	 * Function to add a hotel with the given name and rate for regular customer
	 * 
	 * @param hotelName
	 * @param rateForRegularCustomer
	 * @return
	 * @throws HotelReservationException
	 */
	List<Hotel> addHotel(String hotelName) throws HotelReservationException;

	/**
	 * tThis method finds the cheapest hotel
	 * 
	 * @param startDate
	 * @param endDate
	 * @param customerType
	 * @return
	 * @throws HotelReservationException
	 */
	Hotel findCheapestHotel(LocalDate startDate, LocalDate endDate, CustomerType customerType)
			throws HotelReservationException;

}
