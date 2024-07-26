plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "eightpotatoes"

include("core")
include("batch")
include("external-api")
include("internal-api")
include("scraping")
