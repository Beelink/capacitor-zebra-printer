export interface ZebraPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  print(options: { ip: string, port: number, zpl: string }): Promise<{ value: string }>;
}
