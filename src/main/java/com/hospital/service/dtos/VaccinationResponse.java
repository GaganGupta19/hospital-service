package com.hospital.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VaccinationResponse {

    @JsonProperty("error")
    Error error;

    @JsonProperty("result")
    String result;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public static class Error {
        @JsonProperty("message")
        String message;

        @JsonProperty("code")
        String code;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
