package pl.mobilewarsaw.empty.timetoend;

public class TimeToEnd {
    private long mHours;
    private long mMinutes;
    private long mSeconds;

    public TimeToEnd(long hours, long minutes, long seconds) {
        mHours = hours;
        mMinutes = minutes;
        mSeconds = seconds;
    }

    public long getHours() {
        return mHours;
    }

    public void setHours(long hours) {
        mHours = hours;
    }

    public long getMinutes() {
        return mMinutes;
    }

    public void setMinutes(long minutes) {
        mMinutes = minutes;
    }

    public long getSeconds() {
        return mSeconds;
    }

    public void setSeconds(long seconds) {
        mSeconds = seconds;
    }

    public boolean isInThePast() {
        return mHours < 0 || mSeconds < 0 || mMinutes < 0;
    }
}
