import de.heikoseeberger.sbtheader.License
import java.time.Year

// Application dependencies.
val MongoReactiveStreamsVersion = "1.11.0"

lazy val commonSettings = Seq(
  ThisBuild / scalaVersion := "2.12.8",
  crossScalaVersions := Seq("2.12.8"),

  ThisBuild / organization := "com.github.balmungsan",
  ThisBuild / homepage := Some(url("https://github.com/BalmungSan/scala-mongodb-streaming")),
  ThisBuild / description := "Scala lightweight, type-safe, streaming driver for MongoDB.",

  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "utf-8",
    "-explaintypes",
    "-feature",
    "-unchecked",
    "-Dkp:genAsciiNames=true", // For kind-projector.
    "-Xfatal-warnings",
    "-Xlint:constant",
    "-Xlint:inaccessible",
    "-Xlint:infer-any",
    "-Xlint:missing-interpolator",
    "-Xlint:nullary-override",
    "-Xlint:nullary-unit",
    "-Xlint:option-implicit", // Warn if an implicit conversion was applied before a call to Option.apply.
    "-Xlint:package-object-classes",
    "-Xlint:private-shadow",
    "-Xlint:stars-align", // See https://github.com/scala/scala/pull/4216
    "-Xlint:type-parameter-shadow",
    "-Xlint:unsound-match",
    "-Ypartial-unification",
    "-Ywarn-macros:after", // Execute the linter after macro expansion.
    "-Ywarn-dead-code",
    "-Ywarn-extra-implicit",
    "-Ywarn-numeric-widen",
    "-Ywarn-unused:implicits",
    "-Ywarn-unused:imports",
    "-Ywarn-unused:locals",
    "-Ywarn-unused:params",
    "-Ywarn-unused:patvars",
    "-Ywarn-unused:privates",
    "-Ywarn-value-discard"
  ),

  // Disable the linter and warning flags on the console.
  scalacOptions in (Compile, console) ~= {
    _.filterNot(flag => flag.startsWith("-Xlint") || flag.startsWith("-Ywarn"))
  },

  // Kind projector.
  addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.0"),

  // Better monadic for.
  addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.0"),

  // License.
  organizationName := "All scala-mongodb-streaming contributors",
  startYear := Some(2019),
  licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt")),
  headerLicense := Some(License.ALv2(
    s"${startYear.value.get}-${Year.now}",
    organizationName.value
  ))
)

lazy val root =
  project
    .in(file("."))
    .aggregate(
      core
    )

lazy val core =
  project
    .in(file("core"))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(
      commonSettings,
      name := "scala-mongodb-streaming",
      libraryDependencies ++= Seq(
        "org.mongodb" % "mongodb-driver-reactivestreams" % MongoReactiveStreamsVersion,
      )
    )
