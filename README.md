<div align="center">
<img src="http://primefaces-extensions.github.io/reports/images/title.png" width="560" height="76" >

# PrimeFaces Extensions
</div>
<br>

[![Maven](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions.svg)](https://repo1.maven.org/maven2/org/primefaces/extensions/primefaces-extensions/)
[![Actions Status](https://github.com/primefaces-extensions/primefaces-extensions/workflows/Java%20CI/badge.svg)](https://github.com/primefaces-extensions/primefaces-extensions/actions)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=org.primefaces.extensions%3Aprimefaces-extensions-parent&metric=security_rating)](https://sonarcloud.io/dashboard?id=org.primefaces.extensions%3Aprimefaces-extensions-parent)
[![Discord Chat](https://img.shields.io/badge/chat-discord-7289da)](https://discord.gg/gzKFYnpmCY)
[![Stackoverflow](https://img.shields.io/badge/StackOverflow-primefaces-chocolate.svg)](https://stackoverflow.com/questions/tagged/primefaces-extensions)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is an overview page, please visit [primefaces-extensions.github.io](http://primefaces-extensions.github.io/) or the [showcase](https://www.primefaces.org/showcase-ext/) for more information.

## Versioning

There are multiple versions available please check which one for your PrimeFaces release version.

| Version | Explanation |
| --- | --- |
| ![8.x](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions?versionPrefix=8.0&color=cyan) | PrimeFaces 8.0 |
| ![10.x](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions?versionPrefix=10.0&color=cyan) | PrimeFaces 10.0.0 |
| ![11.x](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions?versionPrefix=11.0&color=cyan) | PrimeFaces 11.0.0 |
| ![12.x](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions?versionPrefix=12.0&color=cyan) | PrimeFaces 12.0.0 |
| ![13.x](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions?versionPrefix=13.0&color=cyan) | PrimeFaces 13.0.0 |
| ![14.x](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions?versionPrefix=14.0&color=cyan) | PrimeFaces 14.0.0 |
| ![15.x](https://img.shields.io/maven-central/v/org.primefaces.extensions/primefaces-extensions?versionPrefix=15.0&color=cyan) | PrimeFaces 14.0.0 |

## Maven

### Release (javax)

```xml
<dependency>
    <groupId>org.primefaces.extensions</groupId>
    <artifactId>primefaces-extensions</artifactId>
    <version>${primefaces-extensions.version}</version>
</dependency>
<dependency>
   <groupId>org.primefaces.extensions</groupId>
   <artifactId>resources-monacoeditor</artifactId>
   <version>${primefaces-extensions.version}</version>
</dependency>
```

### Release (jakarta)

```xml
<dependency>
    <groupId>org.primefaces.extensions</groupId>
    <artifactId>primefaces-extensions</artifactId>
    <version>${primefaces-extensions.version}</version>
    <classifier>jakarta</classifier>
</dependency>
```

### Snapshots

```xml
<repository>
    <id>sonatype-snapshots</id>
    <name>Sonatype Snapshot Repository</name>
    <url>https://s01.oss.sonatype.org/content/repositories/snapshots</url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>

<dependency>
   <groupId>org.primefaces.extensions</groupId>
   <artifactId>primefaces-extensions</artifactId>
   <version>${primefaces-extensions.version}</version>
</dependency>
```

## Namespaces

The PrimeFaces Extensions namespace is necessary to add PrimeFaces Extensions components to your pages.

```xml
xmlns:pe="http://primefaces.org/ui/extensions"
```

## Getting Started

Please refer to the [Getting Started Guide](https://github.com/primefaces-extensions/primefaces-extensions.github.com/wiki/Getting-Started) to see what you need
to get started and any optional libraries.

## Migration Guide

Please refer to the [Migration Guide](https://github.com/primefaces-extensions/primefaces-extensions.github.com/wiki/Migration-Guide).

## Demo

Please refer to the Showcase submodule in order to see the full usage of the components. Simply do the following to run the Showcase:

```sh
cd showcase
mvn clean jetty:run
```

Then open your web browser to http://localhost:8080/showcase-ext/

## Issues

Please report all issues [here](https://github.com/primefaces-extensions/primefaces-extensions/issues).

## Releasing

- Run `mvn versions:set -DgenerateBackupPoms=false -DnewVersion=8.0.2` to update all modules versions
- Commit and push the changes to GitHub
- In GitHub create a new Release titled `8.0.2` to tag this release
- Run `mvn clean deploy -Prelease` to push to Maven Central

## License

Licensed under the [MIT](https://en.wikipedia.org/wiki/MIT_License) license.

`SPDX-License-Identifier: MIT`

