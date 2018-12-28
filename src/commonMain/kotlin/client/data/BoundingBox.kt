package client.data

import client.serialize.Deserializer
import client.serialize.Serializer
import client.serialize.unwrap
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonArray


data class BoundingBox(
    val point1: Float,
    val point2: Float,
    val point3: Float,
    val point4: Float
) {

    val asList = listOf(point1, point2, point3, point4)

    internal companion object : Serializer<BoundingBox>, Deserializer<BoundingBox> {

        override fun serialize(input: BoundingBox?): JsonElement {
            return input.unwrap {
                jsonArray {
                    asList.forEach {
                        +(it as Number)
                    }
                }
            }
        }

        override fun deserialize(element: JsonElement): BoundingBox? {
            return when (element) {
                is JsonArray -> {
                    val array = element.jsonArray

                    BoundingBox(
                        array.getPrimitive(0).float,
                        array.getPrimitive(1).float,
                        array.getPrimitive(2).float,
                        array.getPrimitive(3).float
                    )
                }
                else -> null
            }
        }
    }
}