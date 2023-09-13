package com.spyker.framework.modbus;

import com.digitalpetri.modbus.master.ModbusTcpMaster;
import com.digitalpetri.modbus.master.ModbusTcpMasterConfig;
import com.digitalpetri.modbus.requests.ReadCoilsRequest;
import com.digitalpetri.modbus.requests.ReadDiscreteInputsRequest;
import com.digitalpetri.modbus.requests.ReadHoldingRegistersRequest;
import com.digitalpetri.modbus.requests.ReadInputRegistersRequest;
import com.digitalpetri.modbus.responses.ReadCoilsResponse;
import com.digitalpetri.modbus.responses.ReadDiscreteInputsResponse;
import com.digitalpetri.modbus.responses.ReadHoldingRegistersResponse;
import com.digitalpetri.modbus.responses.ReadInputRegistersResponse;
import io.netty.buffer.ByteBuf;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class ModbusMasterTcpUtils {

    /**
     * 默认端口
     */
    public final static String DEFAULT_COMMUNICATION_MANAGER_PORT = "502";
    /**
     * 默认slave Id
     */
    public final static int DEFAULT_COMMUNICATION_MANAGER_SLAVE_ID = 200;
    static ModbusTcpMaster master;

    /**
     * 获取TCP协议的Master
     *
     * @return
     */
    public static void initModbusTcpMaster(String ip, int port) {
        if (master == null) {
            // 创建配置
            ModbusTcpMasterConfig config = new ModbusTcpMasterConfig.Builder(ip).setPort(port).build();
            master = new ModbusTcpMaster(config);

        }
    }

    /***
     * 释放资源
     */
    public static void release() {
        if (master != null) {
            master.disconnect();
        }

        //		Modbus.releaseSharedResources();
    }

    public static float readFloatHoldingRegisters(int address, int quantity, int unitId) throws InterruptedException,
            ExecutionException {

        Number number = readHoldingRegisters(address, quantity, unitId);

        String numberValue = number.toString();

        float result = 0;

        try {

            if ("Infinity".equalsIgnoreCase(numberValue) || "Null".equalsIgnoreCase(numberValue)) {
                result = 0;
            } else {
                result = Float.parseFloat(numberValue);
            }

        } catch (Exception e) {
            log.error("error --> {}", e);
        }

        return result;

    }

    /**
     * 读取HoldingRegister数据
     *
     * @param address  寄存器地址
     * @param quantity 寄存器数量
     * @param unitId   id
     * @return 读取结果
     * @throws InterruptedException 异常
     * @throws ExecutionException   异常
     */
    public static Number readHoldingRegisters(int address, int quantity, int unitId) throws InterruptedException,
            ExecutionException {
        Number result = null;
        CompletableFuture<ReadHoldingRegistersResponse> future =
                master.sendRequest(new ReadHoldingRegistersRequest(address, quantity), unitId);
        ReadHoldingRegistersResponse readHoldingRegistersResponse = future.get();// 工具类做的同步返回.实际使用推荐结合业务进行异步处理
        if (readHoldingRegistersResponse != null) {
            ByteBuf buf = readHoldingRegistersResponse.getRegisters();

            result = buf.readFloat();

            ReferenceCountUtil.release(readHoldingRegistersResponse);
        }
        return result;
    }

    /**
     * 读取InputRegisters模拟量数据
     *
     * @param address  寄存器开始地址
     * @param quantity 数量
     * @param unitId   ID
     * @return 读取值
     * @throws InterruptedException 异常
     * @throws ExecutionException   异常
     */
    public static Number readInputRegisters(int address, int quantity, int unitId) throws InterruptedException,
            ExecutionException {
        Number result = null;
        CompletableFuture<ReadInputRegistersResponse> future =
                master.sendRequest(new ReadInputRegistersRequest(address, quantity), unitId);
        ReadInputRegistersResponse readInputRegistersResponse = future.get();// 工具类做的同步返回.实际使用推荐结合业务进行异步处理
        if (readInputRegistersResponse != null) {
            ByteBuf buf = readInputRegistersResponse.getRegisters();
            result = buf.readFloat();
            ReferenceCountUtil.release(readInputRegistersResponse);
        }
        return result;
    }

    /**
     * 读取Coils开关量
     *
     * @param address  寄存器开始地址
     * @param quantity 数量
     * @param unitId   ID
     * @return 读取值
     * @throws InterruptedException 异常
     * @throws ExecutionException   异常
     */
    public static Boolean readCoils(int address, int quantity, int unitId) throws InterruptedException,
            ExecutionException {

        Boolean result = null;
        CompletableFuture<ReadCoilsResponse> future = master.sendRequest(new ReadCoilsRequest(address, quantity),
                unitId);
        ReadCoilsResponse readCoilsResponse = future.get();// 工具类做的同步返回.实际使用推荐结合业务进行异步处理
        if (readCoilsResponse != null) {
            ByteBuf buf = readCoilsResponse.getCoilStatus();
            result = buf.readBoolean();
            ReferenceCountUtil.release(readCoilsResponse);
        }
        return result;
    }

    /**
     * 读取readDiscreteInputs开关量
     *
     * @param address  寄存器开始地址
     * @param quantity 数量
     * @param unitId   ID
     * @return 读取值
     * @throws InterruptedException 异常
     * @throws ExecutionException   异常
     */
    public static Boolean readDiscreteInputs(int address, int quantity, int unitId) throws InterruptedException,
            ExecutionException {
        Boolean result = null;
        CompletableFuture<ReadDiscreteInputsResponse> future =
                master.sendRequest(new ReadDiscreteInputsRequest(address, quantity), unitId);
        ReadDiscreteInputsResponse discreteInputsResponse = future.get();// 工具类做的同步返回.实际使用推荐结合业务进行异步处理
        if (discreteInputsResponse != null) {
            ByteBuf buf = discreteInputsResponse.getInputStatus();
            result = buf.readBoolean();
            ReferenceCountUtil.release(discreteInputsResponse);
        }
        return result;
    }

}