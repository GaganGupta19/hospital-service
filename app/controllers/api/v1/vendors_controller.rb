class Api::V1::VendorsController < ApplicationController
  require 'net/http'

  VENDOR_URL = "http://vaccine-vendor"

  def index
    if params[:vendor_id] == nil
      render json: { error: "please pass vendor_id"}, status: 412
      return
    end

    uri = URI(VENDOR_URL)
    http = Net::HTTP.new(uri.hostname, uri.port)
    request = Net::HTTP::Get.new("/vendors/vaccineId/#{params[:vendor_id]}")
    res = http.request(request)

    render json: JSON.parse(res.body)
  end
end
