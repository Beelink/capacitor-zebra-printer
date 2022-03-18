import Foundation
import Capacitor
import SwiftSocket

@objc(ZebraPrinterPlugin)
public class ZebraPrinterPlugin: CAPPlugin {

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": value
        ])
    }

    @objc func print(_ call: CAPPluginCall) {
        let ip = call.getString("ip") ?? ""
        let port = call.getInt("port") ?? 0
        let zpl = call.getString("zpl") ?? ""

        let client:TCPClient = TCPClient(address: ip, port: Int32(port))
        switch client.connect(timeout: 30){
            case .success:
                switch client.send(string: zpl) {
                    case .success:
                        guard let data = client.read(1024*10)
                        else {
                            call.resolve()
                            return
                        }
                    case .failure(let error):
                        call.reject("Something went wrong")

                }
            case .failure(let error):
                call.reject("Something went wrong")
        }
    }
}
