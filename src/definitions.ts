export interface ZebraPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
