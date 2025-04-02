import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateRange {
	private LocalDateTime start;
	private LocalDateTime end;

	public DateRange(String startDate, String startTime, String endDate, String endTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		this.start = LocalDateTime.parse(startDate + " " + startTime, formatter);
		this.end = LocalDateTime.parse(endDate + " " + endTime, formatter);
	}

	public LocalDateTime getStart() {
		return start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "From " + start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " to "
				+ end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
}