ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.3"

val zioVersion = "2.1.1"
val zioHttpVersion = "3.0.0-RC6" // Перевірте версію, актуальна для Scala 3
val quillVersion = "4.8.4"
val circeVersion = "0.14.7"

lazy val root = (project in file("."))
  .settings(
    name := "scala-clean-user-microservice",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,
      "org.scalacheck" %% "scalacheck" % "1.17.0" % Test,

      // ZIO
      "dev.zio" %% "zio" % "2.1.1",
      "dev.zio" %% "zio-http" % "3.0.0-RC6",
      "dev.zio" %% "zio-test" % "2.0.15" % Test,
      "dev.zio" %% "zio-test-sbt" % "2.0.15" % Test,
      "dev.zio" %% "zio-logging" % "2.1.3",
      "dev.zio" %% "zio-logging-slf4j" % "2.1.3",
      "dev.zio" %% "zio-config" % "3.0.2",
      "dev.zio" %% "zio-config-typesafe" % "3.0.2",
      "dev.zio" %% "zio-config-magnolia" % "3.0.2",
      "dev.zio" %% "zio-kafka" % "2.0.0",
      //"dev.zio" %% "zio-kafka-testcontainers" % "2.0.0" % Test,

      // Database (Quill + HikariCP + JDBC Driver)
      "io.getquill" %% "quill-jdbc-zio" % quillVersion,
      "com.zaxxer" % "HikariCP" % "5.1.0",
      "org.postgresql" % "postgresql" % "42.7.3", // Або інший драйвер, якщо не Postgres

      // JSON (Circe)
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,

      // JWT
      "com.github.jwt-scala" %% "jwt-zio-json" % "10.0.1", // або jwt-core


      "dev.zio" %% "zio-json" % "0.3.0"
      //"dev.zio" %% "zio-redis" % "2.0.0",
      //"dev.zio" %% "zio-redis-testcontainers" % "2.0.0" % Test,
      //"dev.zio" %% "zio-mysql" % "2.0.0",
      //"dev.zio" %% "zio-mysql-testcontainers" % "2.0.0" % Test

    )
  )

