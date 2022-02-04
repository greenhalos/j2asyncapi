# Application API 0.1.0 documentation

* Support: [Fancy Team](https://greenhalos.lu)
* Email support: [asyncapi@greenhalos.lu](mailto:asyncapi@greenhalos.lu)


## Table of Contents

* [Servers](#servers)
  * [test](#test-server)
* [Operations](#operations)
  * [SUB exchange/queries](#sub-exchangequeries-operation)
  * [PUB exchange/routing.key](#pub-exchangeroutingkey-operation)
  * [SUB exchange/routing.key](#sub-exchangeroutingkey-operation)
  * [SUB exchange/routing.key.multiple1](#sub-exchangeroutingkeymultiple1-operation)
  * [SUB exchange/routing.key.multiple2](#sub-exchangeroutingkeymultiple2-operation)
  * [SUB routing.key.default.exchange](#sub-routingkeydefaultexchange-operation)

## Servers

### `test` Server

* URL: `http://rabbitmq`
* Protocol: `amqp`

RabbitMQ Server


## Operations

### SUB `exchange/queries` Operation

#### Message Void `j.l.Void`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |

> Examples of payload _(generated)_

```json
{}
```



### PUB `exchange/routing.key` Operation

Description explaining exactly what happens here

#### Message ExampleListenerMessage `l.g.j.a.e.l.ExampleListener$ExampleListenerMessage`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |
| amount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| currency | string | - | examples (`"EUR"`, `"USD"`, `"CHF"`) | - | - |
| id | string | there can only be 2 valid values | examples (`"value1"`, `"value42"`) | - | - |

> Examples of payload _(generated)_

```json
{
  "amount": 42.42,
  "currency": "EUR",
  "id": "value1"
}
```



### SUB `exchange/routing.key` Operation

Description explaining exactly what happens here

#### Message ExamplePublisherMessage `l.g.j.a.e.p.ExamplePublisher$ExamplePublisherMessage`

this is a message which gets published

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |
| amount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| currency | integer | - | examples (`42`, `352`) | format (`int32`) | - |

> Examples of payload _(generated)_

```json
{
  "amount": 42.42,
  "currency": 42
}
```



### SUB `exchange/routing.key.multiple1` Operation

#### Message Void `j.l.Void`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |

> Examples of payload _(generated)_

```json
{}
```



### SUB `exchange/routing.key.multiple2` Operation

#### Message Void `j.l.Void`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |

> Examples of payload _(generated)_

```json
{}
```



### SUB `routing.key.default.exchange` Operation

#### Message Void `j.l.Void`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |

> Examples of payload _(generated)_

```json
{}
```



