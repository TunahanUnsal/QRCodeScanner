package com.ezdream.qrscanner.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Movie
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import java.io.InputStream

object UiUtil {

    fun customAlertDialog(context: Context, message: String) {
        val builder = AlertDialog.Builder(context)

        with(builder)
        {
            setMessage(message)
            setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            show()
        }
    }

    fun showSnackBar(parentView: View, message: String) {
        val snack = Snackbar.make(parentView, message, Snackbar.LENGTH_SHORT)
        snack.show()
    }

    fun getDuration(context: Context, id: Int): Int {
        val temp: InputStream = context.resources.openRawResource(id)
        val movie = Movie.decodeStream(temp)  //deprecation i know but i can not find new one in AnimationDrawable
        return movie.duration()
    }

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity.currentFocus
        if (currentFocusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusedView.windowToken, 0)
        }
    }
}