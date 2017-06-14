package us.tla.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import us.tla.model.Role
import java.util.*

/**
 * Created by Kamil on 25.05.2017.
 */
interface RoleRepo : CrudRepository<Role, Long> {

    @Query("select * from role join user_role on role.idrole=user_role.role_idrole where user_role.user_iduser = ?1", nativeQuery = true)
    fun findUserRoles(userId: Long): Optional<List<Role>>
}