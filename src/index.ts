import { registerPlugin } from '@capacitor/core';

import type { ZebraPrinterPlugin } from './definitions';

const ZebraPrinter = registerPlugin<ZebraPrinterPlugin>('ZebraPrinter', {
  web: () => import('./web').then(m => new m.ZebraPrinterWeb()),
});

export * from './definitions';
export { ZebraPrinter };
