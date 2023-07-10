package com.example.myapplication

class VO {

    class Response {

        data class Result(
            val images_results: List<OrganicResults>,
//            val pagination: Pagination
        ) {

            data class OrganicResults(
                val position: Int,
                val thumbnail: String,
                val related_content_id: String,
                val serpapi_related_content_link: String,
                val source: String,
                val title: String,
                val link: String,
                val original: String,
                val original_width: Int,
                val original_height: Int,
                val is_product: Boolean
            )

//            data class Pagination(
//                val current: Int,
//                val next: String
//            )
        }
    }
}