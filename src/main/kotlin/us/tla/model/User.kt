package us.tla.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

/**
 * Created by Kamil on 11.03.2017.
 */
@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    val id: Long = 0L

    val email: String = ""

    val password: String = ""

    @OneToMany(/*cascade = arrayOf(CascadeType.ALL),*/ fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = arrayOf(JoinColumn(name = "user_iduser", referencedColumnName = "iduser")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "role_idrole", referencedColumnName = "idrole")))
    val roles: MutableList<Role>? = null

    override fun toString(): String {
        return "User(id=$id, email='$email', password='$password', roles=$roles)"
    }


}