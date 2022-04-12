class Api::V1::HospitalsController < ApplicationController

  def create
    hospital = Hospital.new(hospital_params)
    if hospital.save!
      render json: { hospital: hospital }, status: :ok
    else
      render json: hospital.error_messages, status: :precondition_failed
    end
  end

  def index
    hospitals = Hospital.all
    render json: { count: hospitals.count, data: hospitals }
  end

  private
    def hospital_params
      params.require(:hospital).permit(:name, :location, :phone)
    end
end
