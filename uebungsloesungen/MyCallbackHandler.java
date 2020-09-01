package de.axishotels.booking.service;

import de.axishotels.booking.types.CreateReservationResponse;
import de.axishotels.booking.types.GetHotelsResponse;

public class MyCallbackHandler extends BookingServiceCallbackHandler {

	public MyCallbackHandler(MyResultContainer resultContainer) {
		this.clientData = resultContainer;
	}
	
    @Override
	public void receiveResultgetHotels(GetHotelsResponse response) {
		System.out.println("Received response from getHotels.");
		((MyResultContainer) clientData).isDone = true;
	}

	@Override
	public void receiveErrorgetHotels(java.lang.Exception e) {
		System.out.println("Received error from getHotels:" + e.getMessage());
		((MyResultContainer) clientData).isDone = true;
	}

	@Override
	public void receiveResultcreateReservation(CreateReservationResponse response) {
		System.out.println("Received response from createReservation.");
		((MyResultContainer) clientData).isDone = true;
	}

	@Override
	public void receiveErrorcreateReservation(java.lang.Exception e) {
		System.out.println("Received error from createReservation:" + e.getMessage());
		((MyResultContainer) clientData).isDone = true;
	}
}
