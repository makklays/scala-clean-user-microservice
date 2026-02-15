
/**
 * The main entry point of the application.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @since 15.02.2026
 * @version 0.0.1
 */
@main
def main(): Unit = {
  println("Hello world, from Scala!")
  hello("TechMatrix18")
}

def hello(name: String): Unit = {
  println(s"Hello, $name!")
}

