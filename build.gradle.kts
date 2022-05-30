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
            version = "1.2"

            from(components["java"])
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    //minecraft
    paperDevBundle("1.18.2-R0.1-SNAPSHOT")

    //yaml
    implementation("org.yaml:snakeyaml:1.30")
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}