call mvn package

REM run using maven exec plugin: https://www.baeldung.com/maven-java-main-method
call mvn compile exec:java -Dexec.mainClass="com.paulfrischknecht.hid4javamvndemo.App"

