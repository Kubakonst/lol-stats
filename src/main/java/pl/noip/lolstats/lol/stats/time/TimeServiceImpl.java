package pl.noip.lolstats.lol.stats.time;

public class TimeServiceImpl implements TimeService {
    @Override
    public long getMillisSinceEpoch() {
        return System.currentTimeMillis();
    }
}
