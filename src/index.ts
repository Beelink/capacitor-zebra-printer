import { registerPlugin } from '@capacitor/core';

import type { CapacitorZebraPrinterPlugin } from './definitions';

const CapacitorZebraPrinter = registerPlugin<CapacitorZebraPrinterPlugin>(
  'CapacitorZebraPrinter',
  {
    web: () => import('./web').then(m => new m.CapacitorZebraPrinterWeb()),
  },
);

export * from './definitions';
export { CapacitorZebraPrinter };
