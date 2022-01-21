package com.mrgrd56.somePackage.lombok;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HTMLElement {
    private String id;
    private String className;
    private String innerText;
    private Map<String, String> style;
    @Singular
    private List<HTMLElement> children;
}
