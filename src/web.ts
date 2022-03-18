import { WebPlugin } from '@capacitor/core';

import type { CapacitorZebraPrinterPlugin } from './definitions';
import type PrintOptions from './types/PrintOptions';
import fetchWithTimeout from './utils/fetchWithTimeout';

export class CapacitorZebraPrinterWeb
  extends WebPlugin
  implements CapacitorZebraPrinterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async print(options: PrintOptions): Promise<{ value: string }> {
    const request = new Request(`http://${options.ip}:${options.port}`, {
      method: 'POST',
      mode: 'no-cors',
      cache: 'no-cache',
      body: options.zpl,
    });
    return await fetchWithTimeout(request)
      .then(() => {
        return { value: 'success' };
      })
      .catch(() => {
        return { value: 'error' };
      });
  }
}
