# j2asyncapi

A lib which helps you generate `asyncapi.yaml` from your Java code based on annotations.

## How to use

1. Include the dependencies in your `pom.xml`

```xml

<dependencies>
    <dependency>
        <groupId>lu.greenhalos</groupId>
        <artifactId>j2asyncapi-annotation</artifactId>
        <version>${j2asyncapi.version}</version>
    </dependency>

    <dependency>
        <groupId>lu.greenhalos</groupId>
        <artifactId>j2asyncapi-processor</artifactId>
        <version>${j2asyncapi.version}</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

2. Copy [this test](./j2asyncapi-processor/src/test/java/lu/greenhalos/j2asyncapi/annoations/WriteToFileTest.java)
   from this Repository to your code base
3. Change `ExampleBaseApplication.class` with a valid base class of yours e.g. your SpringBootApplication class with the
   main method.
4. Change the constant `DOCS_TARGET` to your needs.
5. Annotate a method which is a publisher e.g. a methode which is already annotated with `@RabbitListener` with the
   j2asyncapi annotation `@AsyncApi`:
```java
class Listener {

    @AsyncApi(
            type = LISTENER, exchange = "exchange", routingKey = "routing.key", payload = ExampleListenerMessage.class,
            description = "Description explaining exactly what happens here"
    )
    @RabbitListener(queues = "${some.long.path.to.the.queue.name}")
    public void on(Message message) {
        // do your stuff
    }
}
```
6. Run the copied test. The resulting asyncapi.yml is generated to your `DOCS_TARGET`.

## How to build

🥁 it is a simple a running:
```shell
./mvnw clean verify # or install in case you want to publish it to you local .m2 repository
```

# TODOS

* @JsonIgnore
* process java.util.Locale
* Statistics about schemas

