package us.tla.repository

import org.springframework.data.repository.CrudRepository
import us.tla.model.User

/**
 * Created by Kamil on 11.03.2017.
 */
interface UserRepo : CrudRepository<User, Long> {

}