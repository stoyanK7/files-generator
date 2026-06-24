# checkstyle-files-generator

## Build the `-all.jar`

Run:

```bash
./mvnw package
```

The jar is created at:

```text
target/checkstyle-files-generator-1.0.0-all.jar
```

## Make `checkstyle:X.X.X-SNAPSHOT` available in local Maven Repository

In checkstyle project, run:

```bash
./mvnw clean install -Pno-validations
```
