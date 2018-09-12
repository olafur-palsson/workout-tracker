
package server.review;

import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called reviewRepository
// // CRUD refers Create, Read, Update, Delete
//
public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> { }
