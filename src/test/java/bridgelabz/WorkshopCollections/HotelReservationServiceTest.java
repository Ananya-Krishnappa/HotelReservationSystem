package bridgelabz.WorkshopCollections;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

public class HotelReservationServiceTest {

	HotelReservationService hotelReservationService;
	List<Hotel> hotelList = new ArrayList<Hotel>();

	@Before
	public void init() {
		hotelReservationService = new HotelReservationService();
	}

	@Test
	public void validate_shouldAddHotelToTheListWhenGivenHotelNameAndRate() throws HotelReservationException {
		hotelList = hotelReservationService.addHotel("Lakewood", Double.valueOf(110));
		assertThat(hotelList, hasSize(1));
	}
}
