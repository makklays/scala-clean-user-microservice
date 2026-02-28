package models

import io.circe._
import io.circe.generic.auto._

/**
 * Model representing a user in the system.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @since 27.02.2026
 * @version 0.0.1
 */
case class User(
                 id: String,
                 name: String,
                 email: String,
                 password: String,
                 phone: Option[String],     // Используем Option для полей, которые могут быть NULL
                 firstname: Option[String],
                 lastname: Option[String],
                 postId: Option[Int],       // В Scala используем camelCase
                 departId: Option[Int],
                 roleId: Option[Int]
               )

object User {
  implicit val codec: Codec[User] = io.circe.generic.semiauto.deriveCodec[User]
}

