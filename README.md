# mvn-assembly-issue
Parent project cannot assembly children artifacts with named classifier if children packaging is `pom`

The current project structure consists of:

* `parent (packaging=pom)`: assemblies all module artifacts with attachmentClassifier `module`
* `child1 (packaging=pom)`: compiles java example, jars it and creates an additional assembly `module`

If we change `child1` packaging to `jar`, the parent will happily package it.
