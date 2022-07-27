package org.meowcat.mesagisto.fabric.impl

import org.meowcat.mesagisto.fabric.logger
import org.mesagisto.mcmod.api.ICompat
import org.mesagisto.mcmod.api.Log4jLogger

class ICompatImpl : ICompat by CompatImpl

object CompatImpl : ICompat {
  override fun getLogger(): Log4jLogger = logger
}
