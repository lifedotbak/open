package com.spyker.framework.zlmediakit.service;

import io.github.lunasaw.zlm.entity.ServerNodeConfig;
import io.github.lunasaw.zlm.hook.param.*;
import io.github.lunasaw.zlm.hook.service.AbstractZlmHookService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * zlm-spring-boot-starter的hook服务，默认实现，尚未测试
 *
 * <p>zlm-spring-boot-starter(1.0.3)此版本使用的springboot版本为2.7.8,本项目(open)使用spingboot版本为3.2.4。
 * 两个版本的yaml配置有所冲突与不同，io.github.lunasaw.zlm.config包下的配置都未曾生效。 ZlmRestService使用不受影响
 */
@Component
@Slf4j
public class ApplicationZlmHookService extends AbstractZlmHookService {

    @Override
    public void onServerKeepLive(OnServerKeepaliveHookParam param) {

        log.info("param-->{}", param);
    }

    @Override
    public HookResult onPlay(OnPlayHookParam param) {

        log.info("param-->{}", param);

        return HookResult.SUCCESS();
    }

    @Override
    public HookResultForOnPublish onPublish(OnPublishHookParam param) {

        log.info("param-->{}", param);

        return HookResultForOnPublish.SUCCESS();
    }

    @Override
    public void onStreamChanged(OnStreamChangedHookParam param) {}

    @Override
    public HookResultForStreamNoneReader onStreamNoneReader(OnStreamNoneReaderHookParam param) {

        log.info("param-->{}", param);

        return HookResultForStreamNoneReader.SUCCESS();
    }

    @Override
    public void onStreamNotFound(OnStreamNotFoundHookParam param) {

        log.info("param-->{}", param);
    }

    @Override
    public void onServerStarted(ServerNodeConfig param) {

        log.info("param-->{}", param);
    }

    @Override
    public void onSendRtpStopped(OnSendRtpStoppedHookParam param) {

        log.info("param-->{}", param);
    }

    @Override
    public void onRtpServerTimeout(OnRtpServerTimeoutHookParam param) {

        log.info("param-->{}", param);
    }

    @Override
    public HookResultForOnHttpAccess onHttpAccess(OnHttpAccessParam param) {

        log.info("param-->{}", param);

        return HookResultForOnHttpAccess.SUCCESS();
    }

    @Override
    public HookResultForOnRtspRealm onRtspRealm(OnRtspRealmHookParam param) {

        log.info("param-->{}", param);
        return HookResultForOnRtspRealm.SUCCESS();
    }

    @Override
    public HookResultForOnRtspAuth onRtspAuth(OnRtspAuthHookParam param) {

        log.info("param-->{}", param);
        return HookResultForOnRtspAuth.SUCCESS();
    }

    @Override
    public void onFlowReport(OnFlowReportHookParam param) {

        log.info("param-->{}", param);
    }

    @Override
    public void onServerExited(HookParam param) {

        log.info("param-->{}", param);
    }

    @Override
    public void onRecordMp4(OnRecordMp4HookParam param) {

        log.info("param-->{}", param);
    }
}
