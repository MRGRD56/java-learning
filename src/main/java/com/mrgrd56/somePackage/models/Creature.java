package com.mrgrd56.somePackage.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class Creature {
    @SerializedName("birth_date")
    private Date birthDate;
}
