package org.example
import kotlin.random.Random

fun generateProductStringsArray(size: Int): Array<String> {
    val array = Array(size) { "" }
    for (i in 0 until size) {
        val a = Random.nextInt(1, 100)
        val b = Random.nextInt(1, 100)
        val c = Random.nextInt(1, 100)
        val d = Random.nextInt(1, 100)
        array[i] = "$a,$b;$c,$d"
    }
    return array
}

fun parseMatrix(input: String): Array<IntArray>? {
    val rows = input.split(";")
    val matrix = Array(rows.size) { IntArray(rows[0].split(",").size) }

    for (i in rows.indices) {
        val elements = rows[i].split(",")
        if (elements.size != matrix[0].size) {
            return null
        }

        for (j in elements.indices) {
            matrix[i][j] = elements[j].toIntOrNull() ?: return null
        }
    }

    return matrix
}

//kotlin.csv problem with installation
//draft for parseMatrix(not tested)
//fun parseMatrixCsv(input: String): Array<IntArray>? {
 //   val rows = csvReader().readAll(input).map { it.map { it.toInt() }.toIntArray() }.toTypedArray()
//
 //

 //   return rows
//

fun scalarProduct(matrix1: Array<IntArray>, matrix2: Array<IntArray>): Array<IntArray> {
    val result = Array(matrix1.size) { IntArray(matrix2[0].size) }

    for (i in matrix1.indices) {
        for (j in matrix2[0].indices) {
            result[i][j] = (0 until matrix1[0].size).sumOf { k -> matrix1[i][k] * matrix2[k][j] }
        }
    }

    return result
}

fun main(args: Array<String>) {
    val args= generateProductStringsArray(2)

    if (args.size != 2) {
        println("Пожалуйста, введите две матрицы для вычисления скалярного произведения.")
        return
    }

    val matrix1 = parseMatrix(args[0])
    val matrix2 = parseMatrix(args[1])

    if (matrix1 == null || matrix2 == null) {
        println("Ошибка при чтении матриц. Пожалуйста, убедитесь, что ввод корректен.")
        return
    }

    if (matrix1[0].size != matrix2.size) {
        println("Невозможно вычислить скалярное произведение. Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы.")
        return
    }

    val result = scalarProduct(matrix1, matrix2)
    println("Скалярное произведение матриц:")
    for (row in result) {
        for (elem in row) {
            print("$elem ")
        }
        println()
    }
}


