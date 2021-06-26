package bridgelabz.WorkshopCollections;

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
	List<Hotel> addHotel(String hotelName, Double rateForRegularCustomer) throws HotelReservationException;

}
