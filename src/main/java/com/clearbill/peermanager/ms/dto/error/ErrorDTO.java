package com.clearbill.peermanager.ms.dto.error;

import java.util.List;

public class ErrorDTO {

    public List<String> error;

    public String errorType;

    public List<String> resolution;

    public String path;

    public String timeStamp;


    public ErrorDTO includeError(List<String> error){
        this.setError(error);
        return this;
    }

    public ErrorDTO includeErrorType(String errorType){
        this.setErrorType(errorType);
        return this;
    }

    public ErrorDTO includeResolution(List<String> resolution){
        this.setResolution(resolution);
        return  this;
    }

    public ErrorDTO includePath(String path){
        this.setPath(path);

        return this;
    }
    public ErrorDTO includeTimeStamp(String timeStamp){
        this.setTimeStamp(timeStamp);
        return  this;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public List<String> getResolution() {
        return resolution;
    }

    public void setResolution(List<String> resolution) {
        this.resolution = resolution;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "error=" + error +
                ", errorType='" + errorType + '\'' +
                ", resolution=" + resolution +
                ", path='" + path + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
