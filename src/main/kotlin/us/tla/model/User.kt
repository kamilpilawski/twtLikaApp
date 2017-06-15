package us.tla.model

import javax.persistence.*
import kotlin.jvm.Transient

/**
 * Created by Kamil on 11.03.2017.
 */
@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "iduser")
        val id: Long = 0L,

        @Column(unique = true)
        val email: String = "",

        val password: String = "",

        val enabled: Int = 1,

        @OneToMany(fetch = FetchType.EAGER)
        @JoinTable(name = "user_role",
                joinColumns = arrayOf(JoinColumn(name = "user_iduser", referencedColumnName = "iduser")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "role_idrole", referencedColumnName = "idrole")))
        val roles: MutableList<Role>? = null,

        @Transient
        val followed: Boolean = false
) {
    override fun toString(): String {
        return "User(id=$id, email='$email', password='$password', roles=$roles)"
    }


}