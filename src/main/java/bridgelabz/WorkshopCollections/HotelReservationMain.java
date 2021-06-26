package bridgelabz.WorkshopCollections;

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
		LOG.debug("Enter Rate for regular customer");
		String rateForRegularCustomer = scanner.next();
		hotelReservationService.addHotel(hotelName, Double.valueOf(rateForRegularCustomer));
		LOG.debug("Enter hotel name");
		hotelName = scanner.next();
		LOG.debug("Enter Rate for regular customer");
		rateForRegularCustomer = scanner.next();
		hotelReservationService.addHotel(hotelName, Double.valueOf(rateForRegularCustomer));
		LOG.debug("Enter hotel name");
		hotelName = scanner.next();
		LOG.debug("Enter Rate for regular customer");
		rateForRegularCustomer = scanner.next();
		hotelReservationService.addHotel(hotelName, Double.valueOf(rateForRegularCustomer));
		scanner.close();
	}
}
