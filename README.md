# checkstyle-files-generator

`checkstyle-files-generator` is a small command-line application used by Checkstyle
to generate files that are needed during the build. It generates XML metadata files
and converts the XDoc `.xml.template` files into generated `.xml` pages.

## Integration

Install the generator into Maven Local while developing both projects:

```bash
./mvnw package
./mvnw install
```

Checkstyle invokes the generator through `exec-maven-plugin`. The generator artifact
does not contain Checkstyle; its runtime classpath must include the Checkstyle checkout's
freshly compiled classes and dependencies.

## Build

```bash
./mvnw package
```

The thin generator jar is created at:

```text
target/checkstyle-files-generator-1.0.4.jar
```

## Publish

Trigger workflow
[`.github/workflows/release-deploy-maven-central.yml`](.github/workflows/release-deploy-maven-central.yml)
to publish a new version of the project.
