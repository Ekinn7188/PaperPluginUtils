# PaperPluginUtils
A personal utility api used to help create minecraft plugins for PaperMC

# Usage with Gradle
If for some reason you want to use this, one way you can do it is clone the git repo and run ``./gradlew publishMavenPublicationToMavenLocal``. Then, add the mavenLocal() repo and the implementation for this api
```gradle
repositories {
    mavenLocal()
}
```


```gradle
dependencies {
    implementation 'jeeper.utils:PaperPluginUtils:0.1.0'
}
```
