package com.fs.hcmgmt.util

object Constants {
    const val HEADER_TOKEN = "X-Auth-Token"
    const val PREF_TOKEN = "TOKEN"
    const val PREF_PROJECT = "PROJECT"
    const val PREF_ZONE = "ZONE"
}

enum class ECSOperationMethod(val type: String, val osName: String) {
    START("", "os-start"),
    STOP("HARD", "os-stop"),
    RESTART("SOFT", "reboot")
}

enum class ECSStatus() {
    ACTIVE, //The ECS is running properly.
    BUILD, //The ECS has been created but is not running.
    ERROR, //An error has occurred on the ECS.
    HARD_REBOOT, //The ECS is being forcibly restarted.
    MIGRATING, //The ECS is being live migrated.
    REBOOT, //The ECS is being restarted.
    REBUILD, //The ECS is being rebuilt.
    RESIZE, //The ECS has received a specifications modification request and has started to perform the modification.
    REVERT_RESIZE, //The ECS is rolling back resizing.
    SHUTOFF, //The ECS has been properly stopped.
    VERIFY_RESIZE, //The ECS is verifying the modified configuration.
    DELETED, //The ECS has been deleted.
    SHELVED, //The ECS boot from an image is shelved.
    SHELVED_OFFLOADED, //The ECS boot from a volume is shelved.
    UNKNOWN //The ECS status is unknown.
}