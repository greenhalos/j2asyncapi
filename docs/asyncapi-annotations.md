# Application API 0.1.0 documentation

* Support: [Fancy Team](https://greenhalos.lu)
* Email support: [asyncapi@greenhalos.lu](mailto:asyncapi@greenhalos.lu)


## Table of Contents

* [Servers](#servers)
  * [test](#test-server)
* [Operations](#operations)
  * [PUB exchange/routing.key](#pub-exchangeroutingkey-operation)
  * [SUB exchange/routing.key](#sub-exchangeroutingkey-operation)

## Servers

### `test` Server

* URL: `http://rabbitmq`
* Protocol: `amqp`

RabbitMQ Server


## Operations

### PUB `exchange/routing.key` Operation

Description explaining exactly what happens here

#### Message lu.greenhalos.j2asyncapi.annoations.example.listener.ExampleListener$ExampleListenerMessage `<anonymous-message-1>`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |
| amount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| currency | string | - | examples (`"blah"`, `"blub"`) | - | - |

> Examples of payload _(generated)_

```json
{
  "amount": 42.42,
  "currency": "blah"
}
```



### SUB `exchange/routing.key` Operation

Description explaining exactly what happens here

#### Message lu.greenhalos.j2asyncapi.annoations.example.publisher.ExamplePublisher$ExamplePublisherMessage `<anonymous-message-2>`

##### Payload

| Name | Type | Description | Value | Constraints | Notes |
|---|---|---|---|---|---|
| (root) | - | - | - | - | **additional properties are allowed** |
| amount | number | - | examples (`42.42`, `352.01`) | format (`float`) | - |
| currency | string | - | examples (`"blah"`, `"blub"`) | - | - |

> Examples of payload _(generated)_

```json
{
  "amount": 42.42,
  "currency": "blah"
}
```



