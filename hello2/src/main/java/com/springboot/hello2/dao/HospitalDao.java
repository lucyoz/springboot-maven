package com.springboot.hello2.dao;

import com.springboot.hello2.domain.Hospital;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;

    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //List<Hospital> -- 11만건 Hospital
    public void add(Hospital hospital){
        String sql = "INSERT INTO nation_wide_hospitals(`id`, `open_service_name`, `open_local_goverment_code`, `management_number`, `license_date`, `business_status`, `business_status_code`, `phone`, `full_address`, `road_name_address`, `hospital_name`, `business_type_name`, `healthcare_provider_count`, `patient_room_count`, `total_number_of_beds`, `total_area_size`) "
                +"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        this.jdbcTemplate.update(sql,
                hospital.getId(), hospital.getOpenServiceName(), hospital.getOpenLocalGovernmentCode(), hospital.getManagementNumber(),
                hospital.getLicenseDate(), hospital.getBusinessStatus(), hospital.getBusinessStatusCode(), hospital.getPhone(),
                hospital.getFullAddress(), hospital.getRoadNameAddress(), hospital.getHospitalName(),hospital.getBusinessTypeName(),
                hospital.getHealthcareProviderCount(), hospital.getPatientRoomCount(), hospital.getTotalNumberOfBeds(), hospital.getTotalAreaSize());


    }

    public void deleteAll(){
        String sql = "DELETE FROM nation_wide_hospitals;";
        this.jdbcTemplate.update(sql);
    }
}
