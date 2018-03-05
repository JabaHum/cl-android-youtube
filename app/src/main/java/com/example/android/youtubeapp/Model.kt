package com.example.android.youtubeapp

/**
 * Created by idorenyin on 2/27/18.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Model(
        @SerializedName("resources") @Expose var resources:MutableList<Resource>,
        @SerializedName("next_cursor") @Expose var nextCursor:String,
        @SerializedName("rate_limit_allowed") @Expose var rateLimitAllowed:String,
        @SerializedName("rate_limit_reset_at") @Expose var rateLimitResetAt:String,
        @SerializedName("rate_limit_remaining") @Expose var rateLimitRemaining:Int)

data class Resource (
        @SerializedName("public_id") @Expose var publicId:String,
        @SerializedName("format") @Expose var format:String,
        @SerializedName("version") @Expose var version:Int,
        @SerializedName("resource_type") @Expose var resourceType:String,
        @SerializedName("type") @Expose var type:String,
        @SerializedName("created_at") @Expose var createdAt:String,
        @SerializedName("bytes") @Expose var bytes:Int,
        @SerializedName("width") @Expose var width:Int,
        @SerializedName("height") @Expose var height:Int,
        @SerializedName("backup") @Expose var backup:Boolean,
        @SerializedName("url") @Expose var url:String,
        @SerializedName("secure_url") @Expose var secureUrl:String,
        @SerializedName("placeholder") @Expose var placeholder:Boolean)





