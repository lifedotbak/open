package com.spyker.framework.third.hikvision.jna;

import com.sun.jna.Native;
import com.sun.jna.examples.win32.W32API;

// windows user32接口,user32.dll in system32 folder, 在设置遮挡区域,移动侦测区域等情况下使用
interface USER32 extends W32API {

    USER32 INSTANCE = (USER32) Native.loadLibrary("user32", USER32.class, DEFAULT_OPTIONS);

    int BF_LEFT = 0x0001;
    int BF_TOP = 0x0002;
    int BF_RIGHT = 0x0004;
    int BF_BOTTOM = 0x0008;
    int BDR_SUNKENOUTER = 0x0002;
    int BF_RECT = (BF_LEFT | BF_TOP | BF_RIGHT | BF_BOTTOM);

    boolean DrawEdge(HDC hdc, com.sun.jna.examples.win32.GDI32.RECT qrc, int edge, int grfFlags);

    int FillRect(HDC hDC, com.sun.jna.examples.win32.GDI32.RECT lprc, HANDLE hbr);
}