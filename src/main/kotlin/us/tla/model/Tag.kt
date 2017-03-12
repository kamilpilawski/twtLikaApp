package us.tla.model

import javax.persistence.*

/**
 * Created by Kamil on 12.03.2017.
 */
@Entity
class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idtag")
    val id: Long = 0L
    val title: String = ""

    override fun toString(): String {
        return "Tag(id=$id, title='$title')"
    }

}