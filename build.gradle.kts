plugins {
    this.id("java-library")
    this.id("com.github.johnrengelman.shadow").version("8.1.1").apply(false)
}

group = "me.conclure"
version = "1.0-SNAPSHOT"

subprojects {
    this.apply(plugin = "java-library")
    this.apply(plugin = "com.github.johnrengelman.shadow")

    this.layout.buildDirectory = this.rootProject.layout.buildDirectory.dir(this.name)

    this.java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

    this.repositories {
        this.mavenCentral()
    }

    this@subprojects.tasks {
        this.test {
            this.useJUnitPlatform()
        }

        this.compileJava {
            this.options.encoding = "UTF-8"
            this.options.release.set(17)
        }

        this.processResources {
            this.filteringCharset = "UTF-8"
        }

        this.create<DefaultTask>("produceJar") {
            this.group = "pirates"
            this.dependsOn("shadowJar")
        }
    }

    this.dependencies {
        this.testImplementation(this.platform("org.junit:junit-bom:5.9.1"))
        this.testImplementation("org.junit.jupiter:junit-jupiter")
    }
}

rootProject.tasks.forEach {
    it.enabled = false
    it.dependsOn.clear()
}