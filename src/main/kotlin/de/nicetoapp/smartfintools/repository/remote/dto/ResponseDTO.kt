package de.nicetoapp.smartfintools.repository.remote.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseDTO(
//    val ident: IdentDTO?,
//    val status: StatusDTO?,
//    val parameter: ParameterDTO?,
    @JsonProperty("object")  val responseObject: ObjectDTO?, // Adjusted the name to `object` using backticks
//    val copyright: String?
)

data class IdentDTO(
    val service: String?,
    val method: String?
)

data class StatusDTO(
    val code: Int,
    val content: String?,
    val type: String?
)

data class ParameterDTO(
    val username: String?,
    val password: String?,
    val name: String?,
    // Add other fields as necessary
)

data class ObjectDTO(
    val content: String?, // This will need custom parsing
    val structure: StructureDTO?
)

data class StructureDTO(
    val head: HeadDTO?,
    val columns: List<ColumnDTO>?,
    val rows: List<RowDTO>?,
    val subtitel: String?,
    val subheading: String?
)

data class HeadDTO(
    val code: String?,
    val content: String?,
    val type: String?,
    val values: String?,
    val selected: String?,
    val structure: List<StructureDTO>?,
    val updated: String?
)

data class ColumnDTO(
    val code: String?,
    val content: String?,
    val type: String?,
    val values: String?,
    val selected: String?,
    val structure: List<StructureDTO>?,
    val updated: String?
)

data class RowDTO(
    val code: String?,
    val content: String?,
    val type: String?,
    val values: String?,
    val selected: String?,
    val structure: List<StructureDTO>?,
    val updated: String?
)
