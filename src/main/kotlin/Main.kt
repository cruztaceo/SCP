import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.MainSet
import java.io.File

fun main() {
    val inputFileName = "src/main/resources/InputData.json"
    val json = readFileAsLinesUsingBufferedReader(inputFileName)
    val mainSet = Json.decodeFromString<MainSet>(json)

    val result = greedySetCover(mainSet.X.toSet(), mainSet.F);

    result.forEach { println(it.toString()) }

    println("Validation = ${checkResult(result, mainSet.X)}")

}

/**
 * Read file
 *
 * @param fileName Read file
 * @return List of strings read
 */
fun readFileAsLinesUsingBufferedReader(fileName: String): String = File(fileName).bufferedReader().use { it.readText() }


fun greedySetCover(X: Set<Int>, F: Set<Set<Int>>): MutableSet<Set<Int>> {
    var U = X
    val C = mutableSetOf<Set<Int>>()

    while (U.isNotEmpty()) {
        val S = F.maxByOrNull { subset -> subset.intersect(U).size }
        if (S != null) {
            U = U subtract S
            C.add(S)
        }
    }
    return C
}

fun checkResult(C: MutableSet<Set<Int>>, X: IntArray): Boolean = C.flatten().containsAll(X.asList())