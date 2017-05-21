import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  // Confirmed fails with 0.7.6, 0.9.0
  lazy val ammonite = "com.lihaoyi" % "ammonite" % "0.9.0" cross CrossVersion.full
}
