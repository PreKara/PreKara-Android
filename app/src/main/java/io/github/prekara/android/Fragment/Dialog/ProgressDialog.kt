package io.github.prekara.android.Fragment.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import io.github.prekara.android.R

/**
 * Created by developer on 4/3/18.
 */

class ProgressDialog: DialogFragment() {
    companion object {
        fun getInstance(): ProgressDialog {
            return ProgressDialog()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(activity.layoutInflater.inflate(R.layout.progress_dialog, null))

        return builder.create()
    }
}