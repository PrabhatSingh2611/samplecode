---
sidebar_position: 3
---

# wdx-i18n-core

The library adds i18n support for entities that are stored in the database.

Source code: https://bitbucket.org/windmillgmbh/wdx-apps/src/master/wdx-i18n-core/  
Nexus: https://nexus.dev.topazdigital.cloud/#browse/browse:wdx-i18n-core

## Add wdx-i18n-core dependency

```xml

<dependencies>
    <dependency>
        <groupId>digital.windmill</groupId>
        <artifactId>wdx-i18n-core</artifactId>
        <version>XXX</version>
    </dependency>
</dependencies>
```

```xml
<repositories>
    <repository>
        <id>nexus-dev-topazdigital</id>
        <url>https://nexus.dev.topazdigital.cloud/repository/wdx-i18n-core/</url>
    </repository>
</repositories>
```

_Please make sure that your mvn is configured properly to work with private Nexus repository. See [Setup mvn](development#setup-mvn) for more details_

## Import I18nConfig

```java

@Configuration
@Import(I18nConfig.class)
public class AppConfig {
}
```

## Add json column for field translations

```xml

<column name="title_i18n" type="jsonb" defaultValue="{}"/>
```

## Add entity property with type MultiLanguageText

```java
@Embedded
@AttributeOverride(name = "texts", column = @Column(name = "title_i18n", columnDefinition = "jsonb"))
private MultiLanguageText title;
```

## Use I18nMapper

```java

@Mapper(componentModel = "spring", uses = {I18nMapper.class})
public interface YourMapper {
}
```

## HTTP header

All GraphQL queries should contain "Language" HTTP header with current language.  
If the Language is not defined that default one will be used - EN.