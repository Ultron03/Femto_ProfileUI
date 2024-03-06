pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (
            url="https://phonepe.mycloudrepo.io/public/repositories/phonepe-intentsdk-android"
        )

    }
}

rootProject.name = "Femto_ProfileUI"
include(":app")
 