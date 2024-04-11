package com.onvif;

import com.onvif.soap.OnvifDevice;

import java.net.ConnectException;

import javax.xml.soap.SOAPException;

public class Main {

    private static final String INFO =
            "Commands:\n  \n  url: Get snapshort URL.\n  info: Get information about each valid command.\n  profiles: Get all profiles.\n  exit: Exit this application.";

    public static void main(String[] args) {
        String cameraAddress = "192.168.3.138";
        String user = "admin";
        String password = "123456";
        OnvifDevice cam;
        try {
            cam = new OnvifDevice(cameraAddress, user, password);

        } catch (ConnectException | SOAPException e1) {
            System.err.println("No connection to camera, please try again.");
            return;
        }
        System.out.println("Connection to camera successful!");
    }
}
