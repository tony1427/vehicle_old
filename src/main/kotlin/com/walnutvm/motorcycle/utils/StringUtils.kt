package com.walnutvm.motorcycle.utils

import com.walnutvm.motorcycle.controller.ApplicationConstants.FORWARD_SLASH


val SLASH_REMOVING_REGEX = "(?<!(http:|https:))[//]+".toRegex()

fun String.formattedUrl(): String = this.replace(SLASH_REMOVING_REGEX, FORWARD_SLASH)