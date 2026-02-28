package models

package models

import java.time.Instant
import io.circe._
import io.circe.generic.auto._

/**
 * Модель для хранения Refresh-токенов в базе данных.
 */
case class UserToken(
                      id: Option[Int] = None,       // Option, так как ID генерируется БД (SERIAL)
                      userId: String,               // CamelCase для Scala (в БД будет user_id)
                      tokenHash: String,            // Хеш токена (в БД token_hash)
                      createdAt: Option[Instant] = None, // Генерируется БД через DEFAULT CURRENT_TIMESTAMP
                      expiresAt: Instant,           // Время истечения (TIMESTAMP)
                      isRevoked: Boolean = false    // Флаг отзыва токена
                    )

object UserToken {
  // Кодек для JSON (Circe)
  implicit val codec: Codec[UserToken] = io.circe.generic.semiauto.deriveCodec[UserToken]
}

