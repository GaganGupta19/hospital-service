class Api::V1::PatientsController < ApplicationController
  require 'net/http'

  PATIENT_URL = "http://patient-service-2"
  VENDOR_URL = "http://vaccine-vendor"

  def index
    result = HospitalVaccine.order(created_at: :desc)
    render json: result, status: :ok
  end

  def provide_vaccine
    if params[:hospital_id] == nil ||
       params[:vaccine_id] == nil ||
       params[:phone] == nil ||
       params[:vendor_id] == nil

      render json: {
          error: "vaccine_id, phone, hospital_id, vendor_id are mandatory"
        }, status: 412
      return
    end

    res = get_patient_details
    if res.body == nil || res.code != "200"
      render json: { error: "patient details get error"}, status: 412
      return
    end
    patient_data = JSON.parse(res.body)

    res = get_vendor_vaccine
    if res.body == nil || res.code != "200"
      render json: { error: "vaccine details get error"}, status: 412
      return
    end

    vaccine_details = JSON.parse(res.body)[0]
    if vaccine_details["available_count"] < 1
      render json: { error: "not enough vaccines available"}, status: 412
      return
    end

    res = update_patient_info(patient_data, vaccine_details)
    if res.body == nil || res.code != "200"
      render json: { error: "could not update patient details"}, status: 412
      return
    end

    res = update_vendor_info
    if res.body == nil || res.code != "200"
      render json: { error: "could not update vendor vaccine details"}, status: 412
      return
    end

    hospital = HospitalVaccine.new(patient_phone: patient_data["phone"],
      vaccine_name: vaccine_details["vaccine_name"],
      vendor_id: vaccine_details["vendor_id"],
      hospital_id: params[:hospital_id]
    )

    if hospital.save!
      render json: hospital, status: :ok
    else
      render json: { errors: hospital.error_messages }, status: 500
    end
  end

  private

    def get_patient_details
      uri = URI(PATIENT_URL)
      http = Net::HTTP.new(uri.hostname, uri.port)
      request = Net::HTTP::Get.new("/api/patient/getphone/#{params[:phone]}")
      return http.request(request)
    end

    def get_vendor_vaccine
      uri = URI(VENDOR_URL)
      http = Net::HTTP.new(uri.hostname, uri.port)
      request = Net::HTTP::Get.new("/vendors/#{params[:vaccine_id]}/#{params[:vendor_id]}")
      return http.request(request)
    end

    def update_patient_info(patient_data, vaccine_details)
      req = Net::HTTP::Put.new("/api/patient/updatevaccinedata")
      req.body = {
        "phone": patient_data["phone"],
        "name": patient_data["name"],
        "vaccine_data": [
          {
            "vaccine_name": vaccine_details["vaccine_name"],
            "taken_date": DateTime.now
          }
        ]
      }.to_json

      req.content_type = "application/json"
      uri = URI(PATIENT_URL)
      return Net::HTTP.new(uri.host, uri.port).start {|http| http.request(req) }
    end

    def update_vendor_info
      uri = URI(VENDOR_URL)
      http = Net::HTTP.new(uri.hostname, uri.port)
      request = Net::HTTP::Post.new("/vendors/update/#{params[:vaccine_id]}/#{params[:vendor_id]}")
      return http.request(request)
    end

end
