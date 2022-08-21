package com.sokheang.mediaparknews.ui.article_filter

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.sokheang.mediaparknews.app.MediaparkNewsApp
import com.sokheang.mediaparknews.databinding.ActivityArticleFilterBinding
import com.sokheang.mediaparknews.ui.article_search_in.ArticleSearchInActivity
import com.sokheang.mediaparknews.utils.Constants
import java.text.SimpleDateFormat
import javax.inject.Inject

class ArticleFilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleFilterBinding

    @Inject
    lateinit var viewModel: ArticleFilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MediaparkNewsApp).getApplicationComponent().inject(this)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //Assign data from search screen to fill in with current screen
        viewModel.fromDate.value = intent.getStringExtra(Constants.IntentConstants.FROM_PUBLISH_DATE).toString()
        viewModel.toDate.value = intent.getStringExtra(Constants.IntentConstants.TO_PUBLISH_DATE).toString()
        viewModel.searchIn.value = intent.getStringExtra(Constants.IntentConstants.SEARCH_IN).toString()

        //Navigate to Search In screen allow user to select which option will be search in
        binding.layoutSearchIn.setOnClickListener {
            val intent = Intent(this@ArticleFilterActivity, ArticleSearchInActivity::class.java).apply {
                putExtra(Constants.IntentConstants.SEARCH_IN, viewModel.searchIn.value)
            }
            resultLauncher.launch(intent)
        }

        //show date picker dialog for choose from date
        binding.editFromDate.setOnClickListener {
            showDatePickerDialog(binding.editFromDate)
        }
        //show date picker dialog for choose to date
        binding.editToDate.setOnClickListener {
            showDatePickerDialog(binding.editToDate)
        }

        setUpToolbar()

        binding.buttonApplyFilter.setOnClickListener {
            sendDataToSearchScreen()
        }
    }

    //Send data that user just input from this screen to search screen
    private fun sendDataToSearchScreen() {
        val intent = Intent().apply {
            putExtra(Constants.IntentConstants.FROM_PUBLISH_DATE, viewModel.fromDate.value)
            putExtra(Constants.IntentConstants.TO_PUBLISH_DATE, viewModel.toDate.value)
            putExtra(Constants.IntentConstants.SEARCH_IN, viewModel.searchIn.value)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun setUpToolbar() {
        //Show clear button and add listener
        binding.toolbar.textClear.visibility = View.VISIBLE
        binding.toolbar.textClear.setOnClickListener {
            clearFilter()
        }

        //Show back button and add listener
        binding.toolbar.buttonBack.visibility = View.VISIBLE
        binding.toolbar.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun clearFilter() {
        viewModel.fromDate.value = ""
        viewModel.toDate.value = ""
    }

    //Register for activity result
    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent = result.data!!
            viewModel.searchIn.value = data.getStringExtra(Constants.IntentConstants.SEARCH_IN).toString()

        }
    }

    //Show date picker dialog
    private fun showDatePickerDialog(editText: EditText) {
        val initDateMilli: Long
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        //Try to find initial date and show on dialog
        initDateMilli = if(editText.text.toString().isNotEmpty()) {
            val initDate = simpleDateFormat.parse(editText.text.toString())
            initDate!!.time + 86400000 //+ 1 day to get correct select date
        } else {
            MaterialDatePicker.todayInUtcMilliseconds()
        }

        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .setSelection(initDateMilli)
                .build()

        datePicker.show(supportFragmentManager, "TAG")
        datePicker.addOnPositiveButtonClickListener {
            val dateFormatted = simpleDateFormat.format(it)
            //set data to edit text
            editText.setText(dateFormatted)
        }
    }
}