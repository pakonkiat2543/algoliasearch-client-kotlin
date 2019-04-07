package documentation.methods.synonym

import com.algolia.search.model.synonym.Synonym
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocReplaceAllSynonym {

//    suspend fun Index.replaceAllSynonyms(
//        #{synonyms}: __List<Synonym>__,
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun snippet1() {
        runBlocking {
            // Fetch your synonyms
            val synonyms = listOf<Synonym>()

            index.replaceAllSynonyms(synonyms, forwardToReplicas = true)
        }
    }
}