# checkstyle-files-generator

`checkstyle-files-generator` is a small command-line application used by Checkstyle
to generate files that are needed during the build. The current implementation
generates XML metadata files; XDoc generation will be added separately.

## Usage

Build the runnable jar and execute it from a Checkstyle checkout:

```bash
./mvnw package
cd /path/to/checkstyle
java -jar /path/to/checkstyle-files-generator-1.0.3-all.jar . --generateMetadata
```

The shaded CLI contains the released Checkstyle version it was built against. During
the Checkstyle Maven build, the regular (unshaded) artifact is used instead, so the
generator sees the current checkout's freshly compiled classes.

## Build

```bash
./mvnw package
```

The shaded runnable jar is created at:

```text
target/checkstyle-files-generator-1.0.3-all.jar
```

## Publish

Trigger workflow
[`.github/workflows/release-maven-deploy.yml`](.github/workflows/release-maven-deploy.yml)
to publish a new version of the project.
