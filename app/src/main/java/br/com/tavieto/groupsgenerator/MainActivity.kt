package br.com.tavieto.groupsgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var groupsRecyclerView: RecyclerView
    private lateinit var groupsNumber: AppCompatEditText
    private lateinit var studentsNumber: AppCompatEditText
    private lateinit var calculate: AppCompatButton
    private val adapter by lazy { Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        groupsRecyclerView = findViewById(R.id.recyclerView_result)
        groupsRecyclerView.adapter = adapter
        calculate = findViewById(R.id.btn_result)

        groupsNumber = findViewById(R.id.edt_number_groups)
        studentsNumber = findViewById(R.id.edt_number_students)

        calculate.setOnClickListener {
            val textGroupsNumber = groupsNumber.text.toString()
            val textStudentsNumber = studentsNumber.text.toString()

            if (verifyInputValue(textGroupsNumber, textStudentsNumber)) {
                adapter.setItems(
                    Create().start(
                        textGroupsNumber.toInt(),
                        textStudentsNumber.toInt()
                    )
                )
            } else {
                adapter.setItems(
                    emptyList()
                )
            }
        }
    }

    private fun verifyInputValue(
        textGroupsNumber: String,
        textStudentsNumber: String
    ): Boolean {
        return !((textGroupsNumber == "" || textGroupsNumber == "0") || (textStudentsNumber == "" || textStudentsNumber == "0"))
    }
}