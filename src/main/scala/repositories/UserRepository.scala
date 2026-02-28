package repositories

import io.getquill._
import io.getquill.jdbczio.Quill
import zio._
import models.User
import java.sql.SQLException

trait UserRepository {
  def findById(id: String): ZIO[Any, SQLException, Option[User]]
}

case class UserRepositoryLive(quill: Quill.Postgres[SnakeCase]) extends UserRepository {

  import quill._

  override def findById(id: String): ZIO[Any, SQLException, Option[User]] =
    run(
      query[User]
        .filter(_.id == lift(id))
    ).map(_.headOption)
}

object UserRepositoryLive {

  val layer: URLayer[Quill.Postgres[SnakeCase], UserRepository] =
    ZLayer.fromFunction(UserRepositoryLive(_))
}

