# checkstyle-files-generator

`checkstyle-files-generator` is a small command-line application used by Checkstyle
to generate files that are needed during the build. The current implementation
generates XML metadata files; XDoc generation will be added separately.

## Usage

Build the runnable jar and execute it against a Checkstyle checkout:

```bash
./mvnw package
java -jar target/checkstyle-files-generator-1.0.0-all.jar /path/to/checkstyle --generateMetadata
```

## Build

```bash
./mvnw package
```

The shaded runnable jar is created at:

```text
target/checkstyle-files-generator-1.0.0-all.jar
```

## Publish

Trigger workflow
[`.github/workflows/release-maven-deploy.yml``](.github/workflows/release-maven-deploy.yml)
to publish a new version of the project.
