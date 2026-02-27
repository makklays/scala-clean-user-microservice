package controllers

import zio._
import zio.http._

/**
 * Controller for handling home page requests.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @since 27.02.2026
 * @version 0.0.1
 */
object HomeController {

  // Use App[Any], which is an alias for HttpApp[Any, Throwable], to define the routes
  /*val routes: App[Any] = Http.collectZIO[Request] {
    case Method.GET -> Root =>
      ZIO.succeed(Response.text("Hello ZIO Http in your Scala project!"))
  }*/

  val routes: App[Any] = (Http.collectZIO[Request] {
    case Method.GET -> Root =>
      ZIO.succeed(Response.text("Hello ZIO!"))
  }: HttpApp[Any, Throwable]).catchAllZIO { err =>
    ZIO.succeed(Response.status(Status.InternalServerError))
  }

}

