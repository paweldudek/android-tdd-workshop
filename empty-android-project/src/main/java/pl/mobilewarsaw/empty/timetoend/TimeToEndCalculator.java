package pl.mobilewarsaw.empty.timetoend;

public class TimeToEndCalculator {

    protected static final long MILIS_IN_SECOND = 1000l;
    protected static final long MILIS_IN_MINUTE = 60 * MILIS_IN_SECOND;
    protected static final long MILIS_IN_HOUR = 60 * MILIS_IN_MINUTE;

    public TimeToEnd getTimeToEnd(long start, long end) {
        long delta = end - start;
        long hours = delta / MILIS_IN_HOUR;
        delta = delta - hours * MILIS_IN_HOUR;
        long minutes = delta / MILIS_IN_MINUTE;
        delta = delta - minutes * MILIS_IN_MINUTE;
        long seconds = delta / MILIS_IN_SECOND;

        return new TimeToEnd(hours, minutes, seconds);
    }
}
