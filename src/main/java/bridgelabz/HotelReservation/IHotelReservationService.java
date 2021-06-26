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
	 * @return
	 * @throws HotelReservationException
	 */
	Hotel findCheapestHotel(LocalDate startDate, LocalDate endDate, CustomerType customerType)
			throws HotelReservationException;

}
