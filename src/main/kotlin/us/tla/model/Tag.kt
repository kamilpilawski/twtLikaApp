package us.tla.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
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
        @JsonManagedReference("tagPosts")
        val posts: List<Post> = emptyList(),

        @OneToMany(mappedBy = "commentTags", fetch = FetchType.LAZY)
        @JsonManagedReference("tagComments")
        val comments: List<Comment> = emptyList()
) {
    override fun toString(): String {
        return "Tag(id=$id, title='$title')"
    }

}