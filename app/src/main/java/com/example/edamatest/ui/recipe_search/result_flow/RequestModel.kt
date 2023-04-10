package com.example.edamatest.ui.recipe_search.result_flow

import android.os.Parcel
import android.os.Parcelable

data class RequestModel(
    val keyWord: String,
    val diet: List<String>,
    val health: List<String>,
    val cuisineType: List<String>,
    val nutrients: Map<String, String>
) : Parcelable {

    constructor(parcel: Parcel) : this(
        keyWord = parcel.readString() ?: "",
        diet = parcel.createStringArrayList() ?: emptyList(),
        health= parcel.createStringArrayList() ?: emptyList(),
        cuisineType = parcel.createStringArrayList() ?: emptyList(),
        nutrients = readStringMap(parcel) ?: emptyMap()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(keyWord)
        parcel.writeStringList(diet)
        parcel.writeStringList(health)
        parcel.writeStringList(cuisineType)
        writeStringMap(nutrients, parcel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RequestModel> {
        override fun createFromParcel(parcel: Parcel): RequestModel {
            return RequestModel(parcel)
        }

        override fun newArray(size: Int): Array<RequestModel?> {
            return arrayOfNulls(size)
        }

        private fun writeStringMap(map: Map<String, String>, parcel: Parcel) {
            parcel.writeInt(map.size)
            for ((key, value) in map) {
                parcel.writeString(key)
                parcel.writeString(value)
            }
        }

        private fun readStringMap(parcel: Parcel): Map<String, String>? {
            val size = parcel.readInt()
            if (size < 0) {
                return null
            }
            val map = mutableMapOf<String, String>()
            for (i in 0 until size) {
                val key = parcel.readString() ?: ""
                val value = parcel.readString() ?: ""
                map[key] = value
            }
            return map
        }
    }
}