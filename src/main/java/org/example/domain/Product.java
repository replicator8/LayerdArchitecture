package org.example.domain;

import java.time.LocalDateTime;

public class Product {
    private final int id;
    private String name;
    private LocalDateTime dtCreated;
    private LocalDateTime expirationDate;
    private boolean isDamaged;
    private TemperatureMode mode;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
        this.dtCreated = LocalDateTime.now();
        this.expirationDate = LocalDateTime.now().plusDays(3);
        this.isDamaged = false;
        this.mode = TemperatureMode.getRandom();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isDamaged() {
        return isDamaged;
    }

    public void setDamaged(boolean damaged) {
        isDamaged = damaged;
    }

    public TemperatureMode getMode() {
        return mode;
    }

    public void setMode(TemperatureMode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dtCreated=" + dtCreated +
                ", expirationDate=" + expirationDate +
                ", isDamaged=" + isDamaged +
                ", mode=" + mode +
                '}';
    }
}
