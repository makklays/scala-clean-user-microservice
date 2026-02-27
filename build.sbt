ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-clean-user-microservice",
    libraryDependencies ++= Seq(
      // ZIO
      "dev.zio" %% "zio" % "2.0.20",
      "dev.zio" %% "zio-http" % "3.0.0-RC2",

      // JSON через Circe
      "io.circe" %% "circe-core" % "0.15.0-M1",
      "io.circe" %% "circe-generic" % "0.15.0-M1",
      "io.circe" %% "circe-parser" % "0.15.0-M1",

      // JWT
      "com.github.jwt-scala" %% "jwt-core" % "9.2.0",

      // Тестирование
      "dev.zio" %% "zio-test" % "2.0.20" % Test,
      "dev.zio" %% "zio-test-sbt" % "2.0.20" % Test
    )
  )

