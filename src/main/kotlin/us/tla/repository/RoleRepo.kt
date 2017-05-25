package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.Role

/**
 * Created by Kamil on 25.05.2017.
 */
interface RoleRepo :CrudRepository<Role,Long>{
}