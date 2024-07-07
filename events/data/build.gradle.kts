plugins {
    alias(libs.plugins.stoiximan.android.library)
    alias(libs.plugins.stoiximan.jvm.ktor)
}

android {
    namespace = "com.fps.events.data"
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(projects.events.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
    implementation(projects.core.database)

}