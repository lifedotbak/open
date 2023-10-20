package com.spyker.framework.third.hikvision;

import com.spyker.framework.third.hikvision.utils.OsSelect;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Slf4j
@AutoConfiguration
@ConfigurationProperties(prefix = "hik", ignoreUnknownFields = true)
@ConditionalOnProperty(prefix = "hik", name = "enabled", havingValue = "true")
public class HCProperties {

    private String winLib;
    private String winPic;
    private String winVideo;

    private String linuxLib;
    private String linuxPic;
    private String linuxVideo;

    public String getSystemLib() {

        log.info("lib,linuxLib-->{},{}", winLib, linuxLib);

        if (OsSelect.isLinux()) {

            return linuxLib;

        }

        return winLib;
    }

    public String getSystemPic() {

        if (OsSelect.isLinux()) {

            return linuxPic;

        }

        return winPic;
    }

    public String getSystemVideo() {

        if (OsSelect.isLinux()) {

            return linuxVideo;

        }

        return winVideo;
    }

}