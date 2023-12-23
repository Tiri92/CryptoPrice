import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import thierry.cryptoprice.libs

class HiltJvmConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")

                dependencies {
                    add("implementation", libs.findLibrary("hilt").get())
                    add("kapt", libs.findLibrary("hiltCompiler").get())
                }
            }

            extensions.configure<KaptExtension> {
                correctErrorTypes = true
            }
        }
    }
}