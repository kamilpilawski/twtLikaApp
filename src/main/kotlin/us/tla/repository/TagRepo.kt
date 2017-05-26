package us.tla.repository

import org.springframework.data.repository.PagingAndSortingRepository
import us.tla.model.Tag

/**
 * Created by Kamil on 26.05.2017.
 */
interface TagRepo : PagingAndSortingRepository<Tag, Long> {
}