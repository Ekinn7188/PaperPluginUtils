plugins {
    `maven-publish`
    id("java")
    id("io.papermc.paperweight.userdev") version "1.3.6"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "jeeper.utils"
            artifactId = "PaperPluginUtils"
            version = "1.3"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //minecraft
    paperDevBundle("1.19-R0.1-SNAPSHOT")
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}