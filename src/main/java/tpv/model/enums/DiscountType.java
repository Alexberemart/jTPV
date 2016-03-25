package tpv.model.enums;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum DiscountType {

    SEVENTY_OFF(0, "70% la segunda unidad"),
    THREE_PER_TWO(1, "3x2");

    protected Integer value;
    protected String description;

    DiscountType(Integer code, String description) {
        this.value = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static DiscountType parse(Integer id) {
        DiscountType discountType = null; // Default
        for (DiscountType value : DiscountType.values()) {
            if (value.getValue().equals(id)) {
                discountType = value;
                break;
            }
        }
        return discountType;
    }

    public static Map asMap() {
        DiscountType[] values = DiscountType.values();
        Map<Integer, String> result = new HashMap();
        for (DiscountType value : values) {
            result.put(value.getValue(), value.getDescription());
        }

        return result;
    }
}
