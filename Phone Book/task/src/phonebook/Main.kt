package phonebook

import java.io.File

const val MAX = 500

fun main() {
    // val path = System.getProperty("user.dir")
    // println(path)
    val find = File("/home/link/github-classroom/IESLuisVives/Phone Book/find.txt").readLines()
    var directory = File("/home/link/github-classroom/IESLuisVives/Phone Book/directory3.txt").readLines()

    directory = directory.map { it.substring(it.indexOf(" ") + 1) }

    // Stage 1
    stage1(find, directory)

    // Stage 2
    stage2(find, directory)

    // Stage 3
    stage3(find, directory)
    
    // Stage 4
    stage4(find, directory)



}

/**
 * Hash Table
 */
fun stage4(find: List<String>, directory: List<String>) {
    val entries = find.size
    var found = 0

    println("Start searching (hash table)...")
    val timeStart = System.currentTimeMillis()
    // hash map
    val hashmap = getHashMap(directory)
    val hashEnd = System.currentTimeMillis()
    // Hash Search
    for (i in 0 until entries) {
        if (hashmap.containsKey(find[i].hashCode())) {
            found++
        }
    }
    val timeEnd = System.currentTimeMillis()

    println("Found $MAX / $entries entries. " +
            "Time taken: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (timeEnd - timeStart)))
    println("Creating time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (hashEnd - timeStart)))
    println("Searching time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (timeEnd - hashEnd)))
    println()
}

/**
 * Creating has tab
 */
fun getHashMap(directory: List<String>): HashMap<Int, String> {
    val hashmap = HashMap<Int, String>()
    for (i in directory.indices) {
        val hash = directory[i].hashCode()
        hashmap[hash] = directory[i]
    }
    return hashmap
}
/**
 * Stage 3: QuickSort and Binary Search
 */
fun stage3(find: List<String>, directory: List<String>) {
    val entries = find.size
    var found = 0

    println("Start searching (quick sort + binary search)...")
    val timeStart = System.currentTimeMillis()
    // Bubble sort
    val list = directory.toMutableList().sort() // Esto ya hace el quick sort
    // bubbleSort(list)
    val sortEnd = System.currentTimeMillis()
    // Binbary search
    for (i in 0 until entries) {
        val index = directory.binarySearch { it.compareTo(find[i]) }
        if (index == 0) {
            found++
        }
    }
    val timeEnd = System.currentTimeMillis()

    println("Found $MAX / $entries entries. " +
            "Time taken: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (timeEnd - timeStart)))
    println("Sorting time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (sortEnd - timeStart)))
    println("Searching time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (timeEnd - sortEnd)))
    println()
}

/**
 * Stage 2: Bubble sort and jump search
 */
fun stage2(find: List<String>, directory: List<String>) {
    val entries = find.size
    var found = 0

    println("Start searching (bubble sort + jump search)...")
    val timeStart = System.currentTimeMillis()
    // Bubble sort
    val list = directory.toMutableList()
    bubbleSort(list)
    val bubbleEnd = System.currentTimeMillis()
    // Jump search
    for (i in 0 until entries) {
        val index = jumpSearch(list, find[i])
        if (index != -1) {
            found++
        }
    }
    val timeEnd = System.currentTimeMillis()

    println("Found $MAX / $entries entries. " +
            "Time taken: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (timeEnd - timeStart)))
    println("Sorting time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (bubbleEnd - timeStart)))
    println("Searching time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (timeEnd - bubbleEnd)))
    println()

}

/**
 * Bubble sort
 * @param list List to sort
 */
fun bubbleSort(directory: MutableList<String>) {
    // Bubble sort
    for (i in 0 until directory.size - 1) {
        for (j in 0 until directory.size - i - 1) {
            if (directory[j] > directory[j + 1]) {
                val temp = directory[j]
                directory[j] = directory[j + 1]
                directory[j + 1] = temp
            }
        }
    }
}

/**
 * Jump search
 * @param list List to search
 * @param x element to search
 * @return index of element or -1 if not found
 */
fun jumpSearch(arr: List<String>, x: String): Int {
    val n = arr.size

    // Finding block size to be jumped
    var step = Math.floor(Math.sqrt(n.toDouble())).toInt()

    // Finding the block where element is
    // present (if it is present)
    var prev = 0
    while (arr[Math.min(step, n) - 1] < x) {
        prev = step
        step += Math.floor(Math.sqrt(n.toDouble())).toInt()
        if (prev >= n) return -1
    }

    // Doing a linear search for x in block
    // beginning with prev.
    while (arr[prev] < x) {
        prev++

        // If we reached next block or end of
        // array, element is not present.
        if (prev == Math.min(step, n)) return -1
    }

    // If element is found
    return if (arr[prev] == x) prev else -1
}

/**
 * Linear search
 */
fun stage1(find: List<String>, directory: List<String>) {
    val entries = find.size
    var found = 0

    println("Start searching (linear search)...")
    val timeStart = System.currentTimeMillis()
    for (i in 0 until entries) {
        val index = linearSearch(directory, find[i])
        if (index != -1) {
            found++
        }
    }
    val timeEnd = System.currentTimeMillis()
    println("Found $MAX / $entries entries. " +
            "Time taken: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", (timeEnd - timeStart)))
    println()

}

/**
 * Linear search
 * @param list List to search
 * @param x element to search
 * @return index of element or -1 if not found
 */
fun linearSearch(directory: List<String>, s: String): Any {
    for (i in directory.indices) {
        if (directory[i] == s) {
            return i
        }
    }
    return -1
}
