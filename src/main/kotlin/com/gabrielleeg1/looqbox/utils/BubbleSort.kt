package com.gabrielleeg1.looqbox.utils

/**
 * Sorts a list with bubble sort algorithm, in the best case is O(n), and in the remaining cases
 * is O(n^2).
 *
 * @receiver list of items to sort.
 * @param cmp the comparator to compare the correct order.
 * @return sorted list.
 */
fun <T> List<T>.bubbleSort(cmp: Comparator<T>): List<T> {
    return toMutableList().apply {
        for (pos in 0 until size - 1) {
            // if the element isn't in the correct order, continue
            if (cmp.compare(this[pos], this[pos + 1]) < 0) continue

            //  swap elements
            val tmp = this[pos]
            this[pos] = this[pos + 1]
            this[pos + 1] = tmp
        }
    }
}

/**
 * Sorts a list with bubble sort algorithm using the [Comparable] interface, in the best case is O(n),
 * and in the remaining cases is O(n^2).
 *
 * @receiver list of items to sort.
 * @return sorted list.
 */
fun <T : Comparable<T>> List<T>.bubbleSort(): List<T> = bubbleSort { a, b -> a.compareTo(b) }
