package com.tavieto.groupsgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<View>(R.id.button)
        button.setOnClickListener { start() }
    }

    private fun start() {
        val allGroups = findViewById<EditText>(R.id.numberGroups).text.toString().toInt()
        val allStudents = findViewById<EditText>(R.id.numberStudents).text.toString().toInt()

        val studentsPerGroup = separatePerGroup(allGroups, allStudents)
        val randomNumbers = generateRandomNumbers(allStudents)

        findViewById<TextView>(R.id.result).text = division(allGroups, studentsPerGroup, randomNumbers)

    }
}

private fun separatePerGroup(allGroups: Int, allStudents: Int): IntArray {

    var studentsInEachGroup = IntArray(allGroups)
    val excess = allStudents % allGroups

    if (excess == 0) {

        val studentThisGroup: Double = allStudents / allGroups.toDouble()

        var count = 0
        while (count < allGroups) {
            studentsInEachGroup[count] = studentThisGroup.toInt()
            count++
        }

    } else {

        val studentThisGroup: Double = (allStudents - excess) / allGroups.toDouble()
        var count = 0

        while (count < allGroups) {
            studentsInEachGroup[count] = studentThisGroup.toInt()
            count++
        }

        var addOne = 0
        var wasAdd = 0

        while(addOne < allGroups) {
            if(wasAdd < excess) {
                studentsInEachGroup[addOne] = studentsInEachGroup[addOne] + 1
                wasAdd++
            }
            addOne++
        }
    }

    return studentsInEachGroup
}

fun generateRandomNumbers(allStudents: Int): IntArray {

    var intRange = 1..allStudents
    var arrayOfRandom = IntArray(allStudents)
    var counter = 0


    while(counter < allStudents){
        var randomNum = intRange.random()

        var countVerify = 0
        while(countVerify < allStudents){
            if(randomNum == arrayOfRandom[countVerify]) {
                randomNum = intRange.random()
                countVerify = -1
            }
            countVerify++
        }

        arrayOfRandom[counter] = randomNum
        counter++
    }

    return arrayOfRandom
}

fun division(allGroups: Int, studentsPerGroup: IntArray, randomNumbers: IntArray): String {
    var wasPresented = 0
    var counterGroups = 0
    var storageText = ""

    while(counterGroups < allGroups) {
        storageText += ("Grupo ${counterGroups+1} -> ")

        var counterElement = 0
        while(counterElement < studentsPerGroup[counterGroups]) {

            storageText += ("${randomNumbers[wasPresented]}")

            if(counterElement != studentsPerGroup[counterGroups] - 1){
                storageText += (" - ")
            } else {
                storageText += ("; ")
            }
            counterElement++
            wasPresented++
        }
        storageText += ("\n")
        counterGroups++
    }

    return storageText
}