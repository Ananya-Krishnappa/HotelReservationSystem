package bridgelabz.HotelReservation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class HotelReservationServiceTest {

	HotelReservationService hotelReservationService;
	List<Hotel> hotelList = new ArrayList<Hotel>();

	@Before
	public void init() {
		hotelReservationService = new HotelReservationService();
	}

	private List<Hotel> createHotelList(String name, int rating, Double[] rateArray) {
		Hotel hotel = new Hotel();
		hotel.setName(name);
		hotel.setRating(rating);
		Map<CustomerType, Map<DayType, Double>> rateMap = new HashMap<CustomerType, Map<DayType, Double>>();
		Map<DayType, Double> dayTypeAndRateMap = new HashMap<DayType, Double>();
		dayTypeAndRateMap.put(DayType.WEEKDAY, rateArray[0]);
		dayTypeAndRateMap.put(DayType.WEEKEND, rateArray[1]);
		rateMap.put(CustomerType.REGULAR, dayTypeAndRateMap);
		dayTypeAndRateMap.clear();
		dayTypeAndRateMap.put(DayType.WEEKDAY, rateArray[2]);
		dayTypeAndRateMap.put(DayType.WEEKEND, rateArray[3]);
		rateMap.put(CustomerType.REWARDS, dayTypeAndRateMap);
		hotel.setRateMap(rateMap);
		hotelList.add(hotel);
		return hotelList;
	}

	@Test
	public void validate_shouldAddHotelToTheListWhenGivenHotelNameAndRate() throws HotelReservationException {
		String name = "Lakewood";
		int rating = 3;
		Double[] rateArray = { 100.0, 120.0, 123.9, 789.0 };
		createHotelList(name, rating, rateArray);
		String name1 = "Bridgewood";
		int rating1 = 4;
		Double[] rateArray1 = { 200.0, 420.0, 120.9, 189.0 };
		createHotelList(name1, rating1, rateArray1);
		String name2 = "Ridgewood";
		int rating2 = 3;
		Double[] rateArray2 = { 500.0, 920.0, 823.9, 589.0 };
		createHotelList(name2, rating2, rateArray2);

		assertThat(hotelList, hasSize(3));
	}

}
