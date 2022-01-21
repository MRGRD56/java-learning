package com.mrgrd56.somePackage.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Pet extends Creature {
    @SerializedName("name")
    private String name;
}
