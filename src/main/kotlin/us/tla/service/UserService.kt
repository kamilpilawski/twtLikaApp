package us.tla.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.tla.model.User
import us.tla.repository.UserRepo

/**
 * Created by Kamil on 15.06.2017.
 */
@Service
class UserService {

    @Autowired
    lateinit var userRepo: UserRepo

    fun findFollowed(id: Long): List<User> {

        val users = userRepo.findFollowed(id)

        return when {
            users.isPresent -> {
                users.get().map { it.copy(followed = true) }
            }
            else -> {
                emptyList()
            }
        }
    }

}