package us.tla.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Created by Kamil on 12.03.2017.
 */
@Entity
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idrole")
    var id: Long = 0L
    lateinit var title: String
    lateinit var description: String


    @OneToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    @JsonBackReference
    lateinit var users: List<User>

    override fun toString(): String {
        return "Role(id=$id, title='$title', description='$description')"
    }


}