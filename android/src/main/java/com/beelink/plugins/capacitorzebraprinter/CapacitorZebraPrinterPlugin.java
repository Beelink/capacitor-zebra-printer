package com.beelink.plugins.capacitorzebraprinter;

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

@CapacitorPlugin(name = "CapacitorZebraPrinter")
public class CapacitorZebraPrinterPlugin extends Plugin {

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
                    clientSocket.close();
                }
                if (outToServer != null) {
                    outToServer.close();
                }
            }
        } catch (IOException e1) {
            System.out.println("Cannot print label on this printer : " + ip + ":" + port);
            error = true;
        }

        if (error) {
            call.reject("error");
        } else {
            JSObject ret = new JSObject();
            ret.put("value", "success");
            call.resolve(ret);
        }
    }
}
