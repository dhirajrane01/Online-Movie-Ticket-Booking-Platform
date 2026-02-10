/*WRITE Scenario (Implemented)
Book movie tickets (seat selection)*/
/*
Booking Transaction Flow :
1.Lock seats (Redis)
2.Validate availability
3.Apply offers
4.Create booking
5.Update seat inventory
6.Trigger payment*/



@Transactional
public BookingResponse bookTickets(BookingRequest request) {

    seatLockService.lock(request.getShowId(), request.getSeatIds());

    if (!seatInventoryService.areAvailable(
            request.getShowId(), request.getSeatIds())) {
        throw new SeatUnavailableException();
    }

    BigDecimal price =
        pricingService.calculate(request);

    Booking booking =
        bookingRepository.save(
            Booking.create(request, price));

    seatInventoryService.markBooked(
        request.getShowId(), request.getSeatIds());

    return new BookingResponse(
        booking.getId(), price, "CONFIRMED");
}



