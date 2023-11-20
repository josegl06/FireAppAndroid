package com.auslander.fireapp.placespicker.model

import androidx.annotation.Keep
import com.auslander.fireapp.placespicker.model.Venue
import com.google.gson.annotations.SerializedName

@Keep
data class Response(
        @SerializedName("confident")
        val confident: Boolean,
        @SerializedName("venues")
        val venues: List<Venue>
)