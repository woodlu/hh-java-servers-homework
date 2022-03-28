package dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class GetCounterDto {

    private int counter;
    @JsonSerialize(using = MyLocalDateTimeSerializer.class)
    private LocalDateTime dateTime;

    public GetCounterDto(int counter) {
        this.counter = counter;
        this.dateTime = LocalDateTime.now();
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
