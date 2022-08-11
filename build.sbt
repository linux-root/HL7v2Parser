
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "HL7V2Parser"
  )

libraryDependencies += "ca.uhn.hapi" % "hapi-base" % "2.3"
// https://mvnrepository.com/artifact/ca.uhn.hapi/hapi-structures-v24
libraryDependencies += "ca.uhn.hapi" % "hapi-structures-v22" % "2.3"
libraryDependencies += "ca.uhn.hapi" % "hapi-structures-v23" % "2.3"
libraryDependencies += "ca.uhn.hapi" % "hapi-structures-v24" % "2.3"
libraryDependencies += "ca.uhn.hapi" % "hapi-structures-v25" % "2.3"
libraryDependencies += "ca.uhn.hapi" % "hapi-structures-v26" % "2.3"
libraryDependencies += "ca.uhn.hapi" % "hapi-structures-v27" % "2.3"
libraryDependencies += "ca.uhn.hapi" % "hapi-structures-v28" % "2.3"
libraryDependencies += "org.json" % "json" % "20220320"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.2"
libraryDependencies += "org.scalatest"   %% "scalatest"       % "3.2.12"   % Test

