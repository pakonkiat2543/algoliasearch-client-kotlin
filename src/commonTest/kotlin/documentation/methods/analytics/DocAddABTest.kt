package documentation.methods.analytics

import clientAnalytics
import com.algolia.search.model.ClientDate
import com.algolia.search.model.Time
import com.algolia.search.model.analytics.ABTest
import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.IgnorePlurals
import com.algolia.search.model.search.Query
import runBlocking
import suite.testSuiteIndexName
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAddABTest {

//    suspend fun ClientAnalytics.addABTest(
//        #{abTest}: __ABTest__,
//        requestOptions: __RequestOptions?__ = null
//    ): CreationABTest

    private val suffix = "snippet"
    private val indexName = testSuiteIndexName(suffix)
    private val indexName1 = indexName
    private val indexName2 = indexName.copy(indexName.raw + "_copy")

    @Test
    fun snippet1() {
        runBlocking {
            val dayInMilliseconds = 60 * 60 * 24 * 1000
            val abTest = ABTest(
                name = "myABTest",
                variantA = Variant(
                    indexName = indexName1,
                    trafficPercentage = 90,
                    description = "a description"
                ),
                variantB = Variant(
                    indexName = indexName2,
                    trafficPercentage = 10,
                    description = "a description"
                ),
                endAt = ClientDate(Time.getCurrentTimeMillis() + dayInMilliseconds)
            )

            clientAnalytics.addABTest(abTest)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val dayInMilliseconds = 60 * 60 * 24 * 1000
            val abTest = ABTest(
                name = "myABTest",
                variantA = Variant(
                    indexName = indexName1,
                    trafficPercentage = 90,
                    description = "a description"
                ),
                variantB = Variant(
                    indexName = indexName1,
                    trafficPercentage = 10,
                    description = "a description",
                    customSearchParameters = Query(ignorePlurals = IgnorePlurals.Boolean(true))
                ),
                endAt = ClientDate(Time.getCurrentTimeMillis() + dayInMilliseconds)
            )

            clientAnalytics.addABTest(abTest)
        }
    }
}