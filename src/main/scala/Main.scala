
import zio._
import zio.http._
import controllers.*

/**
 * The main entry point of the application.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @since 15.02.2026 - 27.02.2026
 * @version 0.0.1
 */
object Main extends ZIOAppDefault {
  //override def run = Server.serve(UserController.routes).provide(Server.default)

  val allRoutes = UserController.routes ++ HomeController.routes

  override def run =
    Server.serve(allRoutes).provide(Server.defaultWithPort(8084)) // indicar a porta 8084 para o servidor

  //override def run = Server.serve(UserController.routes).provide(Server.defaultWithPort(8081))
}

/*@main
def main(): Unit = {
  println("Hello world, from Scala!")
  hello("TechMatrix18")
}

def hello(name: String): Unit = {
  println(s"Hello, $name!")
}*/

