package mobi.sevenwinds.app.budget

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object AuthorService {
    suspend fun createAuthor(fio: String): AuthorEntity = withContext(Dispatchers.IO) {
        transaction {
            AuthorEntity.new {
                this.fio = fio
                this.creationDate = DateTime.now()
            }
        }
    }

    suspend fun getAuthors(limit: Int, offset: Int): List<AuthorEntity> = withContext(Dispatchers.IO) {
        transaction {
            AuthorEntity.all().limit(limit, offset).toList()
        }
    }
}