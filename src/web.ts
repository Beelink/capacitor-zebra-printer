import { WebPlugin } from '@capacitor/core';

import type { ZebraPrinterPlugin } from './definitions';
import type Options from './types/options';
import fetchWithTimeout from './utils/fetchWithTimeout';

export class ZebraPrinterWeb extends WebPlugin implements ZebraPrinterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async print(options: Options): Promise<{ value: string }> {
    const request = new Request(`http://${options.ip}:${options.port}`, {
      method: 'POST',
      mode: 'no-cors',
      cache: 'no-cache',
      body: options.zpl,
    });
    return await fetchWithTimeout(request)
      .then(() => {
        return { value: 'print succesfully executed' };
      })
      .catch(error => {
        throw Error(error);
      });
  }
}
