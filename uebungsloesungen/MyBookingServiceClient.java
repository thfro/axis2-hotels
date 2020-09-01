package de.axishotels.booking.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import de.axishotels.booking.types.CreateReservationRequest;
import de.axishotels.booking.types.CreateReservationResponse;
import de.axishotels.booking.types.GetHotelsRequest;
import de.axishotels.booking.types.GetHotelsResponse;
import de.axishotels.booking.types.Hotel;
import de.axishotels.booking.types.Reservation;

public class MyBookingServiceClient {

	public static void main(String[] args) throws Exception {
		BookingServiceStub stub = new BookingServiceStub("http://localhost:8080/axis2/services/BookingService");

		GetHotelsRequest request = new GetHotelsRequest();
		request.setCity("Hamburg");
		request.setNumberOfStars(3);

		GetHotelsResponse response = stub.getHotels(request);
		
		System.out.println("Hotel(s) returned by booking service: ");
		for (Hotel hotel : response.getHotels()) {
			System.out.println(hotel.getHotelName());
		}
		
		if (response.getHotels().length > 0) {
			Hotel hotel = response.getHotels()[0];
			String roomCode = hotel.getRoomTypes()[0].getRoomCode();

			Reservation reservation = new Reservation();
			reservation.setHotelCode(hotel.getHotelCode());
			reservation.setNumberOfRooms(1);
			reservation.setRoomCode(roomCode);
			reservation.setArrivalDate(toDate(LocalDate.of(2020, Month.SEPTEMBER, 15)));
			reservation.setDepartureDate(toDate(LocalDate.of(2020, Month.SEPTEMBER, 18)));
			
			CreateReservationRequest request2 = new CreateReservationRequest();
			request2.setReservation(reservation);
			
			CreateReservationResponse response2 = stub.createReservation(request2);
			System.out.println("Reservation number: " + response2.getConfirmation().getReservationNumber());
			System.out.println("Status: " + response2.getConfirmation().getStatus());
			
		}
		
		// async
		MyResultContainer resultContainer = new MyResultContainer();
		MyCallbackHandler cb = new MyCallbackHandler(resultContainer);
		stub.startgetHotels(request, cb);

		while (!resultContainer.isDone) {
			System.out.println("Doing something different...");
			Thread.sleep(5);
		}
	}
	
	private static Date toDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
}
