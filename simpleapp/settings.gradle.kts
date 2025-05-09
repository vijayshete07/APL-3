pluginManagement {
    repositories {
        google()  // Plugin repositories
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()  // Dependency repositories
        mavenCentral()
    }
}

rootProject.name = "simpleapp"
include(":app")
