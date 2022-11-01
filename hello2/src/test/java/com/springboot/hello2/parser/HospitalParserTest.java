package com.springboot.hello2.parser;

import com.springboot.hello2.dao.HospitalDao;
import com.springboot.hello2.domain.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest    //ExtendsWith, ConfigurationContext 어노테이션 대신. SpringBoot가 스캔을 해서 등록한 Bean을 Test에서 쓸 수 있게.
class HospitalParserTest {

    String line1 = "\"1\",\"의원\",\"01_01_02_P\",\"3620000\",\"PHMA119993620020041100004\",\"19990612\",\"\",\"01\",\"영업/정상\",\"13\",\"영업중\",\"\",\"\",\"\",\"\",\"062-515-2875\",\"\",\"500881\",\"광주광역시 북구 풍향동 565번지 4호 3층\",\"광주광역시 북구 동문대로 24, 3층 (풍향동)\",\"61205\",\"효치과의원\",\"20211115113642\",\"U\",\"2021-11-17 02:40:00.0\",\"치과의원\",\"192630.735112\",\"185314.617632\",\"치과의원\",\"1\",\"0\",\"0\",\"52.29\",\"401\",\"치과\",\"\",\"\",\"\",\"0\",\"0\",\"\",\"\",\"0\",\"\",";
    String line770 = "";

    @Autowired      //주로 Test에서 & 서비스 코드는 final과 Constructor를 씁니다. 이렇게 해도 Spring이 DI를 해준다.
    ReadLineContext<Hospital> hospitalReadLineContext;

    //factory도 없는데 어떻게 DI가 되나요?
    // @SrpingBootApplication ---> @ComponentScan에서
    // @Component를 스프링부트가 다 빈으로 등록한다.
    @Autowired
    HospitalDao hospitalDao;


    @Test
    @DisplayName("Hospital이 insert가 잘 되고 select도 잘되는지")
    void addAndGet(){
        hospitalDao.deleteAll();
        assertEquals(0, hospitalDao.getCount());
        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);
        hospitalDao.add(hospital);
        assertEquals(1, hospitalDao.getCount());

        Hospital selectedHospital = hospitalDao.findById(hospital.getId());
        assertEquals( selectedHospital.getId(), hospital.getId());
        assertEquals( selectedHospital.getOpenServiceName(), hospital.getOpenServiceName());
        assertEquals( selectedHospital.getOpenLocalGovernmentCode(), hospital.getOpenLocalGovernmentCode());
        assertEquals( selectedHospital.getManagementNumber(), hospital.getManagementNumber());

        assertEquals( selectedHospital.getBusinessStatus(), hospital.getBusinessStatus());
        assertEquals( selectedHospital.getBusinessStatusCode(), hospital.getBusinessStatusCode());
        assertEquals( selectedHospital.getPhone(), hospital.getPhone());
        assertEquals( selectedHospital.getFullAddress(), hospital.getFullAddress());
        assertEquals( selectedHospital.getRoadNameAddress(), hospital.getRoadNameAddress());
        assertEquals( selectedHospital.getHospitalName(), hospital.getHospitalName());
        assertEquals( selectedHospital.getTotalNumberOfBeds(), hospital.getTotalNumberOfBeds());
        assertEquals( selectedHospital.getBusinessTypeName(), hospital.getBusinessTypeName());
        assertEquals( selectedHospital.getHealthcareProviderCount(), hospital.getHealthcareProviderCount());
        assertEquals( selectedHospital.getPatientRoomCount(), hospital.getPatientRoomCount());

        //날짜, float
        assertTrue(selectedHospital.getLicenseDate().isEqual(hospital.getLicenseDate()));
        assertEquals( selectedHospital.getTotalAreaSize(), hospital.getTotalAreaSize());
    }

    @Test
    @DisplayName("10만건 이상 데이터가 파싱되는지")
    void oneHundreadThousandRows() throws IOException {
        String filename="D:\\Lion\\fulldata_01_01_02_P_의원_utf8.csv";
        List<Hospital> hospitalList = hospitalReadLineContext.readByLine(filename);
        assertTrue(hospitalList.size()>1000);
        assertTrue(hospitalList.size()>10000);

        for (int i = 0; i < 10; i++) {
            System.out.println(hospitalList.get(i).getHospitalName());
        }
    }

    @Test
    @DisplayName("csv 1줄을 Hospital로 잘 만드는지 Test")
    void convertToHospital() {

        HospitalParser hp = new HospitalParser();
        Hospital hospital = hp.parse(line1);

        assertEquals(1, hospital.getId()); // col:0
        assertEquals("의원", hospital.getOpenServiceName()); // col:1
        assertEquals(3620000,hospital.getOpenLocalGovernmentCode()); // col:3
        assertEquals("PHMA119993620020041100004",hospital.getManagementNumber()); // col:4
        assertEquals(LocalDateTime.of(1999,6,12,0,0,0), hospital.getLicenseDate()); // col:5
        assertEquals(1, hospital.getBusinessStatus()); // col:7
        assertEquals(13, hospital.getBusinessStatusCode()); // col:9
        assertEquals("062-515-2875", hospital.getPhone()); // col:0
        assertEquals("광주광역시 북구 풍향동 565번지 4호 3층", hospital.getFullAddress()); // col:18
        assertEquals("광주광역시 북구 동문대로 24, 3층 (풍향동)", hospital.getRoadNameAddress()); // col:19
        assertEquals("효치과의원", hospital.getHospitalName());
        assertEquals("치과의원", hospital.getBusinessTypeName());
        assertEquals(1, hospital.getHealthcareProviderCount());
        assertEquals(0, hospital.getPatientRoomCount());
        assertEquals(0, hospital.getTotalNumberOfBeds());
        assertEquals(52.29f, hospital.getTotalAreaSize());

    }
}