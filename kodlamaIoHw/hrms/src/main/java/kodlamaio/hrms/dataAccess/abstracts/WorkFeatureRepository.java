package kodlamaio.hrms.dataAccess.abstracts;

import kodlamaio.hrms.entities.concretes.TypeOfWorkFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkFeatureRepository extends JpaRepository<TypeOfWorkFeature, Integer> {
    TypeOfWorkFeature findByWorkTypeId(int id);

    List<TypeOfWorkFeature> findByWorkTypeNameContains(String name);

    List<TypeOfWorkFeature> findAllByWorkTypeId(int id);
}
