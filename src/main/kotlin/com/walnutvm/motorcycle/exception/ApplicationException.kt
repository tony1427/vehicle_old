package com.walnutvm.motorcycle.exception

import java.lang.RuntimeException

class NotFoundException: RuntimeException()
class BadActionException(message: String): RuntimeException()