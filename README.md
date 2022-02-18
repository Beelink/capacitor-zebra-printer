# capacitor-zebra-printer

Capacitor Zebra Printer is a fork of [ionic-zebra-printer](https://github.com/levidousseaux/ionic-zebra-printer). You can use this module to send ZPL messages to Zebra printers.
###### Fork changes:

- Add fetch timeout
- Print response typization

## Install
```bash
yarn add capacitor-zebra-printer
```
or
```bash
npm install capacitor-zebra-printer
```
and finally
```bash
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`print(...)`](#print)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => any
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>any</code>

--------------------


### print(...)

```typescript
print(options: PrintOptions) => any
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ ip: string; port: number; zpl: string; }</code> |

**Returns:** <code>any</code>

--------------------

</docgen-api>
