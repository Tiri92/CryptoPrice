import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinParcelizeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-parcelize")
            }
        }
    }
}