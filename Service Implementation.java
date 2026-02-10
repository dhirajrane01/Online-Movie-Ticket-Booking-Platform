/* READ Scenario (Implemented)
Browse theatres running a selected movie in a city on a date*/



@Service
public class BrowseShowService {

    private final ShowRepository showRepository;
    private final SeatInventoryRepository seatRepo;

    public List<ShowResponse> browseShows(
            Long movieId, String city, LocalDate date) {

        List<Show> shows =
            showRepository.findByMovieCityAndDate(movieId, city, date);

        return shows.stream().map(show -> {
            int availableSeats =
                seatRepo.countByShowIdAndStatus(
                    show.getId(), SeatStatus.AVAILABLE);

            return new ShowResponse(
                show.getTheatre().getName(),
                show.getShowTime(),
                availableSeats
            );
        }).toList();
    }
}
