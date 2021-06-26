package bridgelabz.HotelReservation;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservationMain {
	private static final Logger LOG = LogManager.getLogger(HotelReservationMain.class);

	public static void main(String[] args) throws NumberFormatException, HotelReservationException {
		IHotelReservationService hotelReservationService = new HotelReservationService();
		LOG.debug("Welcome to hotel reservation system");
		Scanner scanner = new Scanner(System.in);
		LOG.debug("Enter hotel name");
		String hotelName = scanner.next();
		hotelReservationService.addHotel(hotelName);
		LOG.debug("Enter hotel name");
		hotelName = scanner.next();
		hotelReservationService.addHotel(hotelName);
		LOG.debug("Enter hotel name");
		hotelName = scanner.next();
		hotelReservationService.addHotel(hotelName);
		hotelReservationService.findCheapestHotel(LocalDate.now(), LocalDate.of(2021, 6, 30), CustomerType.REGULAR);
		scanner.close();
	}
}
