import Foundation

@objc public class CapacitorZebraPrinter: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
