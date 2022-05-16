package documentation.reference

import clientPlaces
import com.algolia.search.client.ClientPlaces
import com.algolia.search.dsl.countries
import com.algolia.search.dsl.placesQuery
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.ObjectID
import com.algolia.search.model.places.PlacesQuery
import com.algolia.search.model.search.Language
import com.algolia.search.model.search.Point
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocPlaces {

    private val client = ClientPlaces()

    @Test
    fun unauthenticated() {
        runTest {
            ClientPlaces()
        }
    }

    @Suppress("UNUSED_VARIABLE")
    @Test
    fun authenticated() {
        runTest {
            val client = ClientPlaces(
                ApplicationID("YourApplicationID"),
                APIKey("YourPlacesAPIKey")
            )
        }
    }

    @Test
    fun multipleLanguage() {
        runTest {
            val response = client.searchPlaces(PlacesQuery("Paris"))

            response.hits.first().city.getValue(Language.English)
        }
    }

    @Test
    fun oneLanguage() {
        runTest {
            val response = client.searchPlaces(
                query = PlacesQuery("New-York"),
                language = Language.English
            )

            response.hits.first().city
        }
    }

    @Test
    fun multipleCountries() {
        runTest {
            val query = placesQuery("York") {
                countries {
                    +UnitedKingdom
                    +UnitedStates
                }
            }

            client.searchPlaces(query)
        }
    }

    @Test
    fun aroundLatLng() {
        runTest {
            val query = placesQuery {
                aroundLatLng = Point(40.7128f, -74.0060f) // New-York
            }

            client.searchPlaces(query)
        }
    }

    @Test
    fun reverseGeoCoding() {
        runTest {
            client.reverseGeocoding(Point(40.7128f, -74.0060f)) // New-York
        }
    }

    @Test
    fun getByObjectID() {
        runTest {
            clientPlaces.getByObjectID(ObjectID("201316654_7340078")) // New-York
        }
    }
}
