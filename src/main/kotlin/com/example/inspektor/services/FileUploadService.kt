package com.example.inspektor.services

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.*


data class ApiResponse(
        val success: Boolean,
        val key: String?,
        val link: String?,
        val expiry: String?
)

interface FileIOUploadService {

    @Multipart
    @POST("/")
    suspend fun uploadFile(@Part("file") name: RequestBody, @Part part: MultipartBody.Part): ApiResponse

}

class FileUploadService {

    private val transferFileUploadService: FileIOUploadService
    init {
        val objectMapper = ObjectMapper()
                .registerModule(KotlinModule())

        val retrofit = Retrofit.Builder()
                .baseUrl("https://file.io")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build()
        transferFileUploadService = retrofit.create(FileIOUploadService::class.java)
    }

    suspend fun uploadFile(byteArray: ByteArray, name: String): String? {
        val requestBody = RequestBody.create(MediaType.parse("image/*"), byteArray)
        val part = MultipartBody.Part.createFormData("file", name, requestBody)

        val result = transferFileUploadService.uploadFile(requestBody, part)
        return result.link
    }
}