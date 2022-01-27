# Application API 0.1.0 documentation

* Support: [Fancy Team](https://greenhalos.lu)
* Email support: [asyncapi@greenhalos.lu](mailto:asyncapi@greenhalos.lu)


## Table of Contents

* [Servers](#servers)
  * [test](#test-server)
* [Operations](#operations)
  * [SUB channelName](#sub-channelname-operation)

## Servers

### `test` Server

* URL: `http://rabbitmq`
* Protocol: `amqp`

RabbitMQ Server


## Operations

### SUB `channelName` Operation

Publish information

#### Message lu.greenhalos.j2asyncapi.core.Example `<anonymous-message-1>`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |
| floatingAmount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| bigDecimalAmount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| intAmount | integer | - | examples (`42`, `352`) | format (`int32`) | - |
| innerExample | - | - | - | - | **additional properties are allowed** |
| innerExample.innerCurrency | string | - | examples (`"blah"`, `"blub"`) | - | - |
| innerExample.nestedInnerExample | - | - | - | - | **additional properties are allowed** |
| innerExample.nestedInnerExample.nestedInnerCurrency | string | - | examples (`"blah"`, `"blub"`) | - | - |
| finalCurrency | string | - | examples (`"blah"`, `"blub"`) | - | - |
| currency | string | - | examples (`"blah"`, `"blub"`) | - | - |
| listCurrency | array<string> | - | - | - | - |
| listCurrency (single item) | string | - | examples (`"blah"`, `"blub"`) | - | - |
| privateFinalCurrency | string | - | examples (`"blah"`, `"blub"`) | - | - |
| isFancy | boolean | - | examples (`true`, `false`) | - | - |
| doubleAmount | number | - | examples (`42.42`, `352.01`) | format (`double`) | - |
| longAmount | integer | - | examples (`42`, `352`) | format (`int64`) | - |

> Examples of payload _(generated)_

```json
{
  "floatingAmount": 42.42,
  "bigDecimalAmount": 42.42,
  "intAmount": 42,
  "innerExample": {
    "innerCurrency": "blah",
    "nestedInnerExample": {
      "nestedInnerCurrency": "blah"
    }
  },
  "finalCurrency": "blah",
  "currency": "blah",
  "listCurrency": [
    "blah"
  ],
  "privateFinalCurrency": "blah",
  "isFancy": true,
  "doubleAmount": 42.42,
  "longAmount": 42
}
```



