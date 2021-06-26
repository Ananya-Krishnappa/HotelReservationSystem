/**
 * Purpose:Hotel Reservation System- program to help find the customer the cheapest hotel.
 * @author Ananya K
 * @version 1.0
 * @since 26/06/2021
 * 
 */
package bridgelabz.WorkshopCollections;

import java.util.ArrayList;
import java.util.List;

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
	 * Function to add a hotel with the given name and rate for regular customer
	 * 
	 * @param hotelName
	 * @param rateForRegularCustomer
	 * @return
	 * @throws HotelReservationException
	 */
	@Override
	public List<Hotel> addHotel(String hotelName, Double rateForRegularCustomer) throws HotelReservationException {
		try {
			Hotel hotel = new Hotel(hotelName, rateForRegularCustomer);
			hotelList.add(hotel);
			LOG.debug("Hotel added successfully " + hotelList.toString());
			return hotelList;
		} catch (Exception e) {
			throw new HotelReservationException(e.getMessage());
		}

	}
}
