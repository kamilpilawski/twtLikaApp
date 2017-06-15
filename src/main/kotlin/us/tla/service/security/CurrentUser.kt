package us.tla.service.security

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

/**
 * Created by Kamil on 15.06.2017.
 */
class CurrentUser(val user: us.tla.model.User) : User(user.email, user.password, AuthorityUtils.createAuthorityList(user.roles?.map { it.title }?.joinToString(",")))