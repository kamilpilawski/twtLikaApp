package us.tla.model

import javax.persistence.*

/**
 * Created by Kamil on 24.05.2017.
 */
@Entity
data class Post(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idpost")
        val id: Long = 0,
        val title: String = "",
        val content: String = "",
        @Column(name = "user_iduser")
        val userId: Long = 0
) {
    override fun toString(): String {
        return "Post(id=$id, title='$title', content='$content', userId=$userId)"
    }


}