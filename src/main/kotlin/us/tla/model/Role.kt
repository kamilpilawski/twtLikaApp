package us.tla.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Created by Kamil on 12.03.2017.
 */
@Entity
class Role {

    @Id
    @Column(name = "idrole")
    val id: Long = 0L
    val title: String = ""
    val description: String = ""


    @OneToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonBackReference
    val users: List<User> = emptyList()

    override fun toString(): String {
        return "Role(id=$id, title='$title', description='$description')"
    }


}