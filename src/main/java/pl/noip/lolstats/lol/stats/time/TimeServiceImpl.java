package pl.noip.lolstats.lol.stats.time;

import org.springframework.stereotype.Component;

@Component
public class TimeServiceImpl implements TimeService {
    @Override
    public long getMillisSinceEpoch() {
        return System.currentTimeMillis();
    }
}