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
        .mapError(e => Response.status(Status.InternalServerError)) // Конвертуємо помилку БД у 500
    }
  )

}


