package com.deutschebahn.movieapi

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MovieApiTest {

    @Test fun `can create instance`() {
        val sut = MovieApi.create(
            MovieApiConfig()
        )

        assertNotNull(sut)
    }

    @Test fun `can request now play`() = runBlocking {
        val sut = MovieApi.create(
            MovieApiConfig()
        )

        val result = sut.nowPlaying()

        with(result) {
            assertNotNull(this)
            assertTrue(this.hasResult)
        }
    }

    @Test fun `can get details for id`() = runBlocking {
        val sut = MovieApi.create(
            MovieApiConfig()
        )

        val result = sut.details(123)
        val test = """
            INFORMATION: {"adult":false,"backdrop_path":"/jOuCWdh0BE6XPu2Vpjl08wDAeFz.jpg","belongs_to_collection":null,"budget":4000000,"genres":[{"id":12,"name":"Abenteuer"},{"id":16,"name":"Animation"},{"id":14,"name":"Fantasy"}],"homepage":"","id":123,"imdb_id":"tt0077869","original_language":"en","original_title":"The Lord of the Rings","overview":"Der Herr der Ringe ist ein Zeichentrickfilm aus dem Jahr 1978, dessen Handlung die erste Hälfte der Geschichte von J.R.R. Tolkiens 'Der Herr der Ringe' erzählt. Regisseur Ralph Bakshi drehte hierbei nahezu alle Szenen mit realen Schauspielern, die dann im sogenannten Rotoskopie-Verfahren nachgezeichnet wurden. Aufgrund des mäßigen Erfolges blieb Bakshi jedoch eine Fortsetzung verwehrt.","popularity":23.554,"poster_path":"/oNOiV1wlIsbfQCoXNb5xV7wPwe5.jpg","production_companies":[{"id":286,"logo_path":null,"name":"Fantasy Films","origin_country":""},{"id":4921,"logo_path":null,"name":"Bakshi Productions","origin_country":""},{"id":141322,"logo_path":null,"name":"Saul Zaentz Film Productions","origin_country":""}],"production_countries":[{"iso_3166_1":"ES","name":"Spain"},{"iso_3166_1":"GB","name":"United Kingdom"},{"iso_3166_1":"US","name":"United States of America"}],"release_date":"1978-11-15","revenue":30471420,"runtime":132,"spoken_languages":[{"english_name":"English","iso_639_1":"en","name":"English"}],"status":"Released","tagline":"","title":"Der Herr der Ringe","video":false,"vote_average":6.6,"vote_count":651}
        """.trimIndent()

        with(result) {
            assertNotNull(this)
            assertTrue(this.hasResult)
        }
    }

    @Test fun `can request search`() = runBlocking {
        val sut = MovieApi.create(
            MovieApiConfig()
        )

        val result = sut.search("test")

        with(result) {
            assertNotNull(this)
            assertTrue(this.hasResult)
        }
    }
}