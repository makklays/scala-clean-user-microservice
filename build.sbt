ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "scala-clean-user-microservice",
    //libraryDependencySchemes += "dev.zio" %% "zio-json" % "early-semver",
    libraryDependencies ++= Seq(
      // Testing
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,
      "org.scalacheck" %% "scalacheck" % "1.17.0" % Test,

      // ZIO Core and HTTP
      "dev.zio" %% "zio" % "2.1.1",
      "dev.zio" %% "zio-http" % "3.8.1",
      "dev.zio" %% "zio-json" % "0.7.2",

      // Database (Quill + HikariCP + JDBC Driver)
      "io.getquill" %% "quill-zio" % "4.8.6",
      "io.getquill" %% "quill-jdbc-zio" % "4.8.6",

      // Authentication (JWT)
      "com.github.jwt-scala" %% "jwt-core" % "10.0.1",
      "org.postgresql" % "postgresql" % "42.7.3",

      // Database Migration (Flyway)
      "org.flywaydb" % "flyway-core" % "12.0.1",
      "org.flywaydb" % "flyway-database-postgresql" % "12.0.1",

      // JSON (Circe)
      "io.circe" %% "circe-core" % "0.14.7",
      "io.circe" %% "circe-generic" % "0.14.7",
      "io.circe" %% "circe-parser" % "0.14.7",

      // Logging
      "dev.zio" %% "zio-logging-slf4j" % "2.1.3",
      "org.slf4j" % "slf4j-simple" % "2.0.9",

      // Redis та Schema
      "dev.zio" %% "zio-redis" % "1.1.12",
      "dev.zio" %% "zio-schema" % "1.6.0",
      "dev.zio" %% "zio-schema-derivation" % "1.6.0",
      "dev.zio" %% "zio-schema-json" % "1.6.0",

      // Kafka
      "dev.zio" %% "zio-kafka" % "2.0.0"

      //"dev.zio" %% "zio-test" % "2.0.15" % Test,
      //"dev.zio" %% "zio-test-sbt" % "2.0.15" % Test,
      //"dev.zio" %% "zio-logging" % "2.1.3",
      //"dev.zio" %% "zio-config" % "3.0.2",
      //"dev.zio" %% "zio-config-typesafe" % "3.0.2",
      //"dev.zio" %% "zio-config-magnolia" % "3.0.2",
      //

      //"dev.zio" %% "zio-mysql" % "2.0.0"
    )
  )

