import type Options from './types/options';

export interface ZebraPrinterPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  print(options: Options): Promise<{ value: string }>;
}
