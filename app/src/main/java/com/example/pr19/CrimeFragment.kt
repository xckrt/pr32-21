package com.example.pr19

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import java.util.Date
import java.util.UUID
import android.text.format.DateFormat
import java.time.LocalDateTime
import java.time.ZoneId

class CrimeFragment : Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Инициализация объекта Crime
        crime = Crime(id = UUID.randomUUID(), title = "Убийство", isSloved = true, date = LocalDateTime.now()) // Изменено на LocalDateTime.now()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)

        // Инициализация View-элементов
        titleField = view.findViewById(R.id.crime_title)
        dateButton = view.findViewById(R.id.crime_date)
        solvedCheckBox = view.findViewById(R.id.crime_solved)

        // Преобразование LocalDateTime в Date
        val date = Date.from(crime.date.atZone(ZoneId.systemDefault()).toInstant())

        // Локализованное форматирование даты
        val dateString = DateFormat.getDateFormat(requireContext()).format(date)
        dateButton.text = dateString
        dateButton.isEnabled = false

        return view
    }

    override fun onStart() {
        super.onStart()

        // Добавляем TextWatcher для отслеживания изменений в поле titleField
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Ничего не делаем
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString() // Обновляем заголовок преступления
            }

            override fun afterTextChanged(s: Editable?) {
                // Ничего не делаем
            }
        }
        titleField.addTextChangedListener(titleWatcher)

        // Обработка состояния чекбокса solvedCheckBox
        solvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSloved = isChecked // Обновляем состояние "решено"
                dateButton.isEnabled = isChecked // Включаем или отключаем кнопку даты

                Snackbar.make(requireView(), "Crime solved: $isChecked", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}
