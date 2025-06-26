package com.electronicmuse.comicsrss

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.coroutines.runBlocking
import java.nio.charset.StandardCharsets

class Lambda : RequestHandler<Map<String, Any>, String> {

    var fileAccess: FileAccess? = null

    override fun handleRequest(input: Map<String, Any>?, context: Context?): String? {
        val mapper: ObjectMapper = YAMLMapper()
        mapper.findAndRegisterModules()
        val configuration: Configuration = mapper.readValue(javaClass.classLoader.getResource("config.yaml")!!)
        val outputWriter: FileAccess = fileAccess ?: S3FileAccess(configuration.region, configuration.bucket, configuration.prefix, publicRead = true)
        val links = configuration.providers.flatMap { provider -> provider.provideLinks() }
        val feedWriter = FeedWriter()
        val xml = feedWriter.writeComics(links)
        runBlocking {
            outputWriter.writeData(configuration.rssOutputPath, xml.toByteArray(StandardCharsets.UTF_8))
        }
        return xml
    }

}
