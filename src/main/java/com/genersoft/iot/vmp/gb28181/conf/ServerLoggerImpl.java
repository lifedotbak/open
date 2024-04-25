package com.genersoft.iot.vmp.gb28181.conf;

import gov.nist.core.CommonLogger;
import gov.nist.core.ServerLogger;
import gov.nist.core.StackLogger;
import gov.nist.javax.sip.message.SIPMessage;
import gov.nist.javax.sip.stack.SIPTransactionStack;

import java.util.Properties;

import javax.sip.SipStack;

public class ServerLoggerImpl implements ServerLogger {

    protected StackLogger stackLogger;
    private boolean showLog = true;
    private SIPTransactionStack sipStack;

    @Override
    public void closeLogFile() {}

    @Override
    public void logMessage(SIPMessage message, String from, String to, boolean sender, long time) {
        if (!showLog) {
            return;
        }
        String stringBuilder = (sender ? "发送：目标--->" + from : "接收：来自--->" + to) + "\r\n" + message;
        this.stackLogger.logInfo(stringBuilder);
    }

    @Override
    public void logMessage(
            SIPMessage message, String from, String to, String status, boolean sender, long time) {
        if (!showLog) {
            return;
        }
        String stringBuilder = (sender ? "发送： 目标->" + from : "接收：来自->" + to) + "\r\n" + message;
        this.stackLogger.logInfo(stringBuilder);
    }

    @Override
    public void logMessage(
            SIPMessage message, String from, String to, String status, boolean sender) {
        if (!showLog) {
            return;
        }
        String stringBuilder = (sender ? "发送： 目标->" + from : "接收：来自->" + to) + "\r\n" + message;
        this.stackLogger.logInfo(stringBuilder);
    }

    @Override
    public void logException(Exception ex) {
        if (!showLog) {
            return;
        }
        this.stackLogger.logException(ex);
    }

    @Override
    public void setStackProperties(Properties stackProperties) {
        if (!showLog) {
            return;
        }
        String TRACE_LEVEL = stackProperties.getProperty("gov.nist.javax.sip.TRACE_LEVEL");
        if (TRACE_LEVEL != null) {
            showLog = true;
        }
    }

    @Override
    public void setSipStack(SipStack sipStack) {
        if (!showLog) {
            return;
        }
        if (sipStack instanceof SIPTransactionStack) {
            this.sipStack = (SIPTransactionStack) sipStack;
            this.stackLogger = CommonLogger.getLogger(SIPTransactionStack.class);
        }
    }
}