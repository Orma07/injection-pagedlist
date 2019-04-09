package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

object NavigationUtils {

    val TAG = "NavigationUtils"

    val REQUEST_CAMERA = 1
    val REQUEST_GALLERY = 2

    // region Activity

    /**
     * Opens an Activity and closes all the others.
     *
     * @param context The current context. You should usually pass getContext().getApplicationContext()
     * as you want to put this Activity in front of all the others and close them all.
     * @param activityClass The class of the Activity you want to open.
     */
    fun openAsRoot(context: Context, activityClass: Class<out Activity>) {
        val intent = Intent(context, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }


    fun next(context: Context, activityClass: Class<out Activity>) {
        val intent = Intent(context, activityClass)
        context.startActivity(intent)
    }

    /**
     * @param intent if null open Activity in activityClass.
     * @param fragment is not null if I want to open activity from fragment
     */
    fun nextForResult(context: Context, fragment: Fragment?, activityClass: Class<out Activity>, requestCode: Int, intent: Intent?) {
        var intent = intent
        if (intent == null) {
            intent = Intent(context, activityClass)
        }
        if (fragment != null)
            fragment.startActivityForResult(intent, requestCode)
        else
            (context as Activity).startActivityForResult(intent, requestCode)
    }

    fun nextForResult(context: Context, fragment: Fragment?, action: String, uri: Uri, requestCode: Int, intent: Intent?) {
        var intent = intent
        if (intent == null) {
            intent = Intent(action, uri)
        }
        if (fragment != null)
            fragment.startActivityForResult(intent, requestCode)
        else
            (context as Activity).startActivityForResult(intent, requestCode)
    }
    // endregion Activity


    // region Fragment
    /**
     * @param containerViewId FrameLayoud id
     */
    fun nextFragment(fragmentManager: FragmentManager, @IdRes containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.addToBackStack(null) //This means that the transaction will be remembered after it is committed, and will reverse its operation when later popped off the stack
        fragmentTransaction.commit()
    }

    fun replaceFragment(fragmentManager: FragmentManager, @IdRes containerViewId: Int, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(containerViewId, fragment)
        fragmentTransaction.commit()
    }
    // endregion Fragment

    fun back(context: AppCompatActivity) {
        if (context.supportFragmentManager != null && context.supportFragmentManager.backStackEntryCount > 0) {
            context.supportFragmentManager.popBackStack()
        } else {
            context.onBackPressed()
        }

    }
}