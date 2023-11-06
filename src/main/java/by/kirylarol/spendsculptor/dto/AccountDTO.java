package by.kirylarol.spendsculptor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.sql.Date;

public class AccountDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @NotEmpty(message = "Дата не может быть пустой")
    private Date date;

    @NotEmpty(message = "Название счета не может быть пустым")
    @Size(min = 2, max = 100, message = "Название счета может содержать от 2 до 100 символов")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = Date.valueOf(date);
    }
}
