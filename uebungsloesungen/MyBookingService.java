package de.axishotels.booking.service;

import java.util.Random;

import de.axishotels.booking.types.Confirmation;
import de.axishotels.booking.types.CreateReservationRequest;
import de.axishotels.booking.types.CreateReservationResponse;
import de.axishotels.booking.types.GetHotelsRequest;
import de.axishotels.booking.types.GetHotelsResponse;
import de.axishotels.booking.types.Hotel;
import de.axishotels.booking.types.RoomType;

public class MyBookingService implements BookingServiceSkeletonInterface {

	@Override
	public GetHotelsResponse getHotels(GetHotelsRequest request) {
		String city = request.getCity();
		int numberOfStars = request.getNumberOfStars();

		Hotel testHotel = new Hotel();
		testHotel.setHotelName("Test Hotel");
		testHotel.setHotelCode("TST");

		RoomType rt = new RoomType();
		rt.setRoomCode("Double");
		rt.setNumberOfBeds(2);
		rt.setPrice(97.50f);
		testHotel.addRoomTypes(rt);

		testHotel.setNumberOfStars(numberOfStars);
		testHotel.setCity(city);

		GetHotelsResponse response = new GetHotelsResponse();
		response.addHotels(testHotel);
		return response;
	}

	@Override
	public CreateReservationResponse createReservation(CreateReservationRequest createReservationRequest) {
		Confirmation confirmation = new Confirmation();
		confirmation.setReservationNumber(new Random().nextInt());
		confirmation.setStatus("BOOKED");
		
		CreateReservationResponse response = new CreateReservationResponse();
		response.setConfirmation(confirmation);
		return response;
	}
}