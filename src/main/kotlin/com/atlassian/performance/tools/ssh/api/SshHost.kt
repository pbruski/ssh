package com.atlassian.performance.tools.ssh.api

import java.nio.file.Path
import java.nio.file.Paths
import javax.json.Json
import javax.json.JsonObject

/**
 * Holds SSH coordinates.
 *
 * @param ipAddress IP of the remote system.
 * @param userName User allowed to connect to the remote server.
 * @param key Private SSH key that authenticates the user.
 */
data class SshHost(
    val ipAddress: String,
    val userName: String,
    val key: Path,
    val port: Int
) {
    constructor(json: JsonObject) : this(
        ipAddress = json.getString("ipAddress"),
        userName = json.getString("userName"),
        key = Paths.get(json.getString("key")),
        port = 22
    )

    constructor(
        ipAddress: String,
        userName: String,
        key: Path
    ) : this(
        ipAddress = ipAddress,
        userName = userName,
        key = key,
        port = 22
    )

    fun toJson(): JsonObject {
        return Json.createObjectBuilder()
            .add("ipAddress", ipAddress)
            .add("userName", userName)
            .add("key", key.toAbsolutePath().toString())
            .build()
    }
}