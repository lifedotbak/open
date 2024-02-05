package com.spyker.framework.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;

public class ExcludeTurboFilter extends TurboFilter {

    String excludePackageNames;

    public void setExcludePackageNames(String excludePackageNames) {
        this.excludePackageNames = excludePackageNames;
    }

    @Override
    public FilterReply decide(
            Marker marker,
            Logger logger,
            Level level,
            String format,
            Object[] params,
            Throwable t) {

        String loggerName = logger.getName();

        String[] excludePackageNameList = excludePackageNames.split(";");

        for (String excludePackageName : excludePackageNameList) {

            if (StringUtils.isNotBlank(excludePackageName)
                    && loggerName.contains(excludePackageName)) {
                return FilterReply.DENY;
            }
        }

        return FilterReply.NEUTRAL;
    }
}