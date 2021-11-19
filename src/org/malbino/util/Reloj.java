/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.malbino.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author malbino
 */
public class Reloj {

    public static int getAño() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.YEAR);
    }

    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();

        return calendar.getTime();
    }

    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();

        return calendar;
    }

    public static String getFecha_ddMMyyyy() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(calendar.getTime());
    }

    public static String getFecha_ddMMyyyyHHmm() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return sdf.format(calendar.getTime());
    }

    public static String getFecha_yyyyMMdd() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(calendar.getTime());
    }

    public static String getHora_HHmmss() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(calendar.getTime());
    }

    public static String formatearFecha_yyyyMMdd(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        return sdf.format(fecha);
    }

    public static String formatearFecha_ddMMyyyy(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.format(fecha);
    }

    public static String formatearFecha_HHmmss(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        return sdf.format(fecha);
    }

    public static String formatearFecha_HHmm(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        return sdf.format(fecha);
    }

    public static String formatearFecha_ddMMyyyyHHmm(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        return sdf.format(fecha);
    }

    public static String formatearFecha_ddMMyyyyHHmmss(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return sdf.format(fecha);
    }

    public static String formatearFecha_ddMMMMyyyy(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'de' MMMMM 'de' yyyy");

        return sdf.format(fecha);
    }

    public static String formatearFecha_yyyy(Date fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

        return sdf.format(fecha);
    }

    public static Date getInicioAño(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMinimum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));

        return c.getTime();
    }

    public static Date getFinAño(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));

        return c.getTime();
    }

    public static Date getInicioMes(int mes, int año) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, año);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));

        return c.getTime();
    }

    public static Date getFinMes(int mes, int año) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, año);
        c.set(Calendar.MONTH, mes);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));

        return c.getTime();
    }

    public static Date getInicioDia(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(Calendar.HOUR_OF_DAY, c.getActualMinimum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMinimum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMinimum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMinimum(Calendar.MILLISECOND));

        return c.getTime();
    }

    public static Date getFinDia(Date fecha) {
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
        c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
        c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
        c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));

        return c.getTime();
    }

    public static int edad(Date fecha) {
        Calendar hoy = Calendar.getInstance();

        Calendar nacimiento = Calendar.getInstance();
        nacimiento.setTime(fecha);

        int años = hoy.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);
        int meses = hoy.get(Calendar.MONTH) - nacimiento.get(Calendar.MONTH);
        int dias = hoy.get(Calendar.DAY_OF_MONTH) - nacimiento.get(Calendar.DAY_OF_MONTH);

        if (meses < 0 || (meses == 0 && dias < 0)) {
            años--;
        }

        return años;
    }
}
