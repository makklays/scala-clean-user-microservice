package controllers

import zio.http._

object HomeController {
  val routes: Routes[Any, Response] = Routes(
    Method.GET / "hello" -> handler(Response.text("Hello from HomeController!"))
  )
}


