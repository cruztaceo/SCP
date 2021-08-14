package models

import kotlinx.serialization.Serializable

@Serializable
data class MainSet(val XSize: Int, val F: Set<Set<Int>>){

    val X = IntArray(XSize){ it + 1 }

}