rootProject.name = "pirates"

includeProject("common")
includeProject("folia-common")
includeProject("folia-impl")
includeProject("proxy-common")
includeProject("proxy-impl")

fun includeProject(project: String) {
    include(":pirates-$project")
    findProject(":pirates-$project")?.name = project
}