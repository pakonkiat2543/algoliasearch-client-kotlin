import client.ApiKey
import client.ApplicationId
import client.Client
import client.Index
import client.query.IndexQuery
import client.query.Query
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertTrue


@RunWith(JUnit4::class)
class TestEndpoints {

    private val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
    private val applicationId = ApplicationId("latency")
    private val api = Client(applicationId, apiKey)
    private val index = Index("products_android_demo")

    @Test
    fun listIndexes() {
        runBlocking {
            api.getListIndexes()
        }
    }

    @Test
    fun search() {
        runBlocking {
            api.search(index)
        }
    }

    @Test
    fun browse() {
        runBlocking {
            val responseA = api.browse(index)

            responseA.cursor?.let { api.browse(index, it) }
        }
    }

    @Test
    fun multiQueries() {
        runBlocking {
            val queries = listOf(
                IndexQuery(index, Query("a")),
                IndexQuery(index, Query("b"))
            )
            api.multipleQueries(queries)
        }
    }

    @Test
    fun searchForFacetValue() {
        runBlocking {
            val maxFacetHits = 2
            val response = api.searchForFacetValue(
                index,
                "color",
                maxFacetHits = maxFacetHits,
                facetQuery = "co",
                query = Query(maxFacetHits = maxFacetHits)
            )

            assertTrue(response.facetHits.size <= maxFacetHits)
        }
    }
}