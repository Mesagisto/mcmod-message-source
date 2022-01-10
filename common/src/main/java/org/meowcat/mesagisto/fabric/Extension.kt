package org.meowcat.mesagisto.fabric

import org.meowcat.mesagisto.client.ILogger
import org.meowcat.mesagisto.client.LogLevel
import org.meowcat.mesagisto.client.Logger

typealias StdLogger = org.apache.logging.log4j.Logger

fun Logger.bridgeToLog4j(impl: StdLogger) {
  level = when (impl.level) {
//    Level.ALL -> LogLevel.TRACE
//    Level.FINE -> LogLevel.TRACE
//    Level.INFO -> LogLevel.TRACE
//    Level.WARNING -> LogLevel.WARN
//    Level.SEVERE -> LogLevel.ERROR
//    Level.OFF -> LogLevel.ERROR
    else -> { LogLevel.TRACE }
  }
  provider = object : ILogger {
    override fun log(level: LogLevel, msg: String) {
      when (level) {
//        LogLevel.TRACE -> impl.fine(msg)
//        LogLevel.DEBUG -> impl.finer(msg)
        LogLevel.INFO -> impl.info(msg)
        else -> impl.info(msg)
      }
    }
  }
}
