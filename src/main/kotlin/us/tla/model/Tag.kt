package us.tla.model

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

/**
 * Created by Kamil on 12.03.2017.
 */
@Entity
data class Tag(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idtag")
        val id: Long = 0,
        val title: String = "",

        @OneToMany(mappedBy = "tags", fetch = FetchType.LAZY)
        @JsonBackReference
        val posts: List<Post> = emptyList(),

        @OneToMany(mappedBy = "tags", fetch = FetchType.LAZY)
        @JsonBackReference
        val comments: List<Comment> = emptyList()
) {
    override fun toString(): String {
        return "Tag(id=$id, title='$title')"
    }

}