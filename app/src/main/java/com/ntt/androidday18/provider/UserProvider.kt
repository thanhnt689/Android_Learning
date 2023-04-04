package com.ntt.androidday18.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

import android.content.UriMatcher
import com.ntt.androidday18.db.UserDAO
import com.ntt.androidday18.db.entity.UserDatabase

class UserProvider : ContentProvider() {
    companion object {
        private const val AUTHORITY = "com.ntt.androidday18.provider.UserProvider"

        //content://com.ddona.l2011service.provider.StudentProvider/student
        private val STUDENT_CONTENT_URI = Uri.parse("content://$AUTHORITY/user")
        private const val CODE_ALL_USER = 1
        private const val CODE_ONE_USER = 2
        private val mUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    }

    private lateinit var userDao: UserDAO

    override fun onCreate(): Boolean {
        userDao = UserDatabase.getInstance(context!!).getUserDao()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}