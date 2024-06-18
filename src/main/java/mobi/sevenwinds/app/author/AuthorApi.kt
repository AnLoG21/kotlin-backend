package mobi.sevenwinds.app.budget

import com.papsign.ktor.openapigen.annotations.parameters.PathParam
import com.papsign.ktor.openapigen.annotations.parameters.QueryParam
import com.papsign.ktor.openapigen.annotations.type.number.integer.max.Max
import com.papsign.ktor.openapigen.annotations.type.number.integer.min.Min
import com.papsign.ktor.openapigen.route.info
import com.papsign.ktor.openapigen.route.path.normal.NormalOpenAPIRoute
import com.papsign.ktor.openapigen.route.path.normal.get
import com.papsign.ktor.openapigen.route.path.normal.post
import com.papsign.ktor.openapigen.route.response.respond
import com.papsign.ktor.openapigen.route.route

fun NormalOpenAPIRoute.author() {
    route("/author") {
        route("/create").post<Unit, AuthorEntity, AuthorEntity>(info("Создать автора")) { _, body ->
            respond(AuthorService.createAuthor(body.fio))
        }

        route("/list") {
            get<AuthorListParam, List<AuthorEntity>>(info("Получить список авторов")) { param ->
                respond(AuthorService.getAuthors(param.limit, param.offset))
            }
        }
    }
}

data class AuthorListParam(
    @QueryParam("Лимит пагинации") val limit: Int,
    @QueryParam("Смещение пагинации") val offset: Int
)

data class AuthorEntity(
    val id: Int,
    val fio: String,
    val creationDate: DateTime
)