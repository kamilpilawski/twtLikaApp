package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.User
import java.util.*

/**
 * Created by Kamil on 11.03.2017.
 */
interface UserRepo : CrudRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>

    fun findAllByRolesId(roleId: Long): Optional<List<User>>
}