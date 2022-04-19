package com.hospital.service.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.service.Constants;
import com.hospital.service.dtos.VaccineVendorResponse;
import com.hospital.service.dtos.PatientResponse;
import com.hospital.service.dtos.VaccinationRequest;
import com.hospital.service.dtos.VaccinationResponse;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import org.apache.logging.log4j.util.Strings;
import com.squareup.okhttp.MediaType;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class HospitalService {

    public VaccinationResponse provideVaccineToPatient(VaccinationRequest request) {
        VaccinationResponse response = new VaccinationResponse();

        if (Strings.isEmpty(request.getHospitalId())
                || Strings.isEmpty(request.getVaccineId())
                || Strings.isEmpty(request.getVendorId())
                || Strings.isEmpty(request.getPhone())) {

            response.getError().setMessage( "vaccine_id, phone, hospital_id, vendor_id are mandatory");
            return response;
        }

        PatientResponse patientResponse = null;
        try{
            patientResponse = getPatientDetails(request);
        }catch (Exception ex){
            response.getError().setMessage("Error while communicating to patient service");
            return response;
        }

        VaccineVendorResponse vaccineVendorResponse = null;
        try {
            vaccineVendorResponse = getVaccineDetails(request);
        }catch (Exception ex){
            response.getError().setMessage("Error while communicating to vaccine vendor service");
            return response;
        }

        try{
            boolean isSuccessFul = updateVaccineData(request);
            if(!isSuccessFul){
                response.getError().setMessage("Vaccine vendor update failed");
                return response;
            }
        }catch (Exception ex){
            response.getError().setMessage("Error while updating to vaccine vendor service");
            return response;
        }

        try{
            boolean isSuccessFul = updatePatientDetails(request, patientResponse, vaccineVendorResponse);
            if(!isSuccessFul){
                response.getError().setMessage("Patient data update failed");
                return response;
            }
        }catch (Exception ex){
            response.getError().setMessage("Error while updating to patient data");
            return response;
        }


        return response;
    }

    private PatientResponse getPatientDetails(VaccinationRequest request) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(Constants.PATIENT_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        OkHttpClient client = new OkHttpClient();

        Request req = new Request.Builder()
                .url(String.format("%s/api/patient/getphone/%s", Constants.PATIENT_URL, request.getPhone()))
                .build(); // defaults to GET

        Response response = client.newCall(req).execute();

        return mapper.readValue(response.body().byteStream(), PatientResponse.class);
    }

    private VaccineVendorResponse getVaccineDetails(VaccinationRequest request) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(Constants.VENDOR_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        OkHttpClient client = new OkHttpClient();

        Request req = new Request.Builder()
                .url(String.format(
                        "%s/vendors/%s/%s",
                        Constants.VENDOR_URL,
                        request.getVaccineId(),
                        request.getVendorId())
                )
                .build();

        Response response = client.newCall(req).execute();

        List<VaccineVendorResponse> res =  mapper.readValue(response.body().byteStream(), new TypeReference<List<VaccineVendorResponse>>(){});
        if(res != null && res.size() > 0){
            return res.get(0);
        }

        throw new RuntimeException("Empty Data received");
    }

    private boolean updatePatientDetails(
            VaccinationRequest request,
            PatientResponse patientResponse,
            VaccineVendorResponse vaccineVendorResponse
    ) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(Constants.PATIENT_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        OkHttpClient client = new OkHttpClient();

        ObjectMapper Obj = new ObjectMapper();
        PatientResponse requestJson = new PatientResponse();
        requestJson.setPatientId(patientResponse.getPatientId());
        requestJson.setName(patientResponse.getName());
        requestJson.setPhone(patientResponse.getPhone());
        requestJson.setVaccinationData(new ArrayList<>());

        PatientResponse.VaccinationData data = new PatientResponse.VaccinationData();
        data.setVaccineName(vaccineVendorResponse.getVaccineName());
        data.setTakenDate(Date.from(Instant.now()));
        requestJson.getVaccinationData().add(data);

        String jsonStr = Obj.writeValueAsString(requestJson);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonStr);

        Request req = new Request.Builder()
                .url(String.format(
                        "%s/api/patient/updatevaccinedata",
                        Constants.PATIENT_URL)
                )
                .put(body)
                .build();

        Response response = client.newCall(req).execute();

        return mapper.readValue(response.body().byteStream(), Boolean.class);
    }
    private boolean updateVaccineData(VaccinationRequest request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        URL url = new URL(Constants.VENDOR_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(JSON, "");

        Request req = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .url(String.format(
                        "%s/vendors/update/%s/%s",
                        Constants.VENDOR_URL,
                        request.getVaccineId(),
                        request.getVendorId())
                )
                .post(body)
                .build();

        Response response = client.newCall(req).execute();

        return response.code() == 200;
    }
}
