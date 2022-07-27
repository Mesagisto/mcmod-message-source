package org.mesagisto.mcmod

import org.meowcat.mesagisto.client.ILogger
import org.meowcat.mesagisto.client.LogLevel

typealias Log4jLogger = org.apache.logging.log4j.Logger
typealias MesagistoLogger = org.meowcat.mesagisto.client.Logger
fun MesagistoLogger.bridgeToLog4j(impl: Log4jLogger) {
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
