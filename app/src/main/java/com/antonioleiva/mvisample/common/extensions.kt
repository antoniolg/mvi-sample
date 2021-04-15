package com.antonioleiva.mvisample.common

import android.content.Context
import android.view.View
import android.widget.Toast

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }