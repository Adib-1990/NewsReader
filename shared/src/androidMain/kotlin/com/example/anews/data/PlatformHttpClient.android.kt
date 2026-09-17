package com.example.anews.data

import io.ktor.client.*
import io.ktor.client.engine.android.*

actual class PlatformHttpClient actual constructor() {
    actual val client: HttpClient = HttpClient(Android)
}
