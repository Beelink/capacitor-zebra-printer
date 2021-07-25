import { WebPlugin } from '@capacitor/core';
import type { ZebraPrinterPlugin } from './definitions';

export class ZebraPrinterWeb extends WebPlugin implements ZebraPrinterPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async print(options: { ip: string, port: number, zpl: string }): Promise<{ value: string }> {

    var request = new Request(`http://${options.ip}:${options.port}`, {
      method: 'POST',
      mode: 'no-cors',
      cache: 'no-cache',
      body: options.zpl
    });
    return await fetch(request)
      .then(() => {
        return { value: "print succesfully executed" }
      })
      .catch((error) => {
        throw Error(error);
      });
  }
}
