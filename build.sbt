import Dependencies._

lazy val buildSettings = Defaults.coreDefaultSettings ++ Seq(
      organization := "com.example",
      scalaVersion := "2.11.8",
      version      := "0.1.0-SNAPSHOT"
    )

lazy val root = (project in file(".")).
  settings(
    buildSettings ++ Seq(
      name := "ammonite-failure-case-test",
      libraryDependencies += ammonite,
      libraryDependencies += scalaTest % Test
    )
  )
