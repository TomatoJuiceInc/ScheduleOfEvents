package ru.ScheduleOfEvents.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ScheduleOfEvents.models.*;
import ru.ScheduleOfEvents.repositories.VipClientApplicationRepository;

import java.util.List;


@Service
public class VipClientApplicationService {

    private final UserDetailsServiceImpl userDetailsService;
    private final VipClientApplicationRepository vipClientApplicationRepository;
    @Autowired
    public VipClientApplicationService(UserDetailsServiceImpl userDetailsService1, VipClientApplicationRepository vipClientApplicationRepository){

        this.userDetailsService = userDetailsService1;
        this.vipClientApplicationRepository = vipClientApplicationRepository;
    }

    @Transactional
    public void approveApplication(int applicationId) {
        VipApplication application = vipClientApplicationRepository.findById(applicationId).orElseThrow(() -> new RuntimeException("Application not found!"));
        application.getApplicantName().setRole(Role.VIP);
        userDetailsService.save(application.getApplicantName());
        vipClientApplicationRepository.deleteById(applicationId);

    }
    @Transactional
    public void rejectApplication(int applicationId) {
        vipClientApplicationRepository.deleteById(applicationId);
    }
    @Transactional
    public void save(VipApplication application) {
        vipClientApplicationRepository.save(application);
    }
    public List<VipApplication> findAll() {
        return vipClientApplicationRepository.findAll();
    }
    public VipApplication findByOwnerId(long id) {
        return vipClientApplicationRepository.findByApplicantName_Id(id);
    }


}
