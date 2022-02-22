package com.zebra.plugins.printer;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@CapacitorPlugin(name = "ZebraPrinter")
public class ZebraPrinterPlugin extends Plugin {

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.resolve(ret);
    }

    @PluginMethod
    public void print(PluginCall call) {
        String ip = call.getString("ip");
        int port = call.getInt("port");
        String zpl = call.getString("zpl");

        System.out.println("start");
        System.out.println("------------------------------------------------------------");

        Boolean error = false;
        try {
            Socket clientSocket = null;
            DataOutputStream outToServer = null;
            try {
                System.out.println("Printing now...");
                clientSocket = new Socket();
                SocketAddress socketAddress = new InetSocketAddress(ip, port);
                clientSocket.connect(socketAddress, 30000);
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
                outToServer.writeBytes(zpl);
                System.out.println("send the following string: " + zpl);
            } catch (Exception e) {
                System.out.println("Connection error");
                error = true;
            } finally {
                if (clientSocket != null) {
                    outToServer.close();
                    clientSocket.close();
                }
            }
        } catch (IOException e1) {
            System.out.println("Cannot print label on this printer : " + ip + ":" + port);
            System.out.println(e1.getMessage());
            call.reject(e1.getMessage());
            error = true;
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("end");

        JSObject ret = new JSObject();
        if (error) {
            ret.put("success", false);
            ret.put("message", "Something went wrong");
        } else {
            ret.put("success", true);
            ret.put("message", "Successfully sent to printer");
        }
        call.resolve(ret);
    }
}
