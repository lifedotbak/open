package com.spyker.framework.hikvision.jna;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.examples.win32.W32API;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;

public interface PlayCtrl extends Library {
    int STREAME_REALTIME = 0;
    int STREAME_FILE = 1;

    boolean PlayM4_GetPort(IntByReference var1);

    boolean PlayM4_OpenStream(int var1, ByteByReference var2, int var3, int var4);

    boolean PlayM4_InputData(int var1, ByteByReference var2, int var3);

    boolean PlayM4_CloseStream(int var1);

    boolean PlayM4_SetStreamOpenMode(int var1, int var2);

    boolean PlayM4_Play(int var1, W32API.HWND var2);

    boolean PlayM4_Stop(int var1);

    boolean PlayM4_SetSecretKey(int var1, int var2, String var3, int var4);

    boolean PlayM4_GetPictureSize(int var1, IntByReference var2, IntByReference var3);

    boolean PlayM4_GetJPEG(int var1, Pointer var2, int var3, IntByReference var4);

    int PlayM4_GetLastError(int var1);

    boolean PlayM4_SetDecCallBackExMend(int var1, DecCallBack var2, Pointer var3, int var4, int var5);

    interface DecCallBack extends Callback {
        void invoke(int var1, Pointer var2, int var3, FRAME_INFO var4, int var5, int var6);
    }

    class FRAME_INFO extends Structure {
        public int nWidth;
        public int nHeight;
        public int nStamp;
        public int nType;
        public int nFrameRate;
        public int dwFrameNum;

        public FRAME_INFO() {
        }
    }
}