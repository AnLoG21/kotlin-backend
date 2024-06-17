package mobi.sevenwinds.app.budget

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object BudgetTable : IntIdTable("budget") {
    val year = integer("year")
    val month = integer("month")
    val amount = integer("amount")
    val type = enumerationByName("type", 100, BudgetType::class)
    val authorId = reference("author_id", AuthorTable)
}

class BudgetEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BudgetEntity>(BudgetTable)

    var year by BudgetTable.year
    var month by BudgetTable.month
    var amount by BudgetTable.amount
    var type by BudgetTable.type

    fun toResponse(): BudgetRecord {
        return BudgetRecord(year, month, amount, type)
    }
}

object AuthorTable : IntIdTable("author") {
    val fio = varchar("fio", 255)
    val creationDate = datetime("creation_date")
}

class AuthorEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AuthorEntity>(AuthorTable)

    var fio by AuthorTable.fio
    var creationDate by AuthorTable.creationDate
}

suspend fun createAuthor(fio: String): AuthorEntity {
    return AuthorEntity.new {
        this.fio = fio
        this.creationDate = DateTime.now()
    }
}