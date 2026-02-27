package services

import pdi.jwt.{Jwt, JwtAlgorithm, JwtClaim}
import java.time.Instant
import scala.util.{Try, Success, Failure}

/**
 * Service for creating and validating JWT tokens for user authentication.
 *
 * @author Alexander Kuziv <makklays@gmail.com>
 * @company TechMatrix18
 * @since 27.02.2026
 * @version 0.0.1
 */
object JwtService {

  private val secretKey = "my-super-secret-key" // можно вынести в конфиг
  private val algorithm = JwtAlgorithm.HS256
  private val tokenExpirySeconds = 3600L          // 1 час
  private val refreshTokenExpirySeconds = 24 * 3600L // 24 часа

  // Generate access token
  def createAccessToken(userId: String): String = {
    val claim = JwtClaim(
      content = s"""{"userId":"$userId"}""",
      issuedAt = Some(Instant.now.getEpochSecond),
      expiration = Some(Instant.now.plusSeconds(tokenExpirySeconds).getEpochSecond)
    )
    Jwt.encode(claim, secretKey, algorithm)
  }

  // Generate refresh token
  def createRefreshToken(userId: String): String = {
    val claim = JwtClaim(
      content = s"""{"userId":"$userId"}""",
      issuedAt = Some(Instant.now.getEpochSecond),
      expiration = Some(Instant.now.plusSeconds(refreshTokenExpirySeconds).getEpochSecond)
    )
    Jwt.encode(claim, secretKey, algorithm)
  }

  // Check if the token is valid and return the user ID if it is
  def validateToken(token: String): Option[String] = {
    Jwt.decode(token, secretKey, Seq(algorithm)).toOption.flatMap { claim =>
      // Проверяем истечение токена
      val now = Instant.now.getEpochSecond
      if (claim.expiration.exists(_ > now)) {
        val userIdRegex = """"userId":"(.*?)"""".r
        userIdRegex.findFirstMatchIn(claim.content).map(_.group(1))
      } else None
    }
  }

}

