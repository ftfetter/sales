package com.github.ftfetter.sales.pojos;

import java.math.BigDecimal;

public class ItemData {

    private String itemId;
    private Integer quantity;
    private BigDecimal price;

    public ItemData(String itemId, Integer quantity, BigDecimal price) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public static final class Builder {

        private String itemId;
        private Integer quantity;
        private BigDecimal price;

        public static Builder of() {
            return new Builder();
        }

        public Builder itemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ItemData build() {
            return new ItemData(itemId,quantity,price);
        }
    }
}
