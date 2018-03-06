package com.example.android.youtubeapp

/**
 * Created by idorenyin on 2/27/18.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Model(
        @SerializedName("resources") var resources: MutableList<Resource>,
        @SerializedName("next_cursor") var nextCursor: String,
        @SerializedName("rate_limit_allowed") var rateLimitAllowed: String,
        @SerializedName("rate_limit_reset_at") var rateLimitResetAt: String,
        @SerializedName("rate_limit_remaining") var rateLimitRemaining: Int)

data class Resource(
        @SerializedName("public_id") var publicId: String,
        @SerializedName("format") var format: String,
        @SerializedName("version") var version: Int,
        @SerializedName("resource_type") var resourceType: String,
        @SerializedName("type") var type: String,
        @SerializedName("created_at") var createdAt: String,
        @SerializedName("bytes") var bytes: Int,
        @SerializedName("width") var width: Int,
        @SerializedName("height") var height: Int,
        @SerializedName("backup") var backup: Boolean,
        @SerializedName("url") var url: String,
        @SerializedName("secure_url") var secureUrl: String,
        @SerializedName("placeholder") var placeholder: Boolean)





