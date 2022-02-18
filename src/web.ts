import { WebPlugin } from '@capacitor/core';

import type { ZebraPrinterPlugin } from './definitions';
import type PrintOptions from './types/PrintOptions';
import type PrintResponse from './types/PrintResponse';
import fetchWithTimeout from './utils/fetchWithTimeout';

export class ZebraPrinterWeb extends WebPlugin implements ZebraPrinterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async print(options: PrintOptions): Promise<PrintResponse> {
    const request = new Request(`http://${options.ip}:${options.port}`, {
      method: 'POST',
      mode: 'no-cors',
      cache: 'no-cache',
      body: options.zpl,
    });
    return await fetchWithTimeout(request)
      .then(() => {
        return { success: true, message: 'Print succesfully executed' };
      })
      .catch(error => {
        return { success: false, message: error.toString() };
      });
  }
}
