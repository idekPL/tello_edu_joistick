# jtellolib
[![pipeline status](https://gitlab.com/utp-bydgoszcz/jtellolib/badges/master/pipeline.svg)](https://gitlab.com/utp-bydgoszcz/jtellolib/commits/master)

Ryze Tello and Ryze Tello Edu library for java

### Using with Maven project
Add repository section to pom.xml:
```xml
<repositories>
    <repository>
        <id>dynak-nexus</id>
        <name>Dymek - nexus</name>
        <url>https://dymek.utp.edu.pl/nexus/repository/maven-public/</url>
    </repository>
</repositories>
```
Then add dependency:
```xml
<dependency>
    <groupId>pl.edu.utp</groupId>
    <artifactId>jtellolib</artifactId>
    <!-- find the latest available version here:
        https://dymek.utp.edu.pl/nexus/service/rest/repository/browse/maven-public/pl/edu/utp/jtellolib/
    -->
    <version>0.1</version>
</dependency>
```


### Using example
Connect to Your drone, and run this code:
```java
DronesManager dm = new DronesManager();
Tello t1 = dm.connect();

t1.takeoff();
t1.waitForResponse(5000);

t1.forward(100);
t1.waitForResponse(5000);

t1.flip(LEFT);
t1.waitForResponse(3000);

t1.land();
```

### TODO
- data parser and listener
- more examples
