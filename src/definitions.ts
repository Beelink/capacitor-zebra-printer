import type PrintOptions from './types/PrintOptions';
import type PrintResponse from './types/PrintResponse';

export interface ZebraPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  print(options: PrintOptions): Promise<PrintResponse>;
}
