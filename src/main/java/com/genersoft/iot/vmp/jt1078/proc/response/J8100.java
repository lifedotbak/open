package com.genersoft.iot.vmp.jt1078.proc.response;

import com.genersoft.iot.vmp.jt1078.annotation.MsgId;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @author QingtaiJiang
 * @date 2023/4/27 18:40
 * @email qingtaij@163.com
 */
@MsgId(id = "8100")
public class J8100 extends Rs {
    public static final Integer SUCCESS = 0;

    Integer respNo;
    Integer result;
    String code;

    @Override
    public ByteBuf encode() {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeShort(respNo);
        buffer.writeByte(result);
        buffer.writeCharSequence(code, CharsetUtil.UTF_8);
        return buffer;
    }

    public void setRespNo(Integer respNo) {
        this.respNo = respNo;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setCode(String code) {
        this.code = code;
    }
}