package com.spyker.framework.log.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

import groovy.util.logging.Slf4j;

@Slf4j
public class LogbackFilter extends Filter<ILoggingEvent> {

    Level level;

    @Override
    public FilterReply decide(ILoggingEvent event) {

        if (!isStarted()) {
            return FilterReply.NEUTRAL;
        }

        String loggerName = event.getLoggerName();

        if (!loggerName.contains("job.core") && event.getLevel().isGreaterOrEqual(level)) {
            return FilterReply.NEUTRAL;
        }

        return FilterReply.DENY;
    }

    public void setLevel(String level) {
        this.level = Level.toLevel(level);
    }
}