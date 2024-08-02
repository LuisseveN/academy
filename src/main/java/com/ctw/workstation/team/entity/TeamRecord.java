package com.ctw.workstation.team.entity;

public record TeamRecord(Long id, String name, String product, String defaultLocation) {

    private Long getId(){ return id;}

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public Team toEntity(){
        return new Team(this.name,this.product, this.defaultLocation);
    }
}
