package bridgelabz.WorkshopCollections;

import java.util.List;

public interface IHotelReservationService {

	List<Hotel> addHotel(String hotelName, Double rateForRegularCustomer) throws HotelReservationException;

}
