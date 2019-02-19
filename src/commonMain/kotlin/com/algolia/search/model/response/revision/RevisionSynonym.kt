package com.algolia.search.model.response.revision

import com.algolia.search.ClientDate
import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyId
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.KeyUpdatedAt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RevisionSynonym(
    @SerialName(KeyUpdatedAt) val updatedAt: ClientDate,
    @SerialName(KeyId) val objectID: ObjectID,
    @SerialName(KeyTaskID) override val taskID: TaskID
) : Task