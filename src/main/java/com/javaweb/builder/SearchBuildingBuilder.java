package com.javaweb.builder;

import java.util.List;

public class SearchBuildingBuilder {
    private String name;
    private Long floorArea;
    private String districtCode;
    private String ward;
    private String street;
    private Long numberOfBasement;
    private String direction;
    private String level;
    private Long rentAreaFrom;
    private Long rentAreaTo;
    private Long rentPriceFrom;
    private Long rentPriceTo;
    private String managerName;
    private String managerPhoneNumber;
    private Long staffId;
    private List<String> typeCodes;
    public String getName() {
        return name;
    }

    public Long getFloorArea() {
        return floorArea;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Long getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public String getLevel() {
        return level;
    }

    public Long getRentAreaFrom() {
        return rentAreaFrom;
    }

    public Long getRentAreaTo() {
        return rentAreaTo;
    }

    public Long getRentPriceFrom() {
        return rentPriceFrom;
    }

    public Long getRentPriceTo() {
        return rentPriceTo;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhoneNumber() {
        return managerPhoneNumber;
    }

    public Long getStaffId() {
        return staffId;
    }

    public List<String> getTypeCodes() {
        return typeCodes;
    }

    private SearchBuildingBuilder(Builder builder){
        this.name = builder.name;
        this.floorArea = builder.floorArea;
        this.districtCode = builder.districtCode;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.direction = builder.direction;
        this.level = builder.level;
        this.rentAreaFrom = builder.rentAreaFrom;
        this.rentAreaTo = builder.rentAreaTo;
        this.rentPriceFrom = builder.rentPriceFrom;
        this.rentPriceTo = builder.rentPriceTo;
        this.managerName = builder.managerName;
        this.managerPhoneNumber = builder.managerPhoneNumber;
        this.staffId = builder.staffId;
        this.typeCodes = builder.typeCodes;
    }
    public static class Builder{
        private String name;
        private Long floorArea;
        private String districtCode;
        private String ward;
        private String street;
        private Long numberOfBasement;
        private String direction;
        private String level;
        private Long rentAreaFrom;
        private Long rentAreaTo;
        private Long rentPriceFrom;
        private Long rentPriceTo;
        private String managerName;
        private String managerPhoneNumber;
        private Long staffId;
        private List<String> typeCodes;
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFloorArea(Long floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDistrictCode(String districtCode) {
            this.districtCode = districtCode;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Long numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setRentAreaFrom(Long rentAreaFrom) {
            this.rentAreaFrom = rentAreaFrom;
            return this;
        }

        public Builder setRentAreaTo(Long rentAreaTo) {
            this.rentAreaTo = rentAreaTo;
            return this;
        }

        public Builder setRentPriceFrom(Long rentPriceFrom) {
            this.rentPriceFrom = rentPriceFrom;
            return this;
        }

        public Builder setRentPriceTo(Long rentPriceTo) {
            this.rentPriceTo = rentPriceTo;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhoneNumber(String managerPhoneNumber) {
            this.managerPhoneNumber = managerPhoneNumber;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setTypeCodes(List<String> typeCodes) {
            this.typeCodes = typeCodes;
            return this;
        }

        public SearchBuildingBuilder build(){
            return new SearchBuildingBuilder(this);
        }
    }
}
