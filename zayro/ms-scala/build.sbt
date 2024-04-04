name := """apiScala"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.12"


libraryDependencies += filters
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % Test
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.2"


libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "42.2.24",
  "com.typesafe.play" %% "play-jdbc" % "2.5.8",
  "mysql" % "mysql-connector-java" % "5.1.36"
)