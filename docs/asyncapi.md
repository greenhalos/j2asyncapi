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

#### Message Example `l.g.j.c.WriteToFileTest$Example`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |
| exampleEnum | string | - | allowed (`"VALUE_2"`, `"VALUE_3"`), examples (`"VALUE_2"`, `"VALUE_3"`) | - | - |
| intAmount | integer | - | examples (`42`, `352`) | format (`int32`) | - |
| exampleLocalDateTime | string | - | examples (`"2022-01-31T23:20:50.52Z"`, `"1985-04-12T15:59:55-08:00"`) | format (`date-time`) | - |
| innerExample | - | - | - | - | **additional properties are allowed** |
| innerExample.innerCurrency | string | - | examples (`"Lorem"`, `"ipsum"`) | - | - |
| innerExample.nestedInnerExample | - | - | - | - | **additional properties are allowed** |
| innerExample.nestedInnerExample.nestedInnerCurrency | string | - | examples (`"Lorem"`, `"ipsum"`) | - | - |
| finalCurrency | string | - | examples (`"Lorem"`, `"ipsum"`) | - | - |
| listCurrency | array<string> | - | - | - | - |
| listCurrency (single item) | string | - | examples (`"Lorem"`, `"ipsum"`) | - | - |
| privateFinalCurrency | string | - | examples (`"Lorem"`, `"ipsum"`) | - | - |
| isFancy | boolean | - | examples (`true`, `false`) | - | - |
| doubleAmount | number | - | examples (`42.42`, `352.01`) | format (`double`) | - |
| exampleInstant | string | - | examples (`"2022-01-31T23:20:50.52Z"`, `"1985-04-12T15:59:55-08:00"`) | format (`date-time`) | - |
| floatingAmount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| bigDecimalAmount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| exampleLocalDate | string | - | examples (`"2022-01-31"`, `"1985-04-12"`) | format (`date`) | - |
| fieldAnnotation | integer | - | examples (`"456565"`, `"4654"`) | format (`flapping`) | - |
| currency | string | - | examples (`"Lorem"`, `"ipsum"`) | - | - |
| longAmount | integer | - | examples (`42`, `352`) | format (`int64`) | - |

> Examples of payload _(generated)_

```json
{
  "exampleEnum": "VALUE_2",
  "intAmount": 42,
  "exampleLocalDateTime": "2022-01-31T23:20:50.52Z",
  "innerExample": {
    "innerCurrency": "Lorem",
    "nestedInnerExample": {
      "nestedInnerCurrency": "Lorem"
    }
  },
  "finalCurrency": "Lorem",
  "listCurrency": [
    "Lorem"
  ],
  "privateFinalCurrency": "Lorem",
  "isFancy": true,
  "doubleAmount": 42.42,
  "exampleInstant": "2022-01-31T23:20:50.52Z",
  "floatingAmount": 42.42,
  "bigDecimalAmount": 42.42,
  "exampleLocalDate": "2022-01-31",
  "fieldAnnotation": "456565",
  "currency": "Lorem",
  "longAmount": 42
}
```



