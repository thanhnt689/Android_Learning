package com.ntt.androidday18.jsoup

import android.util.Log
import com.ntt.androidday18.model.Question
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

object JsoupParser {

    fun getAllQuestion(tag: String): List<Question> {
        val link = "https://en.wikipedia.org/wiki/Main_Page"
        val questions = arrayListOf<Question>()
        val document: Document = Jsoup.connect(link).get()
//        Log.d("doanpt", "$document")
        val firstTitle = document.getElementById("From_today's_featured_article")
        Log.d("thanhnt", "title:${firstTitle?.text()}")
        val thumbImages = document.getElementsByClass("thumbinner mp-thumb")
        Log.d("thanhnt", "thumb size:${thumbImages.size}")
        for (thumbElement in thumbImages) {
            val imageElements = thumbElement.getElementsByTag("img")
            if (imageElements.size > 0) {
                val src = imageElements[0].attr("src")
                Log.d("thanhnt", "src:$src")
            }
        }
        return questions
    }
}