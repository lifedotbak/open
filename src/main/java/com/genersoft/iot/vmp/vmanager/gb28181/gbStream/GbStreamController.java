package com.genersoft.iot.vmp.vmanager.gb28181.gbStream;

import com.genersoft.iot.vmp.conf.exception.ControllerException;
import com.genersoft.iot.vmp.gb28181.bean.GbStream;
import com.genersoft.iot.vmp.service.IGbStreamService;
import com.genersoft.iot.vmp.service.IPlatformService;
import com.genersoft.iot.vmp.service.IStreamPushService;
import com.genersoft.iot.vmp.vmanager.bean.ErrorCode;
import com.genersoft.iot.vmp.vmanager.gb28181.gbStream.bean.GbStreamParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "视频流关联到级联平台")
@RestController
@RequestMapping("/api/gbStream")
public class GbStreamController {

    private static final Logger logger = LoggerFactory.getLogger(GbStreamController.class);

    @Autowired private IGbStreamService gbStreamService;

    @Autowired private IStreamPushService service;

    @Autowired private IPlatformService platformService;

    /**
     * 移除国标关联
     *
     * @param gbStreamParam
     * @return
     */
    @Operation(summary = "移除国标关联")
    @DeleteMapping(value = "/del")
    @ResponseBody
    public void del(@RequestBody GbStreamParam gbStreamParam) {

        if (gbStreamParam.getGbStreams() == null || gbStreamParam.getGbStreams().isEmpty()) {
            if (gbStreamParam.isAll()) {
                gbStreamService.delAllPlatformInfo(
                        gbStreamParam.getPlatformId(), gbStreamParam.getCatalogId());
            }
        } else {
            gbStreamService.delPlatformInfo(
                    gbStreamParam.getPlatformId(), gbStreamParam.getGbStreams());
        }
    }

    /**
     * 保存国标关联
     *
     * @param gbStreamParam
     * @return
     */
    @Operation(summary = "保存国标关联")
    @PostMapping(value = "/add")
    @ResponseBody
    public void add(@RequestBody GbStreamParam gbStreamParam) {
        if (gbStreamParam.getGbStreams() == null || gbStreamParam.getGbStreams().isEmpty()) {
            if (gbStreamParam.isAll()) {
                List<GbStream> allGBChannels =
                        gbStreamService.getAllGBChannels(gbStreamParam.getPlatformId());
                gbStreamService.addPlatformInfo(
                        allGBChannels, gbStreamParam.getPlatformId(), gbStreamParam.getCatalogId());
            }
        } else {
            gbStreamService.addPlatformInfo(
                    gbStreamParam.getGbStreams(),
                    gbStreamParam.getPlatformId(),
                    gbStreamParam.getCatalogId());
        }
    }

    /**
     * 保存国标关联
     *
     * @param gbId
     * @return
     */
    @Operation(summary = "保存国标关联")
    @GetMapping(value = "/addWithGbid")
    @ResponseBody
    public void add(
            String gbId, String platformGbId, @RequestParam(required = false) String catalogGbId) {
        List<GbStream> gbStreams = gbStreamService.getGbChannelWithGbid(gbId);
        if (gbStreams.isEmpty()) {
            throw new ControllerException(ErrorCode.ERROR100.getCode(), "gbId的信息未找到");
        }
        gbStreamService.addPlatformInfo(gbStreams, platformGbId, catalogGbId);
    }
}