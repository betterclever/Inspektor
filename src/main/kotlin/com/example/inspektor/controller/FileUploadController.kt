package com.example.inspektor.controller

import com.example.inspektor.services.FileUploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("files")
class FileUploadController(val fileUploadService: FileUploadService) {

    @PostMapping
    suspend fun uploadFile(@RequestParam("file") file: MultipartFile): String {
        val fileLink = fileUploadService.uploadFile(file.bytes, file.name)
        return fileLink ?: throw Exception("Unable to Upload File")
    }

}