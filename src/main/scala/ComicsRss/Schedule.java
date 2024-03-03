package ComicsRss;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.Function;

public enum Schedule {

	SevenDays(d -> true), MondayThroughSaturday(d -> d.getDayOfWeek() != DayOfWeek.SUNDAY), Sunday(d -> d.getDayOfWeek() == DayOfWeek.SUNDAY);

	private final Function<LocalDate, Boolean> acceptor;

	Schedule(final Function<LocalDate, Boolean> acceptor) {
		this.acceptor = acceptor;
	}

	public boolean accept(LocalDate date) {
		return acceptor.apply(date);
	}

}
