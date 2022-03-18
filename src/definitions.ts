import type PrintOptions from './types/PrintOptions';

export interface CapacitorZebraPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  print(options: PrintOptions): Promise<{ value: string }>;
}
