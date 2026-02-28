package layers

import zio._
import javax.sql.DataSource
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

object DataSourceLayer {
  val live: ZLayer[Any, Throwable, DataSource] = ZLayer.scoped {
    ZIO.acquireRelease {
      ZIO.attempt {
        val config = new HikariConfig()
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/scala_db") // назва бази
        config.setUsername("admin") // юзер
        config.setPassword("admin") // пароль
        config.setDriverClassName("org.postgresql.Driver")
        new HikariDataSource(config)
      }
    } { ds =>
      ZIO.attempt(ds.close()).orDie
    }
  }
}

