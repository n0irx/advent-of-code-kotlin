import java.util.PriorityQueue

fun main() {
    // TC: O(n), only looping through the list of calories once
    // TC: O(1), only need to hold tempSum, and maxSum variable
    fun part1(input: List<String>): Int {
        var tempSum = 0; var maxSum = 0
        for (num in input) {
            if (num != "") {
                tempSum += Integer.parseInt(num)
                maxSum = maxOf(tempSum, maxSum)
            } else {
                tempSum = 0
            }
        }
        return maxSum
    }

    // TC: O(n), only looping through the list of calories once
    // TC: O(1), need to store 3 of the max calories of elf
    fun part2(input: List<String>): Int {
        // maxHeap will hold the order of max elf's calories
        val maxHeap = PriorityQueue<Int> { p1, p2 -> p1 - p2 }
        var tempSum = 0; var maxSum = 0
        for (num in input) {
            // empty string indicate a separator for each elf's calories
            if (num != "") {
                tempSum += Integer.parseInt(num)
                maxSum = maxOf(tempSum, maxSum)
            } else {
                // current elf's calories finished calculated
                maxHeap.add(tempSum)
                if (maxHeap.size > 3) {
                    maxHeap.poll()
                }
                tempSum = 0
            }
        }

        // get the max three value
        var maxThree = 0
        for (i in 1..3) {
            maxThree += maxHeap.poll()
        }
        return maxThree
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
