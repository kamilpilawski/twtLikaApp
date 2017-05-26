package us.tla.model

import javax.persistence.*

/**
 * Created by Kamil on 11.03.2017.
 */
@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "iduser")
        val id: Long = 0L,
        @Column(name = "email", unique = true)
        val email: String = "",
        val password: String = "",

        @OneToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_role",
                joinColumns = arrayOf(JoinColumn(name = "user_iduser", referencedColumnName = "iduser")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "role_idrole", referencedColumnName = "idrole")))
        val roles: MutableList<Role>? = null
) {
    override fun toString(): String {
        return "User(id=$id, email='$email', password='$password', roles=$roles)"
    }


}