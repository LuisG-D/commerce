package com.commerce.commerce.mayorist.dto;

import com.commerce.commerce.mayorist.domain.Mayorist;

import java.util.List;

public class MayoristListDTO {
    private List<Mayorist> mayorists;
    public MayoristListDTO(){}
    public List<Mayorist>getMayorists(){return mayorists;}
    public void setMayorists(List<Mayorist>mayorists){this.mayorists = mayorists;}
}
