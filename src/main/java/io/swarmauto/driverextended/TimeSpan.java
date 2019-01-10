package io.swarmauto.driverextended;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class TimeSpan {
    public Period Span;
    public Duration Time;

    public static TimeSpan getSpan(LocalDateTime start, LocalDateTime end) {
        TimeSpan timeSpanToReturn = new TimeSpan();
        timeSpanToReturn.Span = Period.between(start.toLocalDate(), end.toLocalDate());
        timeSpanToReturn.Time = Duration.between(start, end);
        return timeSpanToReturn;
    }
}
