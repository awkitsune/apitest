package com.awkitsune.owmtest

import com.google.gson.annotations.SerializedName

data class FoxResponse (
    @SerializedName("image") val image: String,
    @SerializedName("link") val link: String
    )
