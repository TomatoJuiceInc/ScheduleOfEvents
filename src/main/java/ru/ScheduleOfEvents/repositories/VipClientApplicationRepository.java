package ru.ScheduleOfEvents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ScheduleOfEvents.models.VipApplication;

@Repository
public interface VipClientApplicationRepository extends JpaRepository<VipApplication, Integer> {
    VipApplication findByApplicantName_Id(long id);
}
