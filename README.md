# hid4java-maven-demo
A trivial test project using https://github.com/gary-rowe/hid4java

## Initial Setup
This project was initialized using

https://maven.apache.org/archetypes/maven-archetype-quickstart/

```
cd ..
mvn archetype:generate -B -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4  -DgroupId=com.paulfrischknecht.hid4javamvndemo  -Dpackage=com.paulfrischknecht.hid4javamvndemo -DartifactId=hid4java-maven-demo
```

the [hid4java dependency](https://github.com/gary-rowe/hid4java/tree/develop?tab=readme-ov-file#maven-dependency) was added to the pom.xml afterwards.

## Dependencies

Use Debian packages `openjdk-11-jdk` and `maven`.

## Working in an IDE
Tested in vscode & IntelliJ