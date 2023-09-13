package com.spyker.framework.hikvision;

import com.spyker.framework.hikvision.utils.OsSelect;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Slf4j
@Component
@ConfigurationProperties(prefix = "hik", ignoreUnknownFields = true)
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