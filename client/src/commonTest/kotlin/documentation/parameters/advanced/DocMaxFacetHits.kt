package documentation.parameters.advanced

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocMaxFacetHits {

//    maxFacetHits: Int = number_of_facet_hits

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                maxFacetHits = 10
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                maxFacetHits = 5
            }

            index.search(query)
        }
    }
}
