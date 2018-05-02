name := "ferris-json-utils"

organization := "com.ferris"

version := "0.0.2"

scalaVersion := "2.12.1"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaHttpV = "10.0.1"
  Seq(
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV
  )
}
