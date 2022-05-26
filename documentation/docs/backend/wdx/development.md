---
sidebar_position: 2
---

# Development

Source code: https://bitbucket.org/windmillgmbh/wdx-apps/  
Nexus: https://nexus.dev.topazdigital.cloud/

## Setup mvn

For working with private Nexus repository, mvn should be configured properly.

Put this to your `~/.m2/settings.xml` file:

```xml

<settings xmlns="http://maven.apache.org/SETTINGS/1.1.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd">
    <servers>
        <server>
            <id>nexus-dev-topazdigital</id>
            <username>backend-user</username>
            <password>XXX</password>
        </server>
    </servers>
</settings>
```

_Please contact our DevOps team if you don't know password_

More information about configuring mvn and Nexus can be
found [here](https://blog.sonatype.com/using-nexus-3-as-your-repository-part-1-maven-artifacts).

## Publish to Nexus

For deploying a library to Nexus use the following command:

```shell
mvn clean deploy
```

_Please don't forget to commit your changes to Bitbucket repository_