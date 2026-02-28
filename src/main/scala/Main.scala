/**
 * The main entry point of the application.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @since 15.02.2026 - 27.02.2026
 * @version 0.0.1
 */

package main

import zio._
import zio.http._
import repositories._
import controllers._
import layers.DataSourceLayer

object Main extends ZIOAppDefault {

  // Замість Http використовуємо Routes
  val allRoutes: Routes[Any, Response] = Routes(
    Method.GET / "hello" -> handler(Response.text("Hello ZIO HTTP 3.x!"))
  )

  // Main.scala
  override def run =
    Server.serve(allRoutes) // Просто передайте об'єкт
      .provide(Server.defaultWithPort(8082))

}


/*
object Main extends ZIOAppDefault {

  override def run = {
    // 1. Отримуємо репозиторій з оточення ZIO
    val program = for {
      repo <- ZIO.service[UserRepository]
      userController = UserController(repo)

      // 2. Об'єднуємо маршрути
      allRoutes = HomeController.routes ++ userController.routes

      // 3. Запускаємо сервер
      _ <- Server.serve(allRoutes.toHttpApp)
    } yield ()

    program.provide(
      Server.defaultWithPort(8082),
      UserRepositoryLive.layer, // Ваш реальний репозиторій
      DataSourceLayer.live      // Шар для підключення до БД (Hikari)
    )
  }
}*/


/*package main

import zio._
import zio.http._
import io.getquill._
import repositories._
import controllers._
import javax.sql.DataSource
import org.flywaydb.core.Flyway

object Main extends ZIOAppDefault {

  // --- Flyway миграции ---
  def runMigrations: ZIO[DataSource, Throwable, Unit] =
    ZIO.serviceWithZIO[DataSource] { ds =>
      ZIO.attempt {
        Flyway.configure()
          .dataSource(ds)
          .baselineOnMigrate(true)
          .load()
          .migrate()
      }
    }

  // --- DataSource ZLayer ---
  val connectionPool: ZLayer[Any, Throwable, DataSource] = ZLayer.scoped {
    ZIO.acquireRelease(
      ZIO.attempt {
        val ds = new org.postgresql.ds.PGSimpleDataSource()
        ds.setUrl("jdbc:postgresql://localhost:5432/yourdb")
        ds.setUser("user")
        ds.setPassword("pass")
        ds
      }
    )(ds => ZIO.attempt(ds.getConnection.close()).orDie)
  }

  // --- Quill контекст как ZLayer ---
  val dbContextLayer: ZLayer[DataSource, Nothing, PostgresZioJdbcContext[SnakeCase]] =
    ZLayer.fromFunction((_: DataSource) => new PostgresZioJdbcContext(SnakeCase))

  // --- Маршруты ---
  val allRoutes: Http[Any, Throwable, Request, Response] =
    UserController.routes.merge(HomeController.routes) // merge вместо ++ в ZIO HTTP 3.x

  // --- Main ---
  override def run: ZIO[Any with ZIOAppArgs with Scope, Throwable, Unit] = {
    val program = for {
      _ <- runMigrations
      _ <- Server.serve(allRoutes)
    } yield ()

    program.provide(
      Server.defaultWithPort(8082),
      connectionPool,
      dbContextLayer,
      UserRepositoryLive.layer
    )
  }
}*/


/*@main
def main(): Unit = {
  println("Hello world, from Scala!")
  hello("TechMatrix18")
}

def hello(name: String): Unit = {
  println(s"Hello, $name!")
}*/

