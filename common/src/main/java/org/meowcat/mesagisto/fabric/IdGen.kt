package org.meowcat.mesagisto.fabric

import org.meowcat.mesagisto.fabric.Mod.CONFIG
import java.util.concurrent.atomic.AtomicInteger

object IdGen {
  private val cur by lazy { AtomicInteger(CONFIG.idBase) }
  fun gen(): Int {
    val v = cur.getAndIncrement()
    CONFIG.idBase = v
    return v
  }
}
