class Api::V1::VendorsController < ApplicationController
  require 'net/http'

  def index
    if params[:vendor_id] == nil
      render json: { error: "please pass vendor_id"}, status: 412
      return
    end

    uri = URI("http://localhost:8080")
    http = Net::HTTP.new(uri.hostname, uri.port)
    request = Net::HTTP::Get.new("/vendors/vaccineId/#{params[:vendor_id]}")
    res = http.request(request)

    render json: JSON.parse(res.body)
  end
end
