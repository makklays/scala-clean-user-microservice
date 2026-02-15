ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-clean-user-microservice",
    idePackagePrefix := Some("com.techmatrix18")
  )
