package com.sokheang.mediaparknews.utils.views

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sokheang.mediaparknews.databinding.LayoutSortByBinding

/**
 * Create by Sokheang RET on 21-Aug-22.
 **/
class BottomSheetSortBy(context: Context,val onUploadDateClicked: () -> Unit, val onRelevanceClicked: () -> Unit,): BottomSheetDialog(context) {
    init {
        val binding= LayoutSortByBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.radioGroupSort.setOnCheckedChangeListener { _, id ->
            if(id == binding.radioByDate.id) {
                onUploadDateClicked()
            } else {
                onRelevanceClicked()
            }
            dismiss()
        }
    }
}