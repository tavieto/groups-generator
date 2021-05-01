package br.com.tavieto.groupsgenerator

import android.util.Log

class Create {
    private var _listOfGroups = mutableListOf<Group>()
    private val listOfGroups: List<Group> = _listOfGroups

    fun start(allGroups: Int, allStudents: Int): List<Group> {
        val randomNumbers = generateRandomNumbers(allStudents)
        val studentsPerGroup = separatePerGroup(allGroups, allStudents)

        createGroupsList(allGroups, studentsPerGroup, randomNumbers)

        return listOfGroups
    }

    private fun generateRandomNumbers(allStudents: Int): List<Int> {
        Log.i("TESTE", "generateRandomNumbers")

        val intRange = 1..allStudents
        val arrayOfRandom = IntArray(allStudents)
        var counter = 0

        while (counter < allStudents) {
            var randomNum = intRange.random()

            var countVerify = 0
            while (countVerify < allStudents) {
                if (randomNum == arrayOfRandom[countVerify]) {
                    randomNum = intRange.random()
                    countVerify = -1
                }
                countVerify++
            }

            arrayOfRandom[counter] = randomNum
            counter++
        }

        return arrayOfRandom.toList()
    }

    private fun separatePerGroup(allGroups: Int, allStudents: Int): List<Int> {

        Log.i("TESTE", "separatePerGroup")

        val studentsInEachGroup = IntArray(allGroups)
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

            while (addOne < allGroups) {
                if (wasAdd < excess) {
                    studentsInEachGroup[addOne] = studentsInEachGroup[addOne] + 1
                    wasAdd++
                }
                addOne++
            }
        }

        return studentsInEachGroup.toList()
    }

    private fun createGroupsList(
        allGroups: Int,
        studentsPerGroup: List<Int>,
        randomNumbers: List<Int>
    ) {
        Log.i("TESTE", "createGroupList")
        Log.i("TESTE", "Estudantes por grupo: $studentsPerGroup")

        var counterGroups = 0
        var init = 0
        var final = studentsPerGroup[counterGroups]

        while (counterGroups < allGroups) {

//            Log.i("TESTE", "index-1: $final")

            _listOfGroups.add(
                Group(
                    (counterGroups + 1),
                    studentsPerGroup[counterGroups],
                    randomNumbers.toList().subList(init, final)
                )
            )

//            Log.i("TESTE", "index-2: $final")

            Log.i("TESTE", randomNumbers.toList().subList(init, final).toString())

            init += studentsPerGroup[counterGroups]

            if (counterGroups != allGroups-1) {
                final += studentsPerGroup[counterGroups + 1]
            }

//            final += studentsPerGroup[counterGroups + 1]

//            Log.i("TESTE", "index-3: $final")

            counterGroups++

//            Log.i("TESTE", "index-4: $final")
        }


    }
}