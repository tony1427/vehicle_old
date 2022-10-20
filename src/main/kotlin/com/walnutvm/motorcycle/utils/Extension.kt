package com.walnutvm.motorcycle.utils

import com.walnutvm.motorcycle.config.Logging
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <T : Logging> T.logger(): Logger = LoggerFactory.getLogger(javaClass)