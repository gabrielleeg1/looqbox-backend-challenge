package com.gabrielleeg1.looqbox.utils

/**
 * Sorts a list with quick sort algorithm, in the worst cases is O(n^2), and in the remaining cases
 * is O(n log n).
 *
 * @receiver list of items to sort.
 * @param cmp the comparator to compare the correct order.
 * @return sorted list.
 */
fun <T> List<T>.quickSort(cmp: Comparator<T>): List<T> = when {
    size < 2 -> this
    else -> {
        val fst = first()
        val (smaller, greater) = drop(1).partition { cmp.compare(it, fst) < 0 }
        smaller.quickSort(cmp) + fst + greater.quickSort(cmp)
    }
}

/**
 * Sorts a list with quick sort algorithm using the [Comparable] interface, in the worst cases is
 * O(n^2), and in the remaining cases is O(n log n).
 *
 * @receiver list of items to sort.
 * @return sorted list.
 */
fun <T : Comparable<T>> List<T>.quickSort(): List<T> = quickSort { a, b -> a.compareTo(b) }
