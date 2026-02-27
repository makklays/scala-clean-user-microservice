package controllers

import zio._
import zio.http._
import io.circe._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._
import models.User
import services.JwtService
import scala.collection.concurrent.TrieMap

/**
 * Controller for managing users, including CRUD operations and JWT token generation.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @since 27.02.2026
 * @version 0.0.1
 */

/*object UserController {
  // use App[Any] instead of HttpApp[Any] to avoid type mismatch with Http.collectZIO
  val routes: App[Any] = Http.collectZIO[Request] {
    case Method.GET -> Root / "users" =>
      ZIO.succeed(Response.text("Hello Users"))
    case Method.GET -> Root / "health" =>
      ZIO.succeed(Response.text("OK"))
  }.catchAllZIO { _ =>
    ZIO.succeed(Response.status(Status.InternalServerError))
  }
}*/

object UserController {

  // Temporary in-memory storage for users. In a real application, this would be replaced with a database.
  private val users = TrieMap.empty[String, User]

  // Use App[Any] instead of HttpApp[Any] to avoid type mismatch with Http.collectZIO
  val routes: App[Any] = Http.collectZIO[Request] {

    // GET /api/v1/users/{id}
    case Method.GET -> Root / "api" / "v1" / "users" / id =>
      users.get(id) match {
        case Some(user) => ZIO.succeed(Response.json(user.asJson.noSpaces))
        case None       => ZIO.succeed(Response.status(Status.NotFound))
      }

    // POST /api/v1/users/add
    case req @ Method.POST -> Root / "api" / "v1" / "users" / "add" =>
      for {
        body <- req.body.asString.orElseFail(new Exception("Empty body"))
        user <- ZIO.fromEither(decode[User](body)).mapError(e => new Exception(e.getMessage))
        _    = users.put(user.id, user)
        token = JwtService.createAccessToken(user.id)
      } yield Response.text(s"User added. JWT: $token")

    // PATCH /api/v1/users/{id}
    case req @ Method.PATCH -> Root / "api" / "v1" / "users" / id =>
      for {
        body    <- req.body.asString.orElseFail(new Exception("Empty body"))
        updated <- ZIO.fromEither(decode[User](body)).mapError(e => new Exception(e.getMessage))
        _       = users.update(id, updated)
      } yield Response.text(s"User $id updated")

    // DELETE /api/v1/users/{id}
    case Method.DELETE -> Root / "api" / "v1" / "users" / id =>
      ZIO.succeed {
        users.remove(id)
        Response.text(s"User $id deleted")
      }

    // Old health check endpoint
    case Method.GET -> Root / "health" =>
      ZIO.succeed(Response.text("OK"))

  }.catchAllZIO { err =>
    ZIO.logError(s"Error: ${err.getMessage}") *>
      ZIO.succeed(Response.text(err.getMessage).withStatus(Status.BadRequest))
  }
}

