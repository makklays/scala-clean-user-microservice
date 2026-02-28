package controllers

import zio._
import zio.http._
import repositories.UserRepository

case class UserController(repo: UserRepository) {
  // Замість Http[Any, ...] використовуємо Routes
  val routes: Routes[Any, Response] = Routes(
    Method.GET / "users" / string("id") -> handler { (id: String, req: Request) =>
      repo.findById(id)
        .map {
          case Some(u) => Response.json(u.toString)
          case None => Response.status(Status.NotFound)
        }
        .catchAll { e =>
          // Можно залогировать ошибку:
          println(s"Database error: $e")
          ZIO.succeed(Response.status(Status.InternalServerError))
        }
    }
  )

}


