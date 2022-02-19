package br.com.pedrodavi.msworkday.service;

import br.com.pedrodavi.msworkday.model.ResponseDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WorkdayCheckService {

    public static LocalDate convertDate(String dateReq) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("ddMMyyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormated = format2.format(format1.parse(dateReq));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return LocalDate.parse(dateFormated, formatter);
    }

    public static boolean fimDeSemana(LocalDate ld) {
        DayOfWeek d = ld.getDayOfWeek();
        return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
    }

    public static ResponseDTO checkWorkday(String req) throws ParseException {

        if (req.length() != 8) {
            ResponseDTO error = new ResponseDTO();
            error.setMsg("ERRO! Data tem que conter 8 caracteres. Ex: DDMMAAAA");
            return error;
        }

        LocalDate date = WorkdayCheckService.convertDate(req);
        String day = date.getDayOfWeek().name();

        String dia = "";

        switch (day) {
            case "SUNDAY":
                dia = "Domingo";
                break;
            case "SATURDAY":
                dia = "Sábado";
                break;
            case "MONDAY":
                dia = "Segunda";
                break;
            case "TUESDAY":
                dia = "Terça";
                break;
            case "WEDNESDAY":
                dia = "Quarta";
                break;
            case "THURSDAY":
                dia = "Quinta";
                break;
            case "FRIDAY":
                dia = "Sexta";
                break;
            default:
                dia = "";
                break;
        }

        if (WorkdayCheckService.fimDeSemana(date)) {
            ResponseDTO fds = new ResponseDTO();
            fds.setData(date.toString());
            fds.setDiaDaSemana(dia);
            fds.setUtil("N");
            fds.setMsg("Final de semana");
            return fds;
        }

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(date.toString());
        responseDTO.setDiaDaSemana(dia);
        responseDTO.setUtil("S");
        responseDTO.setMsg("OK");
        return responseDTO;

    }

}
