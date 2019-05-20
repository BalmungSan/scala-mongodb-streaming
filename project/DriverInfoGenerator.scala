import sbt.Keys._
import sbt._

/** Generate the DriverInfo object with the name, version & platform of the driver. */
object DriverInfoGenerator {
  val task = Def.task {
    val cachedFun = FileFunction.cached(
      cacheBaseDirectory = streams.value.cacheDirectory,
      inStyle = FilesInfo.hash,
      outStyle = FilesInfo.exists
    ) { (in: Set[File]) =>
      val lines = List(
        "package com.github.balmungsan.mongodb.streaming",
        "private[streaming] object DriverInfo {",
        s"""final val Name: String = "${name.value}"""",
        s"""final val Version: String = "${version.value}"""",
        s"""final val Platform: String = "Scala ${scalaVersion.value}"""",
        "}"
      )

      val driverInfoFile = (Compile / sourceManaged).value / "DriverInfo.scala"
      IO.writeLines(driverInfoFile, lines)
      Set(driverInfoFile)
    }

    cachedFun(Set(file("build.sbt"), file("version.sbt"))).toSeq
  }
}
