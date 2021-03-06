import Dependencies._

lazy val buildSettings = Defaults.coreDefaultSettings ++ Seq(
      organization := "com.example",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )

// Doesn't help in Ammonite, but will fix the same issue in console:
val failInSbtConsole = true
val failInSbtConsoleSettings = 
  if (failInSbtConsole) Seq()
  else Seq("-Yno-load-impl-class")

lazy val root = (project in file(".")).
  settings(
    buildSettings ++ Seq(
      scalacOptions ++= failInSbtConsoleSettings,
      name := "ammonite-failure-case-test",
      libraryDependencies += ammonite,
      libraryDependencies += scalaTest % Test
    )
  )
