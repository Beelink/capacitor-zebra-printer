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
                clientSocket.setSoTimeout(30000);
                clientSocket.connect(new InetSocketAddress(ip, port), 30000);
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
                outToServer.writeBytes(zpl);
                outToServer.close();
                System.out.println("send the following string: " + zpl);
                clientSocket.close();
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

        if (!error) {
            JSObject ret = new JSObject();
            ret.put("success", true);
            ret.put("message", "Succesfully sent to printer");
            call.success(ret);
        }
    }
}
